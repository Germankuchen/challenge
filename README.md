Entorno:<br>
Glassfish 4.1 <br>
JDK 1.8

Script de creación de tablas:<br>
db/airbnb.sql

Llamadas al WS:<br>
postman/postman.json

Configuración glassfish:<br>
<jdbc-connection-pool driver-classname="com.mysql.jdbc.Driver" name="ping" res-type="java.sql.Driver"&gt;<br>
<property name="portNumber" value="3306"&gt;</property&gt;<br>
<property name="dataBase" value="airbnb"&gt;</property&gt;<br>
<property name="password" value="mavha"&gt;</property&gt;<br>
<property name="user" value="mavha"&gt;</property&gt;<br>
<property name="url" value="jdbc:mysql://localhost:3306/airbnb"&gt;</property&gt;<br>
</jdbc-connection-pool&gt;<br>
<jdbc-resource pool-name="ping" jndi-name="jdbc/airbnb"&gt;</jdbc-resource&gt;
    
    