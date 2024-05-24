package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import repository.AdminRepository;
import repository.DepartmentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewTeacherPageController implements Initializable {
    AdminRepository adminRepository = new AdminRepository();
    DepartmentRepository departmentRepository = new DepartmentRepository();
    ObservableList<String> departmentsName;
    @FXML
    private Label successLabel;
    @FXML
    private Label usernameTakenLabel;
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Spinner<Integer> yearsOfTeachingSpinner;
    @FXML
    private ChoiceBox newDepartmentNameField;
    @FXML
    private TextField newTeacherUsernameField;
    @FXML
    private TextField newTeacherPasswordField;
    @FXML
    private TextField newTeacherFirstnameField;
    @FXML
    private TextField newTeacherLastnameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentsName = DepartmentRepository.getNameDepartments();
        newDepartmentNameField.setItems(departmentsName);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 80);
        yearsOfTeachingSpinner.setValueFactory(valueFactory);
    }

    public boolean checkCompletedFields() {
        successLabel.setVisible(false);
        if (newTeacherUsernameField.getLength() > 0 && newTeacherPasswordField.getLength() > 0 && newTeacherLastnameField.getLength() > 0
                && newTeacherFirstnameField.getLength() > 0 && newDepartmentNameField.getValue() != null) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        }
        completeAllFieldsLabel.setVisible(true);
        return false;
    }

    public void onAddTeacherButtonClick() {
        if (checkCompletedFields()) {
            String newUsername = newTeacherUsernameField.getText();
            String newPassword = newTeacherPasswordField.getText();
            String newFirstname = newTeacherFirstnameField.getText();
            String newLastname = newTeacherLastnameField.getText();

            String newDepartmentName = newDepartmentNameField.getValue().toString();

            Integer newDepartmentid = departmentRepository.getDepartmentByName(newDepartmentName);

            Integer newYearsOfTeaching = yearsOfTeachingSpinner.getValue();

            if (!adminRepository.takenUsername(newUsername)) {
                usernameTakenLabel.setVisible(false);

                String res = adminRepository.insertTeacher(newUsername, newPassword, newFirstname, newLastname, newDepartmentid, newYearsOfTeaching);

                System.out.println(res);
                successLabel.setVisible(true);
                newTeacherFirstnameField.clear();
                newTeacherLastnameField.clear();
                newTeacherPasswordField.clear();
                newTeacherUsernameField.clear();
            } else {
                successLabel.setVisible(false);
                usernameTakenLabel.setVisible(true);
            }
        }
    }
}
