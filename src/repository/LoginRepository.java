package repository;

import model.PostgreSQLConnection;
import model.Students;
import model.Teachers;
import model.Users;

import java.sql.*;
import java.util.Objects;

public class LoginRepository {
    public int validateLogin(String username, String password) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        try {

            String query = "SELECT * FROM \"users\" WHERE \"users\".username = \'" + username + "\' AND \"users\".password = \'" + password + "\'";

            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("userid");
            } else {
                return -1;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -2;
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
                return -2;
            }
        }
    }

    public String getUserType(int userid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM \"users\" WHERE \"users\".userid = " + userid;

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet userTypeSet = preparedStatement.executeQuery();

            if (userTypeSet.next()) {
                String uType = userTypeSet.getString("usertype");
                return uType;
            } else return "Login Failed!";

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

        return "login failed";
    }

    public Users getUser(int userid, String usertype) throws SQLException {
        Users user = null;
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        if (Objects.equals(usertype, "student")) {
            String query = "SELECT * from \"users\" u JOIN students s on u.userid = s.userid WHERE s.userid = " + userid;
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new Students(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("usertype"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("studentid"), rs.getInt("classid"), rs.getString("student_registration_number"));
            }
        } else if (Objects.equals(usertype, "teacher")) {
            String query = "SELECT * from \"users\" u JOIN teachers t on u.userid = t.userid WHERE t.userid = " + userid;
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new Teachers(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("usertype"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("teacherid"), rs.getInt("departmentid"), rs.getInt("years_of_teaching"));
            }
        }
        return user;
    }

    public String changePassword(Integer userid, String password) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        int cellsUpdated = 0;
        try {
            String query = "UPDATE \"users\" SET password = ? WHERE userid = " + userid;

            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);

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
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("eroare baza de date");
            }
        }
        return "error";
    }
}
