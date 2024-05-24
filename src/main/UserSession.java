package main;

import model.Students;
import model.Teachers;

public final class UserSession {
    private static Students students;
    private static Teachers teachers;
    private static UserSession instance;

    private UserSession() {
    }
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static void clear() {
        students = null;
        teachers = null;
    }

    public Students getStudent() {
        return students;
    }

    public void setStudent(Students students) {
        UserSession.students = students;
    }

    public Teachers getTeacher() {
        return teachers;
    }

    public void setTeacher(Teachers teachers) {
        UserSession.teachers = teachers;
    }
}
