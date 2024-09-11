package gr.aueb.cf.schoolapp.model;

public class Teacher {
    private Integer id;
    private String firstname;
    private String lastname;


    public Teacher() {
    }

    public Teacher(Integer teacherId, String firstname, String lastname) {
        this.id = teacherId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer teacherId) {
        this.id = teacherId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
