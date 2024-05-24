package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Enrollment;
import main.UserSession;
import repository.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {
    ObservableList<String> listCNames;
    ObservableList<Enrollment> listSC;
    LoginRepository loginRepository = new LoginRepository();
    TeacherRepository teacherRepository = new TeacherRepository();
    StudentRepository studentRepository = new StudentRepository();
    CourseRepository courseRepository = new CourseRepository();
    DepartmentRepository departmentRepository = new DepartmentRepository();
    String selectedCourseName = null;
    int indexGrade = -1;
    String selectedStudentNumber = "";
    String selectedCourseCode = "";
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Label selectCourseTitle;
    @FXML
    private Label giveGradesTitle;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button logOutButton;
    @FXML
    private TextField gradeField;
    @FXML
    private Label gradeLabel;
    @FXML
    private Button changeButton;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Enrollment, String> col_courseCode;
    @FXML
    private TableColumn<Enrollment, String> col_firstName;
    @FXML
    private TableColumn<Enrollment, Integer> col_grade;
    @FXML
    private TableColumn<Enrollment, String> col_lastName;
    @FXML
    private TableColumn<Enrollment, String> col_studentNumber;
    @FXML
    private Button setGradeButton;
    @FXML
    private TableView<Enrollment> table_studentGrades;
    @FXML
    private Label teacherNameLabel;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField newPasswordField;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private ListView<String> list_courses;

    public void updateTable() {
        col_courseCode.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("course_code"));
        col_studentNumber.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("studentNumber"));
        col_grade.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("grade"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("firstname"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("lastname"));
        listSC = StudentRepository.getDataStudentsCourses(UserSession.getInstance().getTeacher().getTeacherid(), selectedCourseName);
        table_studentGrades.setItems(listSC);
        System.out.println("aici" + listSC);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherNameLabel.setText(UserSession.getInstance().getTeacher().getFirstname() + " " + UserSession.getInstance().getTeacher().getLastname());

        updateTable();

        listCNames = CourseRepository.getNameCoursesOfTeacher(UserSession.getInstance().getTeacher().getTeacherid());
        list_courses.setItems(listCNames);
        departmentLabel.setText(departmentRepository.getDepartmentName(UserSession.getInstance().getTeacher().getDepartmentid()));
    }

    @FXML
    public void getSelected(MouseEvent mouseEvent) {
        selectedCourseName = list_courses.getSelectionModel().getSelectedItem();
        giveGradesTitle.setVisible(true);
        table_studentGrades.setVisible(true);
        setGradeButton.setVisible(true);
        gradeField.setVisible(true);
        gradeLabel.setVisible(true);
        list_courses.setVisible(false);
        selectCourseTitle.setVisible(false);
        updateTable();
    }

    public boolean checkCompletedFields() {
        passwordErrorLabel.setVisible(false);
        successLabel.setVisible(false);
        if (newPasswordField.getLength() > 0 && confirmPasswordField.getLength() > 0) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        } else {
            completeAllFieldsLabel.setVisible(true);
            return false;
        }
    }

    @FXML
    void onChangePasswordButtonClick(ActionEvent event) {
        menuBar.setVisible(false);
        backButton.setVisible(true);
        newPasswordLabel.setVisible(true);
        newPasswordField.setVisible(true);
        confirmPasswordField.setVisible(true);
        confirmPasswordLabel.setVisible(true);
        changeButton.setVisible(true);
        backgroundImage.setVisible(false);
    }

    @FXML
    void onGiveGradeToStudentButtonClick(ActionEvent event) {
        selectCourseTitle.setVisible(true);
        menuBar.setVisible(false);
        backButton.setVisible(true);
        list_courses.setVisible(true);
        backgroundImage.setVisible(false);
    }

    @FXML
    public void onChangeButtonClick() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (Objects.equals(newPassword, confirmPassword)) {
            String res = loginRepository.changePassword(UserSession.getInstance().getTeacher().getUserid(), newPassword);
            passwordErrorLabel.setVisible(false);
            successLabel.setVisible(true);
            System.out.println(res);
            newPasswordField.clear();
            confirmPasswordField.clear();
        } else {
            successLabel.setVisible(false);
            passwordErrorLabel.setVisible(true);
        }
    }

    @FXML
    public void getSelectedGrade(MouseEvent mouseEvent) {
        indexGrade = table_studentGrades.getSelectionModel().getSelectedIndex();
        if (indexGrade <= -1) {
            return;
        }
        gradeField.setText(col_grade.getCellData(indexGrade).toString());
        selectedStudentNumber = col_studentNumber.getCellData(indexGrade);
        selectedCourseCode = col_courseCode.getCellData(indexGrade);
    }

    @FXML
    public void onSetGradeButtonClick(ActionEvent event) throws SQLException {
        Integer grade = Integer.parseInt(gradeField.getText());
        Integer studentID = studentRepository.getStudentIDbyNumber(selectedStudentNumber);
        Integer courseID = courseRepository.getCourseIDbyCode(selectedCourseCode);
        String res = teacherRepository.updateGrade(studentID, grade, courseID);
        System.out.println(res);
        updateTable();
        gradeField.clear();
    }

    @FXML
    public void onBackButtonClick() {
        menuBar.setVisible(true);
        backButton.setVisible(false);
        setGradeButton.setVisible(false);
        table_studentGrades.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        confirmPasswordField.setVisible(false);
        newPasswordField.setVisible(false);
        newPasswordLabel.setVisible(false);
        changeButton.setVisible(false);
        gradeField.setVisible(false);
        gradeLabel.setVisible(false);
        giveGradesTitle.setVisible(false);
        list_courses.setVisible(false);
        selectCourseTitle.setVisible(false);
        backgroundImage.setVisible(true);
        passwordErrorLabel.setVisible(false);
        successLabel.setVisible(false);
        completeAllFieldsLabel.setVisible(false);
        newPasswordField.clear();
        confirmPasswordField.clear();
    }

    @FXML
    public void onLogOutButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login-page.fxml"));

        Stage window = (Stage) logOutButton.getScene().getWindow();
        window.setTitle("Login Page");
        window.setScene(new Scene(root, 800, 440));

        UserSession.clear();
    }

}
