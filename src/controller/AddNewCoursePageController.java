package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import repository.AdminRepository;
import repository.DepartmentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewCoursePageController implements Initializable {
    AdminRepository adminRepository = new AdminRepository();
    DepartmentRepository departmentRepository = new DepartmentRepository();
    ObservableList<String> departmentsName;
    @FXML
    private Label successLabel;
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Label courseNameAlreadyExistsLabel;
    @FXML
    private Label courseCodeAlreadyExistsLabel;
    @FXML
    private TextField newCourseNameField;
    @FXML
    private Spinner<Integer> newCourseNoCreditsField;
    @FXML
    private TextArea newCourseDescriptionArea;
    @FXML
    private TextField newCourseCodeField;
    @FXML
    private ChoiceBox newDepartmentNameField;

    public boolean checkCompletedFields() {
        successLabel.setVisible(false);
        if (newDepartmentNameField.getValue() != null && newCourseNameField.getLength() > 0 && newCourseCodeField.getLength() > 0) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        } else {
            completeAllFieldsLabel.setVisible(true);
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentsName = DepartmentRepository.getNameDepartments();
        newDepartmentNameField.setItems(departmentsName);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8);
        newCourseNoCreditsField.setValueFactory(valueFactory);
    }

    public void onAddCourseButtonClick() {
        if (checkCompletedFields()) {
            String newCourseName = newCourseNameField.getText();
            Integer newNoCredits = newCourseNoCreditsField.getValue();
            String newDescription = newCourseDescriptionArea.getText();
            String newCourseCode = newCourseCodeField.getText();
            String newDepartmentName = newDepartmentNameField.getValue().toString();

            Integer newDepartmentid = departmentRepository.getDepartmentByName(newDepartmentName);

            if (!adminRepository.existsCourseCode(newCourseCode) && !adminRepository.existsCourseName(newCourseName)) {
                successLabel.setVisible(true);
                String res = adminRepository.insertCourse(newCourseName, newNoCredits, newDescription, newCourseCode, newDepartmentid);

                System.out.println(res);
                newCourseNameField.clear();
                newCourseDescriptionArea.clear();
                newCourseCodeField.clear();
            } else {
                successLabel.setVisible(false);
                if (adminRepository.existsCourseCode(newCourseCode)) {
                    courseCodeAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsCourseName(newCourseName)) {
                        courseNameAlreadyExistsLabel.setVisible(true);
                        courseNameAlreadyExistsLabel.setLayoutY(460);
                        courseCodeAlreadyExistsLabel.setLayoutY(490);
                    } else {
                        courseNameAlreadyExistsLabel.setVisible(false);
                        courseCodeAlreadyExistsLabel.setLayoutY(475);
                    }
                } else {
                    courseCodeAlreadyExistsLabel.setVisible(false);
                    courseNameAlreadyExistsLabel.setLayoutY(475);
                }
                if (adminRepository.existsCourseName(newCourseName)) {
                    courseNameAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsCourseCode(newCourseCode)) {
                        courseCodeAlreadyExistsLabel.setVisible(true);
                        courseCodeAlreadyExistsLabel.setLayoutY(460);
                        courseNameAlreadyExistsLabel.setLayoutY(490);
                    } else {
                        courseCodeAlreadyExistsLabel.setVisible(false);
                        courseNameAlreadyExistsLabel.setLayoutY(475);
                    }
                } else {
                    courseNameAlreadyExistsLabel.setVisible(false);
                    courseCodeAlreadyExistsLabel.setLayoutY(475);
                }
            }
        }
    }
}
