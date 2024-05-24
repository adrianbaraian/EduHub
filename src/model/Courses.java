package model;

public class Courses {
    private int courseid, number_of_credits, departmentid;
    private String course_name, course_description, course_code;

    public Courses(int courseid, int number_of_credits, String course_name, String course_description, String course_code, int departmentid) {
        this.courseid = courseid;
        this.number_of_credits = number_of_credits;
        this.course_name = course_name;
        this.course_description = course_description;
        this.course_code = course_code;
        this.departmentid = departmentid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getNumber_of_credits() {
        return number_of_credits;
    }

    public void setNumber_of_credits(int number_of_credits) {
        this.number_of_credits = number_of_credits;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }
}