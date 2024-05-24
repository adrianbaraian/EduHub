package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Teachers;
import repository.AdminRepository;
import repository.TeacherRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteTeacherPageController implements Initializable {
    ObservableList<Teachers> listT;
    int index = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private TextField deleteTeacherIDField;
    @FXML
    private TableColumn<Teachers, String> col_firstname;
    @FXML
    private TableColumn<Teachers, String> col_lastname;
    @FXML
    private TableColumn<Teachers, String> col_password;
    @FXML
    private TableColumn<Teachers, Integer> col_userid;
    @FXML
    private TableColumn<Teachers, String> col_username;
    @FXML
    private TableView<Teachers> table_teachers;

    public void updateTable() {
        col_userid.setCellValueFactory(new PropertyValueFactory<Teachers, Integer>("userid"));
        col_username.setCellValueFactory(new PropertyValueFactory<Teachers, String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Teachers, String>("password"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("lastname"));

        listT = TeacherRepository.getDataTeachers();
        table_teachers.setItems(listT);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_teachers.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        deleteTeacherIDField.setText(col_userid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    @FXML
    public void onDeleteTeacherButtonClick() {
        Integer userid = Integer.parseInt(deleteTeacherIDField.getText());
        successLabel.setVisible(true);
        adminRepository.deleteUser(userid);
        deleteTeacherIDField.clear();
        updateTable();
    }
}
