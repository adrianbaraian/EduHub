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
import model.Students;
import repository.AdminRepository;
import repository.StudentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteStudentPageController implements Initializable {
    ObservableList<Students> listS;
    int index = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private TextField deleteStudentIDField;
    @FXML
    private TableColumn<Students, String> col_firstname;
    @FXML
    private TableColumn<Students, String> col_lastname;
    @FXML
    private TableColumn<Students, String> col_password;
    @FXML
    private TableColumn<Students, Integer> col_userid;
    @FXML
    private TableColumn<Students, String> col_username;
    @FXML
    private TableView<Students> table_students;

    public void updateTable() {
        col_userid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("userid"));
        col_username.setCellValueFactory(new PropertyValueFactory<Students, String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Students, String>("password"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));

        listS = StudentRepository.getDataStudents();
        table_students.setItems(listS);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_students.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        deleteStudentIDField.setText(col_userid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    public void onDeleteButtonClick() {
        Integer userid = Integer.parseInt(deleteStudentIDField.getText());
        successLabel.setVisible(true);
        adminRepository.deleteUser(userid);
        updateTable();
        deleteStudentIDField.clear();
    }

}
