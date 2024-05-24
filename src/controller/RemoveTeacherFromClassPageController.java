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
import model.Teachers;
import repository.AdminRepository;
import repository.ClassRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveTeacherFromClassPageController implements Initializable {
    ObservableList<String> listCl;
    ObservableList<Teachers> listT;
    String selectedClassName = "";
    int selectedClassID = -1;
    ClassRepository classRepository = new ClassRepository();
    AdminRepository adminRepository = new AdminRepository();
    int index = -1;
    int selectedTeacherID = -1;
    @FXML
    private Label successLabel;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> classesList;
    @FXML
    private TableColumn<Teachers, String> col_firstname;
    @FXML
    private TableColumn<Teachers, String> col_lastname;
    @FXML
    private TableColumn<Teachers, Integer> col_teacherid;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Teachers> table_teachers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listCl = ClassRepository.getClassName();
        classesList.setItems(listCl);
    }

    public void updateTable() {
        col_teacherid.setCellValueFactory(new PropertyValueFactory<Teachers, Integer>("teacherid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("lastname"));

        listT = TeacherRepository.getDataTeachersOfClass(selectedClassID);
        table_teachers.setItems(listT);
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
        table_teachers.setVisible(true);
        removeButton.setVisible(true);
        backButton.setVisible(true);
        successLabel.setVisible(false);
    }

    @FXML
    void getSelectedTeacher(MouseEvent event) {
        index = table_teachers.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        selectedTeacherID = col_teacherid.getCellData(index);
        successLabel.setVisible(false);
    }

    @FXML
    void onRemoveButtonClick(ActionEvent event) {
        if (selectedTeacherID == -1) {
            successLabel.setVisible(false);
            return;
        }
        successLabel.setVisible(true);
        adminRepository.removeTeacherFromClass(selectedTeacherID, selectedClassID);
        updateTable();
    }

    @FXML
    public void onBackButtonClick() {
        successLabel.setVisible(false);
        table_teachers.setVisible(false);
        backButton.setVisible(false);
        classesList.setVisible(true);
        removeButton.setVisible(false);
    }

}
