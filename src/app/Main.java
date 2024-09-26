package app;

import data_access.UniversityDataAccessObject;
import entity.Person;
import entity.Student;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String[] name = {"First", "Middle", "Last"};
        Person p1 = new Person(name, "moogah");
        System.out.println(p1); // this calls p1.toString()
        ((Student) p1).m();
        Student s1 = new Student(name, "frooble", "1234567890");
        System.out.println(s1);

        Person[] people = {p1, s1};
        for (Person p : people) {
            System.out.println(p); // What should this print?
        }

        s1.changeLastName("NewLast");
        System.out.println(p1);
        System.out.println(s1);

        UniversityDataAccessObject universityDAO = null;
        try {
            universityDAO = new UniversityDataAccessObject(
                    "person.csv", "student.csv");
        } catch (IOException e) {
            System.out.println("Could not read from files." + e);
        }

        // Save the Persons one at a time
        for (Person p : people) {
            universityDAO.save(p);
        }

        // Retrieve a Person
        Person p = universityDAO.get("moogah");
        System.out.println(p);
        Person s = universityDAO.get("frooble");
        System.out.println(s);
        System.out.println(s.getUtorid());
    }

}
