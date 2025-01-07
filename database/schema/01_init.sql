DROP TABLE IF EXISTS Logs CASCADE;
DROP TABLE IF EXISTS ActionTypes CASCADE;
DROP TABLE IF EXISTS Payments CASCADE;
DROP TABLE IF EXISTS Reviews CASCADE;
DROP TABLE IF EXISTS OrderItems CASCADE;
DROP TABLE IF EXISTS Orders CASCADE;
DROP TABLE IF EXISTS Models CASCADE;
DROP TABLE IF EXISTS Categories CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS Discounts CASCADE;
DROP TABLE IF EXISTS Permissions CASCADE;
DROP TABLE IF EXISTS RolePermissions CASCADE;
DROP TABLE IF EXISTS SuspiciousActionTypes CASCADE;
DROP TABLE IF EXISTS SuspiciousLogs CASCADE;
DROP TABLE IF EXISTS Images CASCADE;
DROP TABLE IF EXISTS UserImages CASCADE;

CREATE TABLE Roles (
                       RoleID SERIAL PRIMARY KEY,
                       RoleName VARCHAR(20) NOT NULL UNIQUE DEFAULT 'GUEST'
);

CREATE TABLE Permissions (
                             PermissionID SERIAL PRIMARY KEY,
                             PermissionName VARCHAR(50) NOT NULL UNIQUE,
                             Description VARCHAR(255)
);

CREATE TABLE RolePermissions (
                                 RoleID INT REFERENCES Roles(RoleID) ON DELETE CASCADE,
                                 PermissionID INT REFERENCES Permissions(PermissionID) ON DELETE CASCADE,
                                 PRIMARY KEY (RoleID, PermissionID)
);

CREATE TABLE Users (
                       UserID SERIAL PRIMARY KEY,
                       Username VARCHAR(50) NOT NULL,
                       Email VARCHAR(100) NOT NULL UNIQUE,
                       PasswordHash VARCHAR(255) NOT NULL,
                       RoleID INT NOT NULL REFERENCES Roles(RoleID),
                       RegistrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Categories (
                            CategoryID SERIAL PRIMARY KEY,
                            Name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Models (
                        ModelID SERIAL PRIMARY KEY,
                        UserID INT REFERENCES Users(UserID),
                        Name VARCHAR(100) NOT NULL,
                        Description VARCHAR(800),
                        Price MONEY NOT NULL  CHECK (Price >= 0::MONEY),
                        CategoryID INT REFERENCES Categories(CategoryID),
                        Rating DECIMAL(3, 2) DEFAULT 0.0,
                        QuantityAvailable INT NOT NULL CHECK (QuantityAvailable >= 0),
                        Status VARCHAR(20) NOT NULL CHECK (Status IN ('ACTIVE', 'INACTIVE', 'ARCHIVED')),
                        DateAdded TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Discounts (
                           DiscountID SERIAL PRIMARY KEY,
                           Code VARCHAR(50) NOT NULL UNIQUE,
                           DiscountAmount MONEY NOT NULL CHECK (DiscountAmount >= 0::MONEY),
                           DiscountType VARCHAR(20) NOT NULL CHECK (DiscountType IN ('FIXED', 'PERCENT')),
                           StartDate TIMESTAMPTZ NOT NULL,
                           EndDate TIMESTAMPTZ NOT NULL CHECK (EndDate > StartDate),
                           UsageLimit INT CHECK (UsageLimit >= 0),
                           TimesUsed INT DEFAULT 0 CHECK (TimesUsed >= 0)
);

CREATE TABLE Orders (
                        OrderID SERIAL PRIMARY KEY,
                        UserID INT REFERENCES Users(UserID),
                        OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        TotalAmount MONEY NOT NULL CHECK (TotalAmount >= 0::MONEY),
                        Status VARCHAR(20) NOT NULL CHECK (Status IN ('PENDING', 'PAID', 'SHIPPED', 'DELIVERED', 'CANCELLED')),
                        DiscountCode VARCHAR(50),
                        CONSTRAINT fk_discount_code FOREIGN KEY (DiscountCode) REFERENCES Discounts(Code)
);

CREATE TABLE OrderItems (
                            OrderItemID SERIAL PRIMARY KEY,
                            OrderID INT REFERENCES Orders(OrderID),
                            ModelID INT REFERENCES Models(ModelID),
                            Quantity INT NOT NULL CHECK (Quantity > 0)
);

CREATE TABLE Reviews (
                         ReviewID SERIAL PRIMARY KEY,
                         UserID INT REFERENCES Users(UserID),
                         ModelID INT REFERENCES Models(ModelID),
                         Text VARCHAR(300),
                         Rating INT CHECK (Rating BETWEEN 1 AND 5),
                         DateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Payments (
                          PaymentID SERIAL PRIMARY KEY,
                          OrderID INT REFERENCES Orders(OrderID),
                          Amount MONEY NOT NULL CHECK (Amount >= 0::MONEY),
                          PaymentMethod VARCHAR(50) NOT NULL CHECK (PaymentMethod IN ('CREDIT_CARD', 'PAYPAL', 'BANK_TRANSFER', 'CASH')),
                          PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ActionTypes (
                             ActionTypeID SERIAL PRIMARY KEY,
                             ActionName VARCHAR(50) NOT NULL UNIQUE,
                             Description VARCHAR(120)
);


CREATE TABLE Logs (
                      LogID SERIAL PRIMARY KEY,
                      UserID INT REFERENCES Users(UserID),
                      ActionTypeID INT REFERENCES ActionTypes(ActionTypeID),
                      Description VARCHAR(800),
                      Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE SuspiciousActionTypes (
                                       ActionTypeID SERIAL PRIMARY KEY,
                                       ActionName VARCHAR(50) NOT NULL UNIQUE,
                                       Description VARCHAR(300)
);

CREATE TABLE SuspiciousLogs (
                                LogID SERIAL PRIMARY KEY,
                                UserID INT REFERENCES Users(UserID),
                                ActionTypeID INT REFERENCES SuspiciousActionTypes(ActionTypeID),
                                Description VARCHAR(800),
                                Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Images (
                        ImageID SERIAL PRIMARY KEY,
                        ModelID INT REFERENCES Models(ModelID) ON DELETE CASCADE,
                        FileName VARCHAR(255) NOT NULL,
                        FileData BYTEA NOT NULL,
                        UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT unique_model_filename UNIQUE (ModelID, FileName)
);

CREATE TABLE UserImages (
                            ImageID SERIAL PRIMARY KEY,
                            UserID INT REFERENCES Users(UserID) ON DELETE CASCADE,
                            FileName VARCHAR(255) NOT NULL,
                            FileData BYTEA NOT NULL,
                            UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT unique_user_filename UNIQUE (UserID, FileName)
);