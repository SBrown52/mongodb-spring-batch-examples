package com.sbrown52.examples.mongodbspringbatch;

public class Dog {

    private String name;
    private int age;
    private String breed;
    private String colour;

    public Dog() {
    }

    public Dog(String name, int age, String breed, String colour) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", age: " + age +
                ", breed: '" + breed + '\'' +
                ", colour: '" + colour + '\'' +
                '}';
    }
}
