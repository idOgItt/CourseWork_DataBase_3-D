classDiagram
direction BT
class actiontypes {
   varchar(50) actionname
   varchar(120) description
   integer actiontypeid
}
class categories {
   varchar(100) name
   integer categoryid
}
class discounts {
   varchar(50) code
   money discountamount
   integer discounttype
   timestamp with time zone startdate
   timestamp with time zone enddate
   integer usagelimit
   integer timesused
   integer discountid
}
class discounttypes {
   varchar(20) name
   varchar(255) description
   integer discounttypeid
}
class images {
   integer modelid
   varchar(255) filename
   bytea filedata
   timestamp uploaddate
   integer imageid
}
class logs {
   integer userid
   integer actiontypeid
   varchar(800) description
   timestamp timestamp
   integer logid
}
class models {
   integer userid
   varchar(100) name
   varchar(800) description
   money price
   integer categoryid
   numeric(3,2) rating
   integer quantityavailable
   integer status
   timestamp dateadded
   integer modelid
}
class modelstatuses {
   varchar(20) name
   varchar(255) description
   integer statusid
}
class orderitems {
   integer orderid
   integer modelid
   integer quantity
   integer orderitemid
}
class orders {
   integer userid
   timestamp orderdate
   money totalamount
   integer statusid
   varchar(50) discountcode
   integer orderid
}
class orderstatuses {
   varchar(20) name
   varchar(255) description
   integer statusid
}
class paymentmethods {
   varchar(50) name
   varchar(255) description
   integer paymentmethodid
}
class payments {
   integer orderid
   money amount
   integer paymentmethodid
   integer paymentstatusid
   timestamp paymentdate
   integer paymentid
}
class paymentstatuses {
   varchar(20) name
   varchar(255) description
   integer statusid
}
class permissions {
   varchar(50) permissionname
   varchar(255) description
   integer permissionid
}
class reviews {
   integer userid
   integer modelid
   varchar(300) text
   integer rating
   timestamp datecreated
   integer reviewid
}
class rolepermissions {
   integer roleid
   integer permissionid
}
class roles {
   varchar(20) rolename
   integer roleid
}
class suspiciousactiontypes {
   varchar(50) actionname
   varchar(300) description
   integer actiontypeid
}
class suspiciouslogs {
   integer userid
   integer actiontypeid
   varchar(800) description
   timestamp timestamp
   integer logid
}
class userimages {
   integer userid
   varchar(255) filename
   bytea filedata
   timestamp uploaddate
   integer imageid
}
class users {
   varchar(50) username
   varchar(100) email
   varchar(255) passwordhash
   integer roleid
   timestamp registrationdate
   integer userid
}

discounts  -->  discounttypes : discounttype:discounttypeid
images  -->  models : modelid
logs  -->  actiontypes : actiontypeid
logs  -->  users : userid
models  -->  categories : categoryid
models  -->  modelstatuses : status:statusid
models  -->  users : userid
orderitems  -->  models : modelid
orderitems  -->  orders : orderid
orders  -->  discounts : discountcode:code
orders  -->  orderstatuses : statusid
orders  -->  users : userid
payments  -->  orders : orderid
payments  -->  paymentmethods : paymentmethodid
payments  -->  paymentstatuses : paymentstatusid:statusid
reviews  -->  models : modelid
reviews  -->  users : userid
rolepermissions  -->  permissions : permissionid
rolepermissions  -->  roles : roleid
suspiciouslogs  -->  suspiciousactiontypes : actiontypeid
suspiciouslogs  -->  users : userid
userimages  -->  users : userid
users  -->  roles : roleid
