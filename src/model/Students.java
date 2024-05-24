package model;

public class Students extends Users {
    private int studentid, classid;
    private String student_registration_number;

    public Students(int userid, String username, String password, String usertype, String firstname, String lastname, int studentid, int classid, String student_registration_number) {
        super(userid, username, password, usertype, firstname, lastname);
        this.studentid = studentid;
        this.student_registration_number = student_registration_number;
        this.classid = classid;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudent_registration_number() {
        return student_registration_number;
    }

    public void setStudent_registration_number(String student_registration_number) {
        this.student_registration_number = student_registration_number;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }
}
