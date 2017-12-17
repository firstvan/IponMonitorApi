# IponMonitorApi

### Követelmények:
- Maven

### Fordítás:
mvn clean package

### Futtatás:
java -jar target\IponMonitor.jar

### Séma generálás:
mvn jaxb2:schemagen

### Séma (generálás után):
IponMonitor\target\schemagen-work\compile_scope\schema1.xsd

### Végpontok:
Monitor keresése márka és típus alapján: http://localhost:8888/monitorSearch?name={márka}&type={típus}
