package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PostgreSQLConnection;
import model.Students;
import model.Teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRepository {
    public static ObservableList<Teachers> getDataTeachers() {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;
        ObservableList<Teachers> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u join teachers t on u.userid = t.userid";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Teachers(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("teacherid"), resultSet.getInt("departmentid"), resultSet.getInt("years_of_teaching")));
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

    public static ObservableList<Teachers> getDataTeachersByCourse(int courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;
        ObservableList<Teachers> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u JOIN teachers t ON t.userid = u.userid JOIN teachers_courses tc on tc.teacherid = t.teacherid JOIN courses c ON c.courseid = tc.courseid WHERE c.courseid = " + courseid;
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Teachers(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("teacherid"), resultSet.getInt("departmentid"), resultSet.getInt("years_of_teaching")));
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

    public static ObservableList<Teachers> getDataTeachersOfClass(int classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Teachers> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM \"users\" u JOIN teachers t ON t.userid = u.userid JOIN teachers_classes tc ON tc.teacherid = t.teacherid WHERE tc.classid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Teachers(resultSet.getInt("userid"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("usertype"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getInt("teacherid"), resultSet.getInt("departmentid"), resultSet.getInt("years_of_teaching")));
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

    public String joinCourse(Integer userid, Integer courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        int rowsAffected = 0;

        String query = "INSERT INTO \"teachers-courses\" (teacherid, courseid) VALUES (" + userid + ", " + courseid + ");";
        try {
            preparedStatement = connection.prepareStatement(query);
            rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected + "Rows Affected. Succes!";
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
        return "error";
    }

    public String updateGrade(Integer studentid, Integer grade, Integer courseid) throws SQLException {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        int cellsUpdated = 0;
        try {
            String query = "UPDATE enrollment SET grade = " + grade + " WHERE studentid = " + studentid + " AND courseid = " + courseid;

            preparedStatement = connection.prepareStatement(query);

            cellsUpdated = preparedStatement.executeUpdate();

            return cellsUpdated + "Cells Updated. Success!";
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
                return cellsUpdated + "Cells Updated. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }
        return null;
    }

    public boolean existTeacherInCourse(int courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT COUNT(*) AS nrTeachers FROM teachers_courses WHERE courseid = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("nrTeachers") > 0;
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
        return false;
    }
}
