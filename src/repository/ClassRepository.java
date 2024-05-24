package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import model.Classes;
import model.Departments;
import model.PostgreSQLConnection;
import model.Students;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassRepository {
    public static ObservableList<String> getClassName() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        ObservableList<String> list = FXCollections.observableArrayList();

        String query = "SELECT * FROM classes ORDER BY class_name";
        try {
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new String(resultSet.getString("class_name")));
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

    public static ObservableList<Classes> getDataClasses() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Classes> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM classes";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Classes(resultSet.getInt("classid"), resultSet.getString("class_code"), resultSet.getString("class_name")));
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

    public static ObservableList<Students> getStudentsInClassOfStudent(int studentid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        ObservableList<Students> list = FXCollections.observableArrayList();

        String query = "SELECT * FROM users u JOIN students s ON u.userid = s.userid JOIN classes c ON s.classid = c.classid WHERE s.classid IN (SELECT classid FROM students WHERE studentid = ?) ORDER BY s.studentid";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);
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

    public Integer getClassByName(String className) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM classes WHERE class_name = ? ORDER BY class_name";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, className);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("classid");
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
        return -1;
    }

    public String getNameOfClass(int classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT class_name FROM classes WHERE classid = ? ORDER BY class_name";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classid);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("class_name");
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
        return "Error";
    }

    public ObservableList<String> getNameOfClassForTeacher(int teacherid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<String> list = FXCollections.observableArrayList();
        String query = "SELECT class_name FROM classes WHERE class_name NOT IN (SELECT class_name FROM classes c JOIN teachers_classes tc on tc.classid = c.classid WHERE teacherid = ?) ORDER BY class_name";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new String(resultSet.getString("class_name")));
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


}
