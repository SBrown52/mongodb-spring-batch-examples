# MongoDB Spring Batch Examples

This repo contains a very primitive example of using Spring Batch to transform a csv file into MongoDB entries. It is heavily
based on the [Spring Batch tutorial](https://spring.io/guides/gs/batch-processing/), with a few small modifications - primarily to
write data to MongoDB as an output, not a relational database. We will not go into details about Spring Batch or MongoDB here.

## How does it work
This example is very simple. It takes a CSV file containing information on dogs, runs a basic transformation, and puts the output into a MongoDB instance.
We then run a listener to print out the results that were inserted.

Please view the Spring Batch tutorial mentioned earlier for a detailed analysis of components. 

## Getting Started
Clone this repo, and run a `mvn clean install`

The main class to run the transformation is `com.sbrown52.examples.mongodbspringbatch.MainApplication`.
Either run this from your IDE, or build an executable jar file and run it use standard Java commands.

You will need an instance of MongoDB running! By default this project connects to a local MongoDB instance on port `27111` (NB: non-standard, you may want to update this).
Alternatively, you can create an [MongoDB Atlas instance](https://www.mongodb.com/atlas/database) and connect to it.

## Why is there a relation database in here?
Spring Batch uses a relational database to store job metadata. This allows it to keep track of progress, and can be used
to restart or rerun jobs in case of failure. Although you do not need to specify any logic for this to work (by default Spring 
will handle it all for you), we purposefully configure a H2 instance to store this data. You can then query the H2 instance and 
see what records it holds

## Official Spring Guides
The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

