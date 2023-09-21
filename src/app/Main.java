package app; // Data is stored in packages to keep it organized and accessible

import data_access.UniversityDataAccessObject;
import entity.Person;
import entity.Student;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        demoPersonStudent();
        demoDataAccessObject();
    }

    private static void demoPersonStudent() {
        String[] name = {"First", "Middle", "Last"};
        Person p1 = new Person(name, "moogah");
        System.out.println(p1);
        Student s1 = new Student(name, "frooble", "1234567890");
        System.out.println(s1);

        Person[] people = {p1, s1};
        for (Person p : people) {
            System.out.println(p); // What should this print?
        }

        System.out.println(p1);
        System.out.println(s1);
    }

    /**
     * Create a few Person and Student objects and save them.
     */
    private static void demoDataAccessObject() {
        UniversityDataAccessObject universityDAO = null; // We currently have no files.
        // But, this code will create a file to store data.
        try {
             universityDAO = new UniversityDataAccessObject(
                    "person.csv", "student.csv");
        } catch (IOException e) { // Checks if there are issues with reading the file
            System.out.println("Could not read from files." + e);
            System.exit(1);
        }

        // This code is new! We stored the data, now we want to do something with it.
        Person p = universityDAO.get("adele");
        System.out.println(p);

        p.changeLastName("ADELE");
        universityDAO.save(p);
        System.out.println(p);

//        String[] name = "John Jacob Jingleheimer Schmidt".split(" ");
//        Person p1 = new Person(name, "schmidtj");
//
//        name = "Adele".split(" ");
//        Person p2 = new Person(name, "adele");
//
//        universityDAO.save(p1); // save the people to the file
//        universityDAO.save(p2);
//
//        name = "Kiefer William Frederick Dempsey George Rufus Sutherland".split(" ");
//        Student s1 = new Student(name, "suther11", "1111111111");
//
//        name = "Rhianna".split(" ");
//        Student s2 = new Student(name, "rhianna", "222222222");
//
//        universityDAO.save(s1);
//        universityDAO.save(s2);

    }


}
