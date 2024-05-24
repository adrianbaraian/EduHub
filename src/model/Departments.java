package model;

public class Departments {
    private int departmentid;
    private String department_code, department_name;

    public Departments(int departmentid, String department_code, String department_name) {
        this.departmentid = departmentid;
        this.department_code = department_code;
        this.department_name = department_name;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
