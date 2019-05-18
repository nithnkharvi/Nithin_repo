This e-commerce application is built with the help of struts2 and hibernate is used to deal with database.
This java project is created as Dynamic web project named as "Omnicuris_Ekart" and then converted to maven project.
All the dependencies are added in pom.xml file.

The web.xml file contains the struts dispatcher declaration and configuration file name "omnicuris_struts.xml" 
which is located under "{project}/WebContent/WEB-INF/classes/"

The application will start by triggering the below request URL. Here ip address depends on where the war file is deployed.
URL = http://10.18.158.241:8081/Omnicuris_Ekart/order/home.action


As per omnicuris_struts.xml file, the above action will take the user to the home page 
where the indiviadual can perform different operations.

Below are the table with field nameS used by the application.

OC_ITEM (ITEM_NAME, ITEM_ID, ITEM_PRICE, ITEM_COUNT, ITEM_DESC, CREATE_ID) primary key (ITEM_ID);
OC_ORDER (ORDER_ID, ORDER_STATUS, ITEM_ID, ITEM_COUNT, EMAIL_ID) primary key (ORDER_ID, ITEM_ID);
OC_USER (USER_NAME, USER_ID, PASSWORD, EMAIL_ID) primary key (USER_ID);

The hibernate configuration file is placed under the java source folder as "{proect}/src/main/java/oc.hibernate.cfg.xml"
The database can be changed by modifying above configuration file.

This file contains the model class which is annotated with table fields.
Also this file contains the named query file declaration as "oc.named-queries.hbm.xml" which contains named sql query definitions.

The database connection related logic is written in DbConnectionUtil class which can be used to get the hibernate session.
The database CRUD operation logic is implemented in ItemManageDAOImpl class which uses DAO design pattern for code reusability.


Below are the API built to support the operations.

1) CRUD operation on Items.
A)Creating the item.
Once the user adds item (item is taken as one per operaion for simplicity), the ajax call can be made with action name as "addItem" 
and need to pass the the data to BusinessContext as json object. Struts supports the Object backed method to pass the object to action API.
As per cml file, the addItemToOmnicuris() method from OmnicurisItemManageAction API is called. This logic will get the first item and send it to DAO class.
The DAO class uses the entity class to save the record into oc_item using the session.

B)Querying the item.
The "getItem" query action will call the getItemFromOmnicuris() method from OmnicurisItemManageAction API. In this case, the query is performed using
named sql query which is defined in "oc.named-queries.hbm.xml". The condition value is set into the query object and list of item is returned.
This items will be set into BusinessContext. As the result type is json, this object will be returned as json object to web page.

C) Updating the item.
The web page sends the updated value in action "updateItem" which uses similar logic like add operaion.

D) Deleting the item.
The "deleteItem" action sends the item which needs to be deleted having the item_id. The session object sends the item object and deletes the records 
using Sesssion.delete() function.


2) Get all the items.
The web page sends the "getAllItem" action having no parameters to the "AllOcItemManageAction" API. This busineess logic uses the same logic as 
(B)Quuery operation,  but with different named sql query.


3) Ordering the items.
The putOrder action can be used to do the single or bulk ordering. If bulk ordering is done, then all the items will have same order_id for that user.
Example: Person A is ordering item B (10), C(4) and D(6), then the order will be saved in oc_order as shown below.
A_order_id , B_item_id , 10 , A_email_id
A_order_id , C_item_id , 4 , A_email_id
A_order_id , D_item_id , 6 , A_email_id

So, the request sends the order object to putOrder action. The CustomerOrderManageAction API will send the order object to DAO class 
which checks for item availability. If the items are available, then the order records will be inserted. Otherwise, the OutOfStockException will be thrown.


4) Get All orders.
The "getAllOrder" action returns the List of order object which internally can have list of items done. The query will fetch the records from oc_order
based on order_id ordering. So, that the same order_id can be matched and items list is set into one object.
