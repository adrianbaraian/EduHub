package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Students;
import model.Teachers;
import main.UserSession;
import model.UserTypes;
import repository.LoginRepository;

import java.util.Objects;

public class LoginPageController {
    LoginRepository loginRepository = new LoginRepository();
    @FXML
    private Label ErrorLabel;
    @FXML
    private Label SuccessLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button LoginButton;

    @FXML
    protected void OnLoginButtonClick() throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();


        int currentUserID = loginRepository.validateLogin(username, password);

        String res = loginRepository.getUserType(currentUserID);

        if (Objects.equals(res, UserTypes.student.toString()) || Objects.equals(res, UserTypes.admin.toString()) || Objects.equals(res, UserTypes.teacher.toString())) {
            Parent root;
            Stage window;
            switch (res) {
                case "admin":
                    root = FXMLLoader.load(getClass().getResource("/view/admin-page.fxml"));

                    window = (Stage) LoginButton.getScene().getWindow();
                    window.setTitle("Administrator");
                    window.setScene(new Scene(root, 800, 500));

                    break;

                case "student":
                    UserSession.getInstance().setStudent((Students) loginRepository.getUser(currentUserID, "student"));

                    root = FXMLLoader.load(getClass().getResource("/view/student-page.fxml"));

                    window = (Stage) LoginButton.getScene().getWindow();
                    window.setTitle("Student");
                    window.setScene(new Scene(root, 800, 440));
                    break;

                case "teacher":
                    UserSession.getInstance().setTeacher((Teachers) loginRepository.getUser(currentUserID, "teacher"));

                    root = FXMLLoader.load(getClass().getResource("/view/teacher-page.fxml"));

                    window = (Stage) LoginButton.getScene().getWindow();
                    window.setTitle("Teacher");
                    window.setScene(new Scene(root, 900, 500));

                    break;

                default:
                    break;
            }


        } else if (Objects.equals(res, "Login Failed!")) {
            SuccessLabel.setVisible(false);
            ErrorLabel.setVisible(true);
        }

    }
}
