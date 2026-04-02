# Postgresql

## Docker creation

Using custom script postgreCreate:

```shell
postgreCreate 15-cv spring-7-rest-mvc_database C:\Projekty\edu\spring-beginner-to-guru\spring-7-rest-mvc\src\scripts\:/opt/scripts
```

## Script execution

```shell
psql -U postgres -f /opt/scripts/postgresql-init.sql
psql -U postgres -d restdb -f /opt/scripts/postgresql-schema-init.sql
```
