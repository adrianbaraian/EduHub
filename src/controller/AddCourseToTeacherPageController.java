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
import model.Teachers;
import repository.AdminRepository;
import repository.CourseRepository;
import repository.TeacherRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseToTeacherPageController implements Initializable {
    ObservableList<Teachers> listT;
    ObservableList<Courses> listC;
    int indexTeacher = -1;
    int indexCourse = -1;
    int selectedTeacherID = -1;
    int selectedCourseID = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private Label teacherAlreadyTeachesCourseLabel;
    @FXML
    private TableColumn<Courses, Integer> col_courseid;
    @FXML
    private TableColumn<Courses, String> col_coursename;
    @FXML
    private TableColumn<Teachers, String> col_firstname;
    @FXML
    private TableColumn<Teachers, String> col_lastname;
    @FXML
    private TableColumn<Teachers, Integer> col_userid;
    @FXML
    private TableView<Courses> table_courses;
    @FXML
    private TableView<Teachers> table_teachers;

    @FXML
    void getSelectedTeacher(MouseEvent event) {
        indexTeacher = table_teachers.getSelectionModel().getSelectedIndex();
        if (indexTeacher <= -1) {
            return;
        }
        selectedTeacherID = col_userid.getCellData(indexTeacher);
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
        listC = CourseRepository.getDataCoursesByTeacherDepartment(selectedTeacherID);
        table_courses.setItems(listC);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_userid.setCellValueFactory(new PropertyValueFactory<Teachers, Integer>("teacherid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("lastname"));


        listT = TeacherRepository.getDataTeachers();

        table_teachers.setItems(listT);
    }

    @FXML
    void onAddCourseToTeacherButtonClick() {

        if (selectedTeacherID <= -1 || selectedCourseID <= -1) {
            return;
        }

        if (!adminRepository.TeacherTeachesCourse(selectedTeacherID, selectedCourseID)) {
            adminRepository.addCourseToTeacher(selectedTeacherID, selectedCourseID);
            successLabel.setVisible(true);
            teacherAlreadyTeachesCourseLabel.setVisible(false);
            selectedCourseID = -1;
            selectedTeacherID = -1;
        } else {
            successLabel.setVisible(false);
            teacherAlreadyTeachesCourseLabel.setVisible(true);
            selectedCourseID = -1;
            selectedTeacherID = -1;
        }

    }
}
