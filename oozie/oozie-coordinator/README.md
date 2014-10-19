Sample Oozie coordinator action with Pig
---------
Shows Pig action execution with formatted CURRENT_DATE parameter

Setup Oozie app directories (if necessary)
```bash
    $ sudo -u hdfs hdfs dfs -mkdir -p /apps/oozie/workflow
    $ sudo -u hdfs hdfs dfs -mkdir /apps/oozie/coordinator
    $ sudo -u hdfs hdfs dfs -chown -R oozie:hdfs /apps/oozie/
    $ sudo -u hdfs hdfs dfs -chmod -R 777 /apps/oozie/
```

Setup app directories (login with user that will execute workflows, e.g. your username)
```bash
    $ su <your username>
    $ hdfs dfs -mkdir /apps/oozie/workflow/coord-test
    $ hdfs dfs -mkdir /apps/oozie/coordinator/coord-test
```

Upload worklow app
```bash
    $ hdfs dfs -put workflow.xml /apps/oozie/workflow/coord-test
    $ hdfs dfs -put filter.pig /apps/oozie/workflow/coord-test
```

Upload coordinator app
```bash
    $ hdfs dfs -put coordinator.xml /apps/oozie/coordinator/coord-test
```

Upload sample data to input path (should be the user home directory of the user submitting the Oozie job)
```bash
    $ hdfs dfs -mkdir /user/<your username>/coord-test-in/20141013
    $ hdfs dfs -put indata.csv /user/<your username>/coord-test-in/20141013
```

Setup config.xml - Modify the nameNode and jobTracker uri's accordingly

Run it
```bash
    $ export OOZIE_URL=http://<oozie-host>:11000/oozie
    $ oozie job -run -config config.xml
```
