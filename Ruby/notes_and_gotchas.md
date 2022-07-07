## Gotcha's

### Error starting userland proxy: listen tcp4 0.0.0.0:2181: bind: address already in use

* If you try to `docker-compose up` (e.g.: when switching labs) and get an error like:

    ```
    $ docker-compose up
    Starting lab4_zookeeper_1 ... 
    Starting lab4_zookeeper_1 ... error

    ERROR: for lab4_zookeeper_1  Cannot start service zookeeper: driver failed programming external connectivity on endpoint lab4_zookeeper_1 (e58525bc89e7159e3a361ee3499a5b345792772bc7beb807ca96c693859a40f5): Error starting userland proxy: listen tcp4 0.0.0.0:2181: bind: address already in use

    ERROR: for zookeeper  Cannot start service zookeeper: driver failed programming external connectivity on endpoint lab4_zookeeper_1 (e58525bc89e7159e3a361ee3499a5b345792772bc7beb807ca96c693859a40f5): Error starting userland proxy: listen tcp4 0.0.0.0:2181: bind: address already in use
    ERROR: Encountered errors while bringing up the project.
    ```

  * Solution:

    ```
    $ sudo netstat -ltnp | grep 2181
    tcp6       0      0 :::2181                 :::*                    LISTEN      1568/java   
    $ sudo kill 1568
    
    ```