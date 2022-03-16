# MongoDB Spring Batch Examples

This repo contains a very primitive example of using Spring Batch to transform a data from a relation database into MongoDB documents.
It is essentially the same as the previous example, but with the underlying data in a relational database vs a csv file.

## How does it work
This example is very simple. It takes data from a relational database with dogs, runs a basic transformation, and puts the output into a MongoDB instance.
We then run a listener to print out the results that were inserted.

Please view the Spring Batch tutorial mentioned earlier for a detailed analysis of components. 

## Getting Started
Clone this repo, and run a `mvn clean install`

The main class to run the transformation is `com.sbrown52.examples.mongodbspringbatch.MainApplication`.
Either run this from your IDE, or build an executable jar file and run it use standard Java commands.

## The underlying data
In order to make this example easy to run, we store data in an in-memory h2 database. When the application starts up, it will
run the script `populate-database.sql` and insert records which will then be transformed. Feel free to edit this as required, or 
point to another database you have running locally.
