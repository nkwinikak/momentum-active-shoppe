This application was built with the following components : 
    Database : h2 in memory db
    monitoringTool : prometheus

Swagger Doc : http://localhost:9090/momentum-active-shoppe/swagger-ui.html#/
h2-In-memory-db url : http://localhost:9090/momentum-active-shoppe/h2-console/

MonitoringTool :
    Prometheus url : http://localhost:9090/momentum-active-shoppe/actuator/prometheus

h2-In-memory-db:
    userName : sa
    password : password
    
ApplicationStartUp : java -jar target/momentum-active-shoppe-1.0.1-SNAPSHOT.jar --spring.config.location=classpath:application.yml
