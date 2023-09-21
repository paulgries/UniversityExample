package data_access;


import entity.Person;
import entity.Student;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UniversityDataAccessObject {

    private final File personFile; // Data is saved here
    private final File studentFile;

    // UTORid to Person
    private final Map<String, Person> persons = new HashMap<>(); // Data is stored here
    // while program is running so it doesn't need to be retrieved from the file every time

    /**
     * Read the Person and Student objects in CSV files named personFilename and studentFilename.
     *
     * @param personFilename  the Person CSV file
     * @param studentFilename the Student CSV file
     * @throws IOException when there is an error reading either file
     */
    public UniversityDataAccessObject(String personFilename, String studentFilename) throws IOException {
        this.personFile = new File(personFilename); // Creates the file
        this.studentFile = new File(studentFilename);

        if (this.personFile.exists()) {
            readPersonFile(personFile); // Tries to read the files, if they exist
        }

        if (this.studentFile.exists()) {
            readStudentFile(studentFile);
        }
    }

    private void readStudentFile(File studentFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.studentFile))) {

            String row;
            while ((row = reader.readLine()) != null) { // Will read until end of file is reached
                // "lastfirs,First Middle Last"
                String[] cols = row.split(",");
                String utorid = cols[0];
                String saveName = cols[1];
                String[] names = saveName.split(" ");
                String studentNumber = cols[2];

                Person p = new Student(names, utorid, studentNumber);
                persons.put(utorid, p);
            }
        }
    }

    private void readPersonFile(File personFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.personFile))) {

            String row;
            while ((row = reader.readLine()) != null) {
                // "lastfirs,First Middle Last"
                String[] cols = row.split(",");
                String utorid = cols[0];
                String saveName = cols[1];
                String[] names = saveName.split(" ");

                Person p = new Person(names, utorid);
                persons.put(utorid, p);
            }
        }
    }

    public void save(Person person) {
        persons.put(person.getUtorid(), person);
        this.save();
    }

    private void save() {
        try {
            BufferedWriter personWriter = new BufferedWriter(new FileWriter(personFile));
            BufferedWriter studentWriter = new BufferedWriter(new FileWriter(studentFile));
            for (Person person : persons.values()) {

                String saveName = String.join(" ", person.getName());

                if (person instanceof Student) { // Instead of checking which type of person is
                    // stored, we could just use polymorphism and create a toString method for
                    // both types
                    // But, we would have to change class every time we want to change format.
                    Student s = (Student) person;
                    String line = "%s,%s,%s".formatted(person.getUtorid(), saveName, s.getStudentID());
                    studentWriter.write(line);
                    studentWriter.write("\n");
                } else { // if the person is not a student, the format must be changed and
                    // we do not attempt to get the student ID
                    String line = "%s,%s".formatted(person.getUtorid(), saveName);
                    personWriter.write(line);
                    personWriter.write("\n");
                }
            }

            personWriter.close();
            studentWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Person get(String utorid) {
        return persons.get(utorid);
    }

}
