Sample Oozie Sqoop Action to HCatalog
---------
Shows Sqoop action execution. Sqoop from MySQL database table to Hive table with partition on a column.

Tried using dynamic hive partitions with the --hive-import option and it does not work. You have to use HCatalog. Other issues:

1. Need to manually copy /etc/hive/conf/hive-site.xml to /apps/share and reference in your workflow.xml - HCatalog will otherwise complain that the destination table does not exist.
2. sqoop import needs to include the --skip-dist-cache option - fix error with "path not found" business
3. mysql jdbc connector needs to be added to the sharelib - "oozie-setup.sh" handles this - [oozie-setup.sh](setup/oozie-setup.sh)

MySQL -> Sqoop -> Hive table with partition

Setup the sample data, application, MySQL database, and Hive table
```bash
    $ cd setup
    $ python makedata.py
    $ ./setup.sh
```

And to run the job
```bash
    $ cd <project-home>
    $ ./run.sh
```

