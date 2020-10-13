Entorno:
Glassfish 4.1
JDK 1.8

Script de creación de tablas
db/airbnb.sql

Llamadas al WS:
postman/postman.json

Configuración glassfish:
    <jdbc-connection-pool driver-classname="com.mysql.jdbc.Driver" name="ping" res-type="java.sql.Driver">
      <property name="portNumber" value="3306"></property>
      <property name="dataBase" value="airbnb"></property>
      <property name="password" value="mavha"></property>
      <property name="user" value="mavha"></property>
      <property name="url" value="jdbc:mysql://localhost:3306/airbnb"></property>
    </jdbc-connection-pool>
    <jdbc-resource pool-name="ping" jndi-name="jdbc/airbnb"></jdbc-resource>
    
    