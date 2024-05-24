package model;

public class Enrollment {
    private int courseID, credits, grade;
    private String course_name, course_code, studentNumber, firstname, lastname;

    public Enrollment(int courseID, int credits, int grade, String course_name, String course_code, String studentNumber, String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.courseID = courseID;
        this.credits = credits;
        this.grade = grade;
        this.course_name = course_name;
        this.course_code = course_code;
        this.studentNumber = studentNumber;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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
}
