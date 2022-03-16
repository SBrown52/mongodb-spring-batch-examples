# MongoDB Spring Batch Examples

This repo contains some basic examples of using Spring Batch with MongoDB.
Check each subdirectory for the various examples.

NB: Each directory contains a complete example. No code is shared between examples in order to make it simpler to understand and run.

## Examples
* [1_csv_to_mongodb](./1_csv_to_mongodb)
* [2_rbdms_to_mongodb](./2_rdbms_to_mongodb)

## MongoDB Usage
Obviously, You will need an instance of MongoDB running! By default this project connects to a local MongoDB instance on port `27111` (NB: non-standard, you may want to update this).
Alternatively, you can create an [MongoDB Atlas instance](https://www.mongodb.com/atlas/database) and connect to it.

### Configuration
All configuration is in the `application.yaml` files. You can can edit the host, port add security etc for your MongoDB cluster here.


### Atlas Setup
If you are using Atlas, you will need to set the following in `application.yaml`:
```
spring:
  data:
    mongodb:
      uri: mongodb+srv://user:pass@atlasurl.mongodb.net
```
You can get your Atlas URI from the Atlas console.

## Why is there a relational database in here?
Spring Batch uses a relational database to store job metadata. This allows it to keep track of progress, and can be used
to restart or rerun jobs in case of failure. Although you do not need to specify any logic for this to work (by default Spring
will handle it all for you), we purposefully configure a H2 instance to store this data. You can then query the H2 instance and
see what records it holds

## Performance
This project, so far, is designed to show examples on how to use Spring Batch with MongoDB, it does not try to write the
efficient or performant jobs. You will need to read the Spring Batch doc and make those changes on your own system.

## Official Spring Guides
The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

