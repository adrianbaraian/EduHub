package model;

public class Teachers extends Users {
    private int teacherid;
    private int departmentid, years_of_teaching;

    public Teachers(int userid, String username, String password, String usertype, String firstname, String lastname, int teacherid, int departmentid, int years_of_teaching) {
        super(userid, username, password, usertype, firstname, lastname);
        this.teacherid = teacherid;
        this.departmentid = departmentid;
        this.years_of_teaching = years_of_teaching;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getYears_of_teaching() {
        return years_of_teaching;
    }

    public void setYears_of_teaching(int years_of_teaching) {
        this.years_of_teaching = years_of_teaching;
    }
}
