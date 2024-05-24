package controller;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

public class AddCourseToStudentPageController implements Initializable {
    ObservableList<Students> listS;
    ObservableList<Courses> listC;
    ObservableList<Teachers> listT;
    TeacherRepository teacherRepository = new TeacherRepository();
    int indexStudent = -1;
    int indexCourse = -1;
    int indexTeacher = -1;
    int selectedStudentID = -1;
    int selectedCourseID = -1;
    int selectedTeacherID = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private Label studentAlreadyStudiesCourseLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label noTeachersErrorLabel;
    @FXML
    private Button addCourseToStudentButton;
    @FXML
    private TableView<Teachers> table_teachers;
    @FXML
    private TableColumn<Teachers, String> teachers_col_firstname;
    @FXML
    private TableColumn<Teachers, String> teachers_col_lastname;
    @FXML
    private TableColumn<Teachers, Integer> teachers_col_teacherid;
    @FXML
    private TableColumn<Courses, Integer> col_courseid;
    @FXML
    private TableColumn<Courses, String> col_coursename;
    @FXML
    private TableColumn<Students, String> col_firstName;
    @FXML
    private TableColumn<Students, String> col_lastName;
    @FXML
    private TableColumn<Students, Integer> col_userid;
    @FXML
    private TableView<Courses> table_courses;
    @FXML
    private TableView<Students> table_students;

    @FXML
    void getSelectedStudent(MouseEvent mouseEvent) {
        indexStudent = table_students.getSelectionModel().getSelectedIndex();

        if (indexStudent <= -1) {
            return;
        }
        selectedStudentID = col_userid.getCellData(indexStudent);
    }

    @FXML
    void getSelectedTeacher(MouseEvent mouseEvent) {
        indexTeacher = table_teachers.getSelectionModel().getSelectedIndex();

        if (indexTeacher <= -1) {
            return;
        }
        selectedTeacherID = teachers_col_teacherid.getCellData(indexTeacher);
        successLabel.setVisible(false);

    }

    public void updateTeacherTable() {
        teachers_col_teacherid.setCellValueFactory(new PropertyValueFactory<Teachers, Integer>("teacherid"));
        teachers_col_firstname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("firstname"));
        teachers_col_lastname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("lastname"));

        listT = TeacherRepository.getDataTeachersByCourse(selectedCourseID);
        table_teachers.setItems(listT);
    }

    @FXML
    void getSelectedCourse(MouseEvent mouseEvent) {
        if (selectedStudentID != -1) {
            indexCourse = table_courses.getSelectionModel().getSelectedIndex();

            if (indexCourse <= -1) {
                return;
            }
            selectedCourseID = col_courseid.getCellData(indexCourse);
            successLabel.setVisible(false);
            updateTeacherTable();
            boolean existTeacherInCourse = teacherRepository.existTeacherInCourse(selectedCourseID);
            boolean studentAlreadyStudiesCourse = adminRepository.studentStudiesCourse(selectedStudentID, selectedCourseID);
            if (!studentAlreadyStudiesCourse) {
                studentAlreadyStudiesCourseLabel.setVisible(false);
                if (existTeacherInCourse) {

                    addCourseToStudentButton.setLayoutX(310);
                    addCourseToStudentButton.setLayoutY(195);
                    backButton.setVisible(true);
                    table_courses.setVisible(false);
                    table_teachers.setVisible(true);
                    noTeachersErrorLabel.setVisible(false);
                } else {
                    studentAlreadyStudiesCourseLabel.setVisible(false);
                    noTeachersErrorLabel.setVisible(true);
                }
            } else {
                noTeachersErrorLabel.setVisible(false);
                studentAlreadyStudiesCourseLabel.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_courseid.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("courseid"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<Courses, String>("course_name"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));
        col_userid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentid"));

        listC = CourseRepository.getDataCourses();
        listS = StudentRepository.getDataStudents();

        table_courses.setItems(listC);
        table_students.setItems(listS);
    }

    @FXML
    void onAddCourseToStudentButtonClick() {
        if (selectedStudentID == -1 || selectedCourseID == -1 || selectedTeacherID == -1) {
            table_courses.setVisible(true);
            table_teachers.setVisible(false);
            selectedTeacherID = -1;
            selectedCourseID = -1;
            selectedStudentID = -1;
            indexCourse = -1;
            indexTeacher = -1;
            indexStudent = -1;
            return;
        }

        adminRepository.addCourseToStudent(selectedStudentID, selectedCourseID, selectedTeacherID);
        table_courses.setVisible(true);
        table_teachers.setVisible(false);
        backButton.setVisible(false);
        successLabel.setVisible(true);
        selectedTeacherID = -1;
        selectedCourseID = -1;
        indexCourse = -1;
        indexTeacher = -1;
        indexStudent = -1;
    }

    @FXML
    public void onBackButtonClick() {
        selectedTeacherID = -1;
        selectedCourseID = -1;
        selectedStudentID = -1;
        indexCourse = -1;
        indexTeacher = -1;
        indexStudent = -1;

        table_courses.setVisible(true);
        table_teachers.setVisible(false);
        backButton.setVisible(false);
        successLabel.setVisible(false);
        addCourseToStudentButton.setLayoutX(320);
    }
}
