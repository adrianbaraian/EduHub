package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Courses;
import model.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRepository {
    public static ObservableList<Courses> getDataCourses() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Courses> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM courses ORDER BY courseid";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Courses(resultSet.getInt("courseid"), resultSet.getInt("number_of_credits"), resultSet.getString("course_name"), resultSet.getString("course_description"), resultSet.getString("course_code"), resultSet.getInt("departmentid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return list;
    }

    public static ObservableList<Courses> getDataCoursesByTeacherDepartment(Integer teacherid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Courses> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM courses c join teachers t ON c.departmentid = t.departmentid WHERE t.teacherid = ? ORDER BY c.courseid";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Courses(resultSet.getInt("courseid"), resultSet.getInt("number_of_credits"), resultSet.getString("course_name"), resultSet.getString("course_description"), resultSet.getString("course_code"), resultSet.getInt("departmentid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return list;
    }

    public static ObservableList<Courses> getDataCoursesByTeacher(Integer teacherid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Courses> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM courses c join teachers_courses tc ON c.courseid = tc.courseid WHERE tc.teacherid = ? ORDER BY c.courseid";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Courses(resultSet.getInt("courseid"), resultSet.getInt("number_of_credits"), resultSet.getString("course_name"), resultSet.getString("course_description"), resultSet.getString("course_code"), resultSet.getInt("departmentid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return list;
    }

    public static ObservableList<Courses> getDataCoursesByStudent(Integer studentid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Courses> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM courses c join enrollment e ON c.courseid = e.courseid WHERE e.studentid = ? ORDER BY c.courseid";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Courses(resultSet.getInt("courseid"), resultSet.getInt("number_of_credits"), resultSet.getString("course_name"), resultSet.getString("course_description"), resultSet.getString("course_code"), resultSet.getInt("departmentid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return list;
    }

    public static ObservableList<String> getNameCoursesOfTeacher(int teacherid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<String> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM courses c JOIN teachers_courses tc ON tc.courseid = c.courseid WHERE tc.teacherid = ? ORDER BY c.course_name";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new String(resultSet.getString("course_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return list;
    }

    public Integer getCourseIDbyCode(String course_code) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        Integer courseid = -1;
        String query = "SELECT courseid FROM courses WHERE course_code = ? ORDER BY courseid";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course_code);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                courseid = rs.getInt("courseid");
            }
            return courseid;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eroare baza de date");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }

        return -1;
    }

}
