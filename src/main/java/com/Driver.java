package com;


import out.PersonDetails;

public class Driver {

    public static void main(String[] args) {
        System.out.println("Hi");

        PersonDetails.Person.Builder p = PersonDetails.Person.newBuilder();
        p.setName("Tony");
        p.build();


        printDetails(p);
    }

    public static void printDetails(PersonDetails.Person.Builder p){
        System.out.println(p.getName());
    }
}
