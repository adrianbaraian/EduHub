package model;

public class Users {
    private int userid;
    private String username, password, usertype, firstname, lastname;

    public Users(int userid, String username, String password, String usertype, String firstname, String lastname) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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
