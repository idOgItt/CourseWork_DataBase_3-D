CREATE OR REPLACE VIEW v_orders_summary AS
SELECT
    o.OrderID,
    u.Username AS "User",
    s.Name AS "Status",
    o.TotalAmount,
    o.DiscountCode,
    CASE
        WHEN o.DiscountCode IS NOT NULL THEN
            d.DiscountAmount || ' ' || dt.Name
        ELSE
            'No Discount'
        END AS "DiscountDetails",
    o.OrderDate
FROM Orders o
         LEFT JOIN Users u ON o.UserID = u.UserID
         LEFT JOIN Discounts d ON o.DiscountCode = d.Code
         LEFT JOIN DiscountTypes dt ON d.DiscountType = dt.DiscountTypeID
         LEFT JOIN OrderStatuses s ON o.StatusID = s.StatusID;

CREATE OR REPLACE VIEW v_models_popularity AS
SELECT
    m.ModelID,
    m.Name AS "ModelName",
    c.Name AS "Category",
    COALESCE(SUM(oi.Quantity), 0) AS "TotalSold",
    COUNT(DISTINCT oi.OrderID) AS "TotalOrders"
FROM Models m
         LEFT JOIN Categories c ON m.CategoryID = c.CategoryID
         LEFT JOIN OrderItems oi ON m.ModelID = oi.ModelID
GROUP BY m.ModelID, m.Name, c.Name;


CREATE OR REPLACE VIEW v_reviews_summary AS
SELECT
    r.ReviewID,
    u.Username AS "Reviewer",
    m.Name AS "ModelName",
    r.Text AS "ReviewText",
    r.Rating,
    r.DateCreated AS "ReviewDate"
FROM Reviews r
         LEFT JOIN Users u ON r.UserID = u.UserID
         LEFT JOIN Models m ON r.ModelID = m.ModelID;


CREATE OR REPLACE VIEW v_discounts_analysis AS
SELECT
    d.Code AS "DiscountCode",
    d.DiscountAmount,
    dt.Name AS "DiscountType",
    d.StartDate,
    d.EndDate,
    d.UsageLimit,
    COUNT(o.OrderID) AS "OrdersUsingDiscount"
FROM Discounts d
         LEFT JOIN DiscountTypes dt ON d.DiscountType = dt.DiscountTypeID
         LEFT JOIN Orders o ON d.Code = o.DiscountCode
GROUP BY
    d.Code, d.DiscountAmount, dt.Name,
    d.StartDate, d.EndDate, d.UsageLimit;


CREATE OR REPLACE VIEW v_order_details AS
SELECT
    o.OrderID,
    u.Username AS "User",
    o.OrderDate,
    s.Name AS "Status",
    m.Name AS "ModelName",
    oi.Quantity,
    (m.Price * oi.Quantity)::money AS "LineTotal"
FROM Orders o
         LEFT JOIN Users u ON o.UserID = u.UserID
         LEFT JOIN OrderItems oi ON o.OrderID = oi.OrderID
         LEFT JOIN Models m ON oi.ModelID = m.ModelID
         LEFT JOIN OrderStatuses s ON o.StatusID = s.StatusID;


CREATE OR REPLACE VIEW v_user_statistics AS
SELECT
    u.UserID,
    u.Username,
    u.Email,
    COUNT(DISTINCT o.OrderID) AS "TotalOrders",
    SUM(
            CASE
                WHEN s.Name IN ('PAID', 'SHIPPED', 'DELIVERED')
                    THEN o.TotalAmount::numeric
                ELSE 0::numeric
                END
    )::money AS "TotalSpent",
    COUNT(DISTINCT r.ReviewID) AS "TotalReviews"
FROM Users u
         LEFT JOIN Orders o ON u.UserID = o.UserID
         LEFT JOIN OrderStatuses s ON o.StatusID = s.StatusID
         LEFT JOIN Reviews r ON u.UserID = r.UserID
GROUP BY
    u.UserID, u.Username, u.Email;


