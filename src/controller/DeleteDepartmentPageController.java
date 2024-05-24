package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Departments;
import repository.AdminRepository;
import repository.DepartmentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteDepartmentPageController implements Initializable {
    ObservableList<Departments> listD;
    int index = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private TableColumn<Departments, String> col_departmentName;
    @FXML
    private TableColumn<Departments, Integer> col_departmentid;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField deleteDepartmentIDField;
    @FXML
    private TableView<Departments> table_departments;

    public void updateTable() {
        col_departmentid.setCellValueFactory(new PropertyValueFactory<Departments, Integer>("departmentid"));
        col_departmentName.setCellValueFactory(new PropertyValueFactory<Departments, String>("department_name"));


        listD = DepartmentRepository.getDataDepartments();
        table_departments.setItems(listD);
    }

    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_departments.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        deleteDepartmentIDField.setText(col_departmentid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        Integer departmentid = Integer.parseInt(deleteDepartmentIDField.getText());
        successLabel.setVisible(true);
        adminRepository.deleteDepartment(departmentid);
        updateTable();
        deleteDepartmentIDField.clear();
    }

}
