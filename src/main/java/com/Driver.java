package com;


import out.PersonDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class Driver {

//    public static final String FILE_PATH = "/Users/tonythomas/Documents/Infomedia/Workspace/Intellij/GoogleProtobuf/src/main/java/file/output.txt";
   public static final String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/file/output.txt";
    public static void main(String[] args) {

        PersonDetails.AddressBook.Builder address = PersonDetails.AddressBook.newBuilder();
        PersonDetails.Person.Builder person1 = PersonDetails.Person.newBuilder();
        person1.setId(1).setName("Tony").setEmail("tony@gmail.com").addNumbers("0460318009").setType(PersonDetails.PhoneType.HOME);
        address.addPeople(person1);

        PersonDetails.Person.Builder person2 = PersonDetails.Person.newBuilder();
        person2.setId(2).setName("Nila").setEmail("nila@gmail.com").addNumbers("0460318010").setType(PersonDetails.PhoneType.WORK);
        address.addPeople(person2);

        PersonDetails.Person.Builder person3 = PersonDetails.Person.newBuilder();
        person3.setId(3).setName("Thanseeh").setEmail("thanseeh@gmail.com").addNumbers("0460318011").setType(PersonDetails.PhoneType.MOBILE);
        address.addPeople(person3);

        address.build();

        PersonDetails.AddressBook buildedAddress = address.build();
        showAddressDetails(buildedAddress);

        printDetails(address.build());

    }

    public static void printDetails(PersonDetails.AddressBook address){
        try {
            FileOutputStream fos = new FileOutputStream(new File(FILE_PATH));
            address.writeTo(fos);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        readData();
    }

    public static void showAddressDetails(PersonDetails.AddressBook add)  {
       List<PersonDetails.Person> people = add.getPeopleList();
       people.stream().forEach(p -> System.out.println(p.getName()));
    }

    public static void readData(){
        try {
            PersonDetails.AddressBook deserialized = PersonDetails.AddressBook.newBuilder().mergeFrom(new FileInputStream(FILE_PATH)).build();
            System.out.println("Reading from file---- ");
            List<PersonDetails.Person> people = deserialized.getPeopleList();
            people.stream().forEach(p-> { System.out.print(p.getId()+ " - " +p.getName()+ " - "+ p.getEmail() + " - "+p.getType()+" \n") ; } );
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
