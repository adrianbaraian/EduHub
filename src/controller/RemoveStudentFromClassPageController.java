package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Students;
import repository.AdminRepository;
import repository.ClassRepository;
import repository.StudentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveStudentFromClassPageController implements Initializable {
    ObservableList<String> listCl;
    ObservableList<Students> listS;
    String selectedClassName = "";
    int selectedClassID = -1;
    ClassRepository classRepository = new ClassRepository();
    AdminRepository adminRepository = new AdminRepository();
    int index = -1;
    int selectedStudentID = -1;
    @FXML
    private Label successLabel;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> classesList;
    @FXML
    private TableColumn<Students, String> col_firstname;
    @FXML
    private TableColumn<Students, String> col_lastname;
    @FXML
    private TableColumn<Students, Integer> col_studentid;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Students> table_students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listCl = ClassRepository.getClassName();
        classesList.setItems(listCl);
    }

    public void updateTable() {
        col_studentid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));

        listS = StudentRepository.getDataStudentsInClass(selectedClassID);
        table_students.setItems(listS);
    }

    @FXML
    void getSelectedClass(MouseEvent event) {
        selectedClassName = classesList.getSelectionModel().getSelectedItem();
        selectedClassID = classRepository.getClassByName(selectedClassName);
        if (selectedClassID <= -1) {
            return;
        }
        updateTable();
        classesList.setVisible(false);
        table_students.setVisible(true);
        removeButton.setVisible(true);
        backButton.setVisible(true);
        successLabel.setVisible(false);
    }

    @FXML
    void getSelectedStudent(MouseEvent event) {
        index = table_students.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        selectedStudentID = col_studentid.getCellData(index);
        successLabel.setVisible(false);
    }

    @FXML
    void onRemoveButtonClick(ActionEvent event) {
        if (selectedStudentID == -1) {
            successLabel.setVisible(false);
            return;
        }
        successLabel.setVisible(true);
        adminRepository.removeStudentFromClass(selectedStudentID);
        updateTable();
    }

    @FXML
    public void onBackButtonClick() {
        successLabel.setVisible(false);
        table_students.setVisible(false);
        backButton.setVisible(false);
        classesList.setVisible(true);
        removeButton.setVisible(false);
    }

}
