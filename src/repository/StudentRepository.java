package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PostgreSQLConnection;
import model.Students;
import model.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {


    public static ObservableList<Enrollment> getDataStudentCourse(int studentID) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Enrollment> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM students s JOIN enrollment e ON s.studentid = e.studentid JOIN courses c ON e.courseid = c.courseid JOIN users u on s.userid = u.userid WHERE s.userid = " + studentID;

            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(new Enrollment(rs.getInt("courseid"), rs.getInt("number_of_credits"), rs.getInt("grade"), rs.getString("course_name"), rs.getString("course_code"), rs.getString("student_registration_number"), rs.getString("firstname"), rs.getString("lastname")));
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

    public static ObservableList<Enrollment> getDataStudentsCourses(Integer teacherid, String courseName) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Enrollment> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM enrollment e JOIN students s ON s.studentid = e.studentid JOIN teachers t ON t.teacherid = e.teacherid JOIN users u ON s.userid = u.userid JOIN courses c ON c.courseid = e.courseid WHERE t.teacherid = ? AND c.course_name = ? ORDER BY u.lastname";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setString(2, courseName);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(new Enrollment(rs.getInt("courseid"), rs.getInt("number_of_credits"), rs.getInt("grade"), rs.getString("course_name"), rs.getString("course_code"), rs.getString("student_registration_number"), rs.getString("firstname"), rs.getString("lastname")));
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

    public static ObservableList<Students> getDataStudents() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Students> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u JOIN students s on u.userid = s.userid";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Students(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("studentid"), resultSet.getInt("classid"), resultSet.getString("student_registration_number")));
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

    public static ObservableList<Students> getDataStudentsNotInClass() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Students> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u JOIN students s on u.userid = s.userid WHERE s.classid IS NULL";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Students(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("studentid"), resultSet.getInt("classid"), resultSet.getString("student_registration_number")));
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

    public static ObservableList<Students> getDataStudentsInClass(int classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Students> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u JOIN students s on u.userid = s.userid WHERE s.classid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Students(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("studentid"), resultSet.getInt("classid"), resultSet.getString("student_registration_number")));
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

    public int getStudentIDbyNumber(String studentNumber) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        int studentid = 0;
        String query = "SELECT studentid FROM students WHERE student_registration_number = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentNumber);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                studentid = rs.getInt("studentid");
            }

            return studentid;

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
