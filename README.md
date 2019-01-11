## CMPT 431: Distributed Systems (Assignment 1, Spring 2019)

### How to run

1. `cd` to folder `remote-method-invocation`
2. open 3 terminals

In the 1st terminal type
```Shell
$ javac *.java
$ rmiregistry # for linux or Mac
# or use 
# $ start rmiregistry 
# for Windows
```

In the 2nd terminal type
```Shell
$ java Server
```

In the 3rd terminal type
```Shell
$ java Client
```

As expected, in `server` terminal,
```shell
Server unicasted current timestamp
8:52:20:301 
```

in `client` terminal,
```shell
Server timestamp received.
8:52:20:393
```

## Reference
* https://www.tutorialspoint.com/java_rmi/java_rmi_application.htm