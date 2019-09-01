# websockets
Manage stopwatches via websocket messages.

## run
To start a local wildfly container and deploy the project run
```
mvn -Prun
```
or use the supplied run configuration for IntelliJ.

The endpoint can be accessed at 
```
ws://localhost:8080/websockets/timer-info
``` 
after start.