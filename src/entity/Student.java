package entity;

/**
 * A student at the UofT with a student ID.
 */
public class Student extends Person {

    /**
     * This student's student ID.
     */
    private final String studentID;

    /**
     * Initialize this Person named name with UTORid utorid.
     *
     * @param name   the person's name (family name last)
     * @param utorid the person's UTORid
     */
    public Student(String[] name, String utorid, String sid) {
        super(name, utorid);
        this.studentID = sid;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.studentID;
    }

    public void m() {}
}
