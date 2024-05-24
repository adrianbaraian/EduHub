package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.AdminRepository;
import repository.ClassRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewStudentPageController implements Initializable {
    ObservableList<String> className;
    AdminRepository adminRepository = new AdminRepository();
    ClassRepository classRepository = new ClassRepository();
    @FXML
    private Label successLabel;
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Label regNumberTakenLabel;
    @FXML
    private Label usernameTakenLabel;
    @FXML
    private Label studentNoLengthText;
    @FXML
    private ChoiceBox selectClassChoice;
    @FXML
    private TextField newStudentUsernameField;
    @FXML
    private TextField newStudentPasswordField;
    @FXML
    private TextField newStudentNoField;
    @FXML
    private TextField newStudentFirstnameField;
    @FXML
    private TextField newStudentLastnameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        className = ClassRepository.getClassName();
        selectClassChoice.setItems(className);
    }

    @FXML
    public boolean validateStudentNoLength() {
        if (newStudentNoField.getLength() != 6) {
            studentNoLengthText.setVisible(true);
            return false;
        } else {
            studentNoLengthText.setVisible(false);
            return true;
        }
    }

    public boolean checkCompletedFields() {
        successLabel.setVisible(false);
        if (newStudentUsernameField.getLength() > 0 && newStudentPasswordField.getLength() > 0 && newStudentLastnameField.getLength() > 0
                && newStudentFirstnameField.getLength() > 0 && selectClassChoice.getValue() != null) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        }
        completeAllFieldsLabel.setVisible(true);
        return false;
    }

    @FXML
    public void onAddStudentButtonClick() {
        if (checkCompletedFields()) {
            String newUsername = newStudentUsernameField.getText();
            String newPassword = newStudentPasswordField.getText();
            String newStudentNo = newStudentNoField.getText();
            String newFirstname = newStudentFirstnameField.getText();
            String newLastname = newStudentLastnameField.getText();

            String newClassName = selectClassChoice.getValue().toString();

            Integer newClassid = classRepository.getClassByName(newClassName);


            if (validateStudentNoLength()) {
                if (!adminRepository.takenUsername(newUsername) && !adminRepository.takenRegNumber(newStudentNo)) {
                    adminRepository.insertStudent(newUsername, newPassword, newFirstname, newLastname, newStudentNo, newClassid);

                    successLabel.setVisible(true);

                    newStudentFirstnameField.clear();
                    newStudentLastnameField.clear();
                    newStudentNoField.clear();
                    newStudentPasswordField.clear();
                    newStudentUsernameField.clear();
                    usernameTakenLabel.setVisible(false);
                    regNumberTakenLabel.setVisible(false);
                } else {
                    successLabel.setVisible(false);
                    if (adminRepository.takenUsername(newUsername)) {
                        usernameTakenLabel.setVisible(true);
                        if (adminRepository.takenRegNumber(newStudentNo)) {
                            regNumberTakenLabel.setVisible(true);
                            usernameTakenLabel.setLayoutY(460);
                            regNumberTakenLabel.setLayoutY(490);
                        } else {
                            regNumberTakenLabel.setVisible(false);
                            usernameTakenLabel.setLayoutY(477);
                        }
                    } else if (adminRepository.takenRegNumber(newStudentNo)) {
                        regNumberTakenLabel.setVisible(true);
                        if (adminRepository.takenUsername(newUsername)) {
                            usernameTakenLabel.setLayoutY(460);
                            regNumberTakenLabel.setLayoutY(490);
                            usernameTakenLabel.setVisible(true);
                        } else {
                            usernameTakenLabel.setVisible(false);
                            regNumberTakenLabel.setLayoutY(477);
                        }
                    }
                }

            }
        }
    }
}
