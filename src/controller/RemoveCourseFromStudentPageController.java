package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Courses;
import model.Students;
import model.Teachers;
import repository.AdminRepository;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveCourseFromStudentPageController implements Initializable {
    AdminRepository adminRepository = new AdminRepository();
    ObservableList<Students> listT;
    ObservableList<Courses> listC;
    int indexStudent = -1;
    int indexCourse = -1;
    int selectedStudentID = -1;
    int selectedCourseID = -1;
    @FXML
    private Label successLabel;
    @FXML
    private TableColumn<Courses, Integer> col_courseid;
    @FXML
    private TableColumn<Courses, String> col_coursename;
    @FXML
    private TableColumn<Students, String> col_firstname;
    @FXML
    private TableColumn<Students, String> col_lastname;
    @FXML
    private TableColumn<Students, Integer> col_studentid;
    @FXML
    private TableView<Courses> table_courses;
    @FXML
    private TableView<Students> table_students;

    @FXML
    void getSelectedStudent(MouseEvent event) {
        indexStudent = table_students.getSelectionModel().getSelectedIndex();
        if (indexStudent <= -1) {
            return;
        }
        selectedStudentID = col_studentid.getCellData(indexStudent);
        updateTable();
        selectedCourseID = -1;
        successLabel.setVisible(false);
    }

    @FXML
    void getSelectedCourse(MouseEvent event) {
        indexCourse = table_courses.getSelectionModel().getSelectedIndex();
        if (indexCourse <= -1) {
            return;
        }
        selectedCourseID = col_courseid.getCellData(indexCourse);
        successLabel.setVisible(false);
    }

    public void updateTable() {
        col_courseid.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("courseid"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<Courses, String>("course_name"));
        listC = CourseRepository.getDataCoursesByStudent(selectedStudentID);
        table_courses.setItems(listC);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_studentid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("Studentid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));


        listT = StudentRepository.getDataStudents();

        table_students.setItems(listT);
    }

    @FXML
    void onRemoveCourseButtonClick() {

        if (selectedStudentID <= -1 || selectedCourseID <= -1) {
            successLabel.setVisible(false);
            return;
        }

        successLabel.setVisible(true);
        adminRepository.removeCourseFromStudent(selectedStudentID, selectedCourseID);
        updateTable();

    }
}
