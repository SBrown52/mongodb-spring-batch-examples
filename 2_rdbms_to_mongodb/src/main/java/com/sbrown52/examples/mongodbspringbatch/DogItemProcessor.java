package com.sbrown52.examples.mongodbspringbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class DogItemProcessor implements ItemProcessor<Dog, Dog> {

    private static final Logger log = LoggerFactory.getLogger(DogItemProcessor.class);

    @Override
    public Dog process(final Dog dog) throws Exception {
        final String newName = dog.getName().toUpperCase();

        // We could do more transformation here if we need
        final String breed = dog.getBreed();
        final String colour = dog.getColour();
        final int age = dog.getAge();

        final Dog transformedDog = new Dog(newName, age, breed, colour);

        log.info("Converting ({}) into ({})", dog, transformedDog);

        return transformedDog;
    }

}