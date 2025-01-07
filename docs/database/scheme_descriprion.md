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
   varchar(20) discounttype
   timestamp with time zone startdate
   timestamp with time zone enddate
   integer usagelimit
   integer timesused
   integer discountid
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
   varchar(20) status
   timestamp dateadded
   integer modelid
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
   varchar(20) status
   varchar(50) discountcode
   integer orderid
}
class payments {
   integer orderid
   money amount
   varchar(50) paymentmethod
   timestamp paymentdate
   integer paymentid
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
class users {
   varchar(50) username
   varchar(100) email
   varchar(255) passwordhash
   integer roleid
   timestamp registrationdate
   integer userid
}

logs  -->  actiontypes : actiontypeid
logs  -->  users : userid
models  -->  categories : categoryid
models  -->  users : userid
orderitems  -->  models : modelid
orderitems  -->  orders : orderid
orders  -->  discounts : discountcode:code
orders  -->  users : userid
payments  -->  orders : orderid
reviews  -->  models : modelid
reviews  -->  users : userid
rolepermissions  -->  permissions : permissionid
rolepermissions  -->  roles : roleid
suspiciouslogs  -->  suspiciousactiontypes : actiontypeid
suspiciouslogs  -->  users : userid
users  -->  roles : roleid
