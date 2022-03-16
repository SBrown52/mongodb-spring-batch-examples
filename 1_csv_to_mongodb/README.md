# MongoDB Spring Batch Examples

This repo contains a very primitive example of using Spring Batch to transform a csv file into MongoDB documents. It is heavily
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

## The underlying data
In order to make this example easy to run, we provide some sample data, conveniently placed in a file called `sample-data.csv`
Feel free to edit or modify this as required.