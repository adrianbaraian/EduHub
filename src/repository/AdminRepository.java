package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;

public class AdminRepository {
    public String insertStudent(String username, String password, String firstname, String lastname, String studentNo, int classid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = PostgreSQLConnection.makeConnection();

            String query1 = "INSERT INTO \"users\" (username, password, usertype, firstname, lastname) VALUES (?, ?, \'student\', ?, ?);";
            String query3 = "SELECT * FROM \"users\" WHERE \"users\".username = ? AND \"users\".password = ?";
            preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            preparedStatement = connection.prepareStatement(query3);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer userid = resultSet.getInt("userid");
                String query2 = "INSERT INTO students (student_registration_number, userid, classid) VALUES (?, " + userid + ", ?);";
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setString(1, studentNo);
                preparedStatement.setInt(2, classid);

                rowsAffected += preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String insertTeacher(String username, String password, String firstname, String lastname, int departmentid, int yearsOfTeaching) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = PostgreSQLConnection.makeConnection();

            String query1 = "INSERT INTO \"users\" (username, password, usertype, firstname, lastname) VALUES (?, ?, \'teacher\', ?, ?);";
            String query3 = "SELECT * FROM \"users\" WHERE \"users\".username = ? AND \"users\".password = ?";
            preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            preparedStatement = connection.prepareStatement(query3);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer userid = resultSet.getInt("userid");
                String query2 = "INSERT INTO teachers (userid, departmentid, years_of_teaching) VALUES (?, ?, ?);";
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, userid);
                preparedStatement.setInt(2, departmentid);
                preparedStatement.setInt(3, yearsOfTeaching);
                rowsAffected += preparedStatement.executeUpdate();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String insertCourse(String name, Integer noCredits, String description, String code, int departmentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = PostgreSQLConnection.makeConnection();

            String query = "INSERT INTO courses (course_name, course_description, number_of_credits, course_code, departmentid) VALUES (?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, noCredits);
            preparedStatement.setString(4, code);
            preparedStatement.setInt(5, departmentid);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public void deleteUser(Integer userid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "DELETE FROM \"users\" WHERE \"users\".userid = " + userid;
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();
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

    }

    public void deleteCourse(Integer courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;


        try {
            String query = "DELETE FROM courses WHERE courses.courseid = " + courseid;
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();
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

    }

    public void deleteClass(Integer classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;


        try {
            String query = "DELETE FROM classes WHERE classes.classid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classid);

            preparedStatement.execute();
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

    }

    public String addCourseToStudent(int studentid, int courseid, int teacherid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String query = "INSERT INTO enrollment(studentid, courseid, teacherid) VALUES(?, ?, ?) ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);
            preparedStatement.setInt(2, courseid);
            preparedStatement.setInt(3, teacherid);
            rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected + " Rows Affected. Success!";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }

    }

    public String addCourseToTeacher(int teacherid, int courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            String query = "INSERT INTO teachers_courses (teacherid, courseid) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, courseid);
            rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected + " Rows Affected. Success!";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }

    }

    public boolean studentStudiesCourse(int studentid, int courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM enrollment WHERE studentid = ? AND courseid = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);
            preparedStatement.setInt(2, courseid);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public void deleteDepartment(Integer departmentid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        try {

            String query = "DELETE FROM users u USING teachers t WHERE t.userid = u.userid AND t.teacherid IN (SELECT teacherid FROM teachers WHERE teachers.departmentid = ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentid);
            preparedStatement.execute();

            String query2 = "DELETE FROM departments WHERE departmentid = ?";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, departmentid);
            preparedStatement.execute();
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

    }

    public boolean TeacherTeachesCourse(int teacherid, int courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM teachers_courses WHERE teacherid = ? AND courseid = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, courseid);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public String insertClass(String name, String code) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = PostgreSQLConnection.makeConnection();

            String query = "INSERT INTO classes (class_name, class_code) VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String insertDepartment(String name, String code) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = PostgreSQLConnection.makeConnection();

            String query = "INSERT INTO departments (department_name, department_code) VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return rowsAffected + "Rows Affected. Success!";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public boolean takenUsername(String username) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM users WHERE username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean takenRegNumber(String regNumber) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM students WHERE student_registration_number = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, regNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean existsClassName(String className) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM classes WHERE class_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, className);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean existsClassCode(String classCode) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM classes WHERE class_code = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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


    public boolean existsDepartmentName(String departmentName) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM departments WHERE department_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departmentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean existsDepartmentCode(String departmentCode) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM departments WHERE department_code = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departmentCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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


    public boolean existsCourseName(String courseName) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM courses WHERE course_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean existsCourseCode(String courseCode) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM courses WHERE course_code = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public void removeCourseFromStudent(Integer studentid, Integer courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;


        try {
            String query = "DELETE FROM enrollment WHERE enrollment.studentid= ? AND enrollment.courseid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);
            preparedStatement.setInt(2, courseid);
            preparedStatement.execute();
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

    }

    public void removeCourseFromTeacher(Integer teacherid, Integer courseid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;


        try {
            String query = "DELETE FROM teachers_courses WHERE teachers_courses.teacherid= ? AND teachers_courses.courseid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, courseid);
            preparedStatement.execute();
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

    }

    public void addStudentToClass(Integer studentid, Integer classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;


        try {
            String query = "UPDATE students SET classid = ? WHERE studentid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classid);
            preparedStatement.setInt(2, studentid);
            preparedStatement.execute();
        }catch (Exception e) {
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
    }

    public void removeStudentFromClass(Integer studentid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;


        try {
            String query = "UPDATE students SET classid = NULL WHERE studentid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentid);
            preparedStatement.execute();
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
    }

    public void removeTeacherFromClass(Integer teacherid, Integer classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        ;
        PreparedStatement preparedStatement = null;


        try {
            String query = "DELETE FROM teachers_classes WHERE teacherid = ? AND classid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, classid);
            preparedStatement.execute();
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
    }

    public void addTeacherToClass(Integer teacherid, Integer classid) {
        Connection connection = PostgreSQLConnection.makeConnection();
        PreparedStatement preparedStatement = null;


        try {
            String query = "INSERT INTO teachers_classes(teacherid, classid) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, classid);
            preparedStatement.executeUpdate();
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
    }

}
