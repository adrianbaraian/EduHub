package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Enrollment;
import model.Students;
import main.UserSession;
import repository.ClassRepository;
import repository.LoginRepository;
import repository.StudentRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    ObservableList<Enrollment> listSC;
    ObservableList<Students> listClassSt;
    StudentRepository studentRepository = new StudentRepository();
    int index = -1;
    ClassRepository classRepository = new ClassRepository();
    int selectedCourseID = -1;
    LoginRepository loginRepository = new LoginRepository();
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label classNameLabel;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Label studentSeeClassTitle;
    @FXML
    private Button logOutButton;
    @FXML
    private TableColumn<Enrollment, String> col_courseCode;
    @FXML
    private TableColumn<Enrollment, String> col_courseName;
    @FXML
    private TableColumn<Enrollment, Integer> col_credits;
    @FXML
    private TableColumn<Enrollment, Integer> col_grade;
    @FXML
    private TableColumn<Students, String> col_firstName;
    @FXML
    private TableColumn<Students, String> col_lastName;
    @FXML
    private TableColumn<Students, Integer> col_studentid;
    @FXML
    private TableView<Students> table_classView;
    @FXML
    private Label studentFullName;
    @FXML
    private Label studentNumber;
    @FXML
    private Label studentTitle;
    @FXML
    private TableView<Enrollment> table_grades;
    @FXML
    private Button backButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Button changeButton;

    public void updateTable() {
        col_courseCode.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("course_code"));
        col_credits.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("credits"));
        col_grade.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("grade"));
        col_courseName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("course_name"));

        col_studentid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentid"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));

        listSC = StudentRepository.getDataStudentCourse(UserSession.getInstance().getStudent().getUserid());
        listClassSt = ClassRepository.getStudentsInClassOfStudent(UserSession.getInstance().getStudent().getStudentid());
        table_grades.setItems(listSC);
        table_classView.setItems(listClassSt);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        studentFullName.setText(UserSession.getInstance().getStudent().getFirstname() + " " + UserSession.getInstance().getStudent().getLastname());
        studentNumber.setText(UserSession.getInstance().getStudent().getStudent_registration_number());

        classNameLabel.setText(classRepository.getNameOfClass(UserSession.getInstance().getStudent().getClassid()));
    }

    @FXML
    public void onSeeGradesButtonClick() {
        table_grades.setVisible(true);
        studentTitle.setVisible(true);
        menuBar.setVisible(false);
        backButton.setVisible(true);
        backgroundImage.setVisible(false);
    }

    @FXML
    public void onChangePasswordButtonClick() {
        menuBar.setVisible(false);
        confirmPasswordField.setVisible(true);
        confirmPasswordLabel.setVisible(true);
        newPasswordField.setVisible(true);
        newPasswordLabel.setVisible(true);
        backButton.setVisible(true);

        changeButton.setVisible(true);
        backgroundImage.setVisible(false);
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
    public void onChangeButtonClick() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (Objects.equals(newPassword, confirmPassword)) {
            String res = loginRepository.changePassword(UserSession.getInstance().getStudent().getUserid(), newPassword);
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
    public void onBackButtonClick() {
        menuBar.setVisible(true);
        backButton.setVisible(false);
        newPasswordLabel.setVisible(false);
        newPasswordField.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        confirmPasswordField.setVisible(false);
        changeButton.setVisible(false);
        studentTitle.setVisible(false);
        table_grades.setVisible(false);
        table_classView.setVisible(false);
        studentSeeClassTitle.setVisible(false);
        backgroundImage.setVisible(true);
        classNameLabel.setVisible(false);
        successLabel.setVisible(false);
        completeAllFieldsLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
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

    @FXML
    public void onViewClassButtonClick() {
        menuBar.setVisible(false);
        backButton.setVisible(true);
        table_classView.setVisible(true);
        studentSeeClassTitle.setVisible(true);
        backgroundImage.setVisible(false);
        classNameLabel.setVisible(true);
    }
}
