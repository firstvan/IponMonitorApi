# IponMonitorApi

### Leírás:
Ez a program xml/json mimeType-ú végpontokat szolgál a http://ipon.hu/ oldal monitor termékeihez.

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

Monitorok listázása alapértelmezett termék megjelenítési szám szerint: http://localhost:8888/monitorList

Az első {darab} monitor megjelenítése: http://localhost:8888/monitorList/{darab}

A {tól} {darab} monitor megjelenítése: http://localhost:8888/monitorList/{darab}?from={tól}

50 darab monitor megjelenítése {oldalszám} szerint: http://localhost:8888/monitorList?page={oldalszám}
