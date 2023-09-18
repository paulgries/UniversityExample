package data_access;


import entity.Person;
import entity.Student;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class UniversityDataAccessObject {

    private final File personFile;
    private final File studentFile;

    // UTORid to Person
    private final Map<String, Person> persons = new HashMap<>();
    private final Map<String, Student> students = new HashMap<>();

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

            readPersonFile(personFilename);
        }
    }

    private void readPersonFile(String personFilename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(personFilename))) {
            String header = reader.readLine();

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
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(personFile));
            for (Person person : persons.values()) {
                String saveName = String.join(" ", person.getName());
                String line = "%s,%s".formatted(person.getUtorid(), saveName);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Person get(String utorid) {
        return persons.get(utorid);
    }

}
