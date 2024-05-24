package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Classes;
import repository.AdminRepository;
import repository.ClassRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteClassPageController implements Initializable {
    ObservableList<Classes> listCL;
    int index = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private TableColumn<Classes, String> col_className;
    @FXML
    private TableColumn<Classes, Integer> col_classid;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField deleteClassIDField;
    @FXML
    private TableView<Classes> table_classes;

    public void updateTable() {
        col_classid.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("classid"));
        col_className.setCellValueFactory(new PropertyValueFactory<Classes, String>("class_name"));


        listCL = ClassRepository.getDataClasses();
        table_classes.setItems(listCL);
    }

    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_classes.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        deleteClassIDField.setText(col_classid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        int classid = Integer.parseInt(deleteClassIDField.getText());
        if(classid < 0) return;

        adminRepository.deleteClass(classid);
        successLabel.setVisible(true);
        updateTable();
        deleteClassIDField.clear();
    }

}
