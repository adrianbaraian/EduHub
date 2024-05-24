package model;

public class Classes {
    private int classid;
    private String class_code, class_name;

    public Classes(int classid, String class_code, String class_name) {
        this.classid = classid;
        this.class_code = class_code;
        this.class_name = class_name;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
