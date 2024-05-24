package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Departments;
import model.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRepository {
    public static ObservableList<String> getNameDepartments() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        ObservableList<String> list = FXCollections.observableArrayList();

        String query = "SELECT * FROM departments ORDER BY department_name";
        try {
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("department_name"));
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

    public static ObservableList<Departments> getDataDepartments() {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Departments> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM departments ORDER BY departmentid";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Departments(resultSet.getInt("departmentid"), resultSet.getString("department_code"), resultSet.getString("department_name")));
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

    public Integer getDepartmentByName(String departmentName) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM departments WHERE department_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departmentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("departmentid");
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

    public String getDepartmentName(int departmentid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        ObservableList<Departments> list = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM departments WHERE departmentid = ? ORDER BY department_name";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentid);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("department_name");
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

}
