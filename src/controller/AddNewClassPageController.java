package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.AdminRepository;

public class AddNewClassPageController {
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private Label classNameAlreadyExistsLabel;
    @FXML
    private Label classCodeAlreadyExistsLabel;
    @FXML
    private Label completeAllFieldsLabel;
    @FXML
    private TextField newClassCodeField;
    @FXML
    private TextField newClassNameField;

    public boolean checkCompletedFields() {
        successLabel.setVisible(false);
        if (newClassCodeField.getLength() > 0 && newClassNameField.getLength() > 0) {
            completeAllFieldsLabel.setVisible(false);
            return true;
        } else {
            completeAllFieldsLabel.setVisible(true);
            return false;
        }
    }

    @FXML
    void onAddClassButtonClick(ActionEvent event) {
        if (checkCompletedFields()) {

            String newClassCode = newClassCodeField.getText();
            String newClassName = newClassNameField.getText();
            if (!adminRepository.existsClassName(newClassName) && !adminRepository.existsClassCode(newClassCode)) {
                successLabel.setVisible(true);
                classNameAlreadyExistsLabel.setVisible(false);
                classCodeAlreadyExistsLabel.setVisible(false);
                adminRepository.insertClass(newClassName, newClassCode);
                newClassNameField.clear();
                newClassCodeField.clear();
            } else {
                successLabel.setVisible(false);
                if (adminRepository.existsClassCode(newClassCode)) {
                    classCodeAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsClassName(newClassName)) {
                        classNameAlreadyExistsLabel.setVisible(true);
                        classNameAlreadyExistsLabel.setLayoutY(245);
                        classCodeAlreadyExistsLabel.setLayoutY(275);
                    } else {
                        classNameAlreadyExistsLabel.setVisible(false);
                        classCodeAlreadyExistsLabel.setLayoutY(262);
                    }
                } else {
                    classCodeAlreadyExistsLabel.setVisible(false);
                    classNameAlreadyExistsLabel.setLayoutY(262);
                }
                if (adminRepository.existsClassName(newClassName)) {
                    classNameAlreadyExistsLabel.setVisible(true);
                    if (adminRepository.existsClassCode(newClassCode)) {
                        classCodeAlreadyExistsLabel.setVisible(true);
                        classNameAlreadyExistsLabel.setLayoutY(245);
                        classCodeAlreadyExistsLabel.setLayoutY(275);
                    } else {
                        classCodeAlreadyExistsLabel.setVisible(false);
                        classNameAlreadyExistsLabel.setLayoutY(262);
                    }
                } else {
                    classNameAlreadyExistsLabel.setVisible(false);
                    classCodeAlreadyExistsLabel.setLayoutY(262);
                }
            }
        }
    }

}
