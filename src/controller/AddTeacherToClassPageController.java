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
import java.util.Objects;
import java.util.ResourceBundle;

public class AddTeacherToClassPageController implements Initializable {
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
    private Button addButton;
    @FXML
    private TableView<Teachers> table_teachers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    public void updateTable() {
        col_teacherid.setCellValueFactory(new PropertyValueFactory<Teachers, Integer>("teacherid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Teachers, String>("lastname"));

        listT = TeacherRepository.getDataTeachers();
        table_teachers.setItems(listT);
    }

    public void updateList() {
        listCl = classRepository.getNameOfClassForTeacher(selectedTeacherID);
        classesList.setItems(listCl);
    }

    @FXML
    void getSelectedClass(MouseEvent event) {
        selectedClassName = classesList.getSelectionModel().getSelectedItem();
        if (Objects.equals(selectedClassName, "")) {
            return;
        }
        selectedClassID = classRepository.getClassByName(selectedClassName);
        successLabel.setVisible(false);
    }

    @FXML
    void getSelectedTeacher(MouseEvent event) {
        index = table_teachers.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        selectedTeacherID = col_teacherid.getCellData(index);
        table_teachers.setVisible(false);
        backButton.setVisible(true);
        classesList.setVisible(true);
        addButton.setVisible(true);
        updateList();
        successLabel.setVisible(false);
    }

    @FXML
    void onAddButtonClick(ActionEvent event) {
        if (selectedTeacherID == -1 || selectedClassID == -1) {
            successLabel.setVisible(false);
            return;
        }
        adminRepository.addTeacherToClass(selectedTeacherID, selectedClassID);
        successLabel.setVisible(true);
        updateList();
    }

    @FXML
    public void onBackButtonClick() {
        table_teachers.setVisible(true);
        backButton.setVisible(false);
        classesList.setVisible(false);
        addButton.setVisible(false);
        successLabel.setVisible(false);
    }

}
