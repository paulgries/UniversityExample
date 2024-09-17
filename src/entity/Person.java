package entity;

import java.util.Arrays;

/**
 * A person at the UofT.
 */
public class Person implements Comparable<Person> {

    /**
     * The person's name (family name last).
     */
    private final String[] name;

    /**
     * The person's UTORid
     */
    private String utorid;

    /**
     * Initialize this Person named name with UTORid utorid.
     *
     * @param name   the person's name (family name last)
     * @param utorid the person's UTORid
     */
    public Person(String[] name, String utorid) {
        // Deep copy mutable parameter.
        this.name = Arrays.copyOf(name, name.length);
        this.utorid = utorid;
    }

    public String getUtorid() {
        return utorid;
    }

    public String[] getName() {
        // Try not to create aliases!
        return Arrays.copyOf(name, name.length);
    }

    /**
     * Return a string representation of this person with this format:
     * 'last name, other names: utorid'
     *
     * @return a string representation of this person
     */
    public String toString() {
        return this.formatName() + ": " + this.utorid;
    }

    /**
     * Return the name formatted as a str. The last name is first, then a
     * comma, then the rest of the names separated by spaces.
     *
     * @return the name formatted as a str.
     */
    public String formatName() {
        String formattedName = this.name[name.length - 1] + ",";
        int i = 0;
        while (i != this.name.length - 1) {
            formattedName = formattedName + " " + this.name[i];
            i += 1;
        }

        return formattedName;
    }

    /**
     * Change this person's last name to newLast.
     *
     * @param newLast the new last name.
     */
    public void changeLastName(String newLast) {
        this.name[name.length - 1] = newLast;
    }

    @Override
    public int compareTo(Person o) {
        return this.formatName().compareTo(o.formatName());
    }
}