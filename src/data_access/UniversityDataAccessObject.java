package data_access;


import entity.Person;
import entity.Student;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UniversityDataAccessObject {

    private final File personFile;
    private final File studentFile;

    // UTORid to Person
    private final Map<String, Person> persons = new HashMap<>();

    /**
     * Read the Person and Student objects in CSV files named personFilename and studentFilename.
     *
     * @param personFilename  the Person CSV file
     * @param studentFilename the Student CSV file
     * @throws IOException when there is an error reading either file
     */
    public UniversityDataAccessObject(String personFilename, String studentFilename) throws IOException {
        this.personFile = new File(personFilename);
        this.studentFile = new File(studentFilename);

        if (this.personFile.exists()) {
            readPersonFile(personFile);
        }

        if (this.studentFile.exists()) {
            readStudentFile(studentFile);
        }
    }

    private void readStudentFile(File studentFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.studentFile))) {

            String row;
            while ((row = reader.readLine()) != null) {
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

                if (person instanceof Student) {
                    Student s = (Student) person;
                    String line = "%s,%s,%s".formatted(person.getUtorid(), saveName, s.getStudentID());
                    studentWriter.write(line);
                    studentWriter.write("\n");
                } else {
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
