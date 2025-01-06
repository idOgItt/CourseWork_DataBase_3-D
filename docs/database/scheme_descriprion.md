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
class total {
   money sum
}
class users {
   varchar(50) username
   varchar(100) email
   varchar(255) passwordhash
   integer roleid
   timestamp registrationdate
   integer userid
}
class v_discounts_analysis {
   varchar(50) discountcode
   money discountamount
   varchar(20) discounttype
   timestamp with time zone startdate
   timestamp with time zone enddate
   integer usagelimit
   bigint ordersusingdiscount
}
class v_models_popularity {
   integer modelid
   varchar(100) modelname
   varchar(100) category
   bigint totalsold
   bigint totalorders
}
class v_order_details {
   integer orderid
   varchar(50) user
   timestamp orderdate
   varchar(20) status
   varchar(100) modelname
   integer quantity
   money linetotal
}
class v_orders_summary {
   integer orderid
   varchar(50) user
   varchar(20) status
   money totalamount
   varchar(50) discountcode
   text discountdetails
   timestamp orderdate
}
class v_reviews_summary {
   integer reviewid
   varchar(50) reviewer
   varchar(100) modelname
   varchar(300) reviewtext
   integer rating
   timestamp reviewdate
}
class v_user_statistics {
   integer userid
   varchar(50) username
   varchar(100) email
   bigint totalorders
   money totalspent
   bigint totalreviews
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
