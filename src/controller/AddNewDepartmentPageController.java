package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.AdminRepository;

public class AddNewDepartmentPageController {
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private Label departmentNameAlreadyExistsLabel;
    @FXML
    private Label departmentCodeAlreadyExistsLabel;
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private Button addDepartmentButton;
    @FXML
    private TextField newDepartmentCodeField;
    @FXML
    private TextField newDepartmentNameField;

    public boolean checkCompletedFields() {
        successLabel.setVisible(false);
        if (newDepartmentCodeField.getLength() > 0 && newDepartmentNameField.getLength() > 0) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        } else {
            completeAllFieldsLabel.setVisible(true);
            return false;
        }
    }

    @FXML
    void onAddDepartmentButtonClick(ActionEvent event) {
        if (checkCompletedFields()) {

            String newDepartmentCode = newDepartmentCodeField.getText();
            String newDepartmentName = newDepartmentNameField.getText();
            if (!adminRepository.existsDepartmentName(newDepartmentName) && !adminRepository.existsDepartmentCode(newDepartmentCode)) {
                successLabel.setVisible(true);
                departmentCodeAlreadyExistsLabel.setVisible(false);
                departmentNameAlreadyExistsLabel.setVisible(false);
                adminRepository.insertDepartment(newDepartmentName, newDepartmentCode);
                newDepartmentNameField.clear();
                newDepartmentCodeField.clear();
            } else {
                successLabel.setVisible(false);
                if (adminRepository.existsDepartmentCode(newDepartmentCode)) {
                    departmentCodeAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsDepartmentName(newDepartmentName)) {
                        departmentNameAlreadyExistsLabel.setVisible(true);
                        departmentNameAlreadyExistsLabel.setLayoutY(245);
                        departmentCodeAlreadyExistsLabel.setLayoutY(275);
                    } else {
                        departmentNameAlreadyExistsLabel.setVisible(false);
                        departmentCodeAlreadyExistsLabel.setLayoutY(262);
                    }
                } else {
                    departmentCodeAlreadyExistsLabel.setVisible(false);
                    departmentNameAlreadyExistsLabel.setLayoutY(262);
                }
                if (adminRepository.existsDepartmentName(newDepartmentName)) {
                    departmentNameAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsDepartmentCode(newDepartmentCode)) {
                        departmentCodeAlreadyExistsLabel.setVisible(true);
                        departmentCodeAlreadyExistsLabel.setLayoutY(275);
                        departmentNameAlreadyExistsLabel.setLayoutY(245);
                    } else {
                        departmentCodeAlreadyExistsLabel.setVisible(false);
                        departmentNameAlreadyExistsLabel.setLayoutY(262);
                    }
                } else {
                    departmentNameAlreadyExistsLabel.setVisible(false);
                    departmentCodeAlreadyExistsLabel.setLayoutY(262);
                }
            }
        }
    }

}
