## CMPT 431: Distributed Systems (Assignment 1, Spring 2019)

## How to run
At the server side:
```Shell
javac *.java
# linux
rmiregistry
# or windows
start rmiregistry
# get the server address and set the IP_ADRESS variable in Serevr.java
java Server
```

At the client side (windows OS only):
```shell
javac *.java
start rmiregistry
# get the server address and set the IP_ADRESS variable in Client.java
java Client
```

## Reference
* https://www.tutorialspoint.com/java_rmi/java_rmi_application.htm