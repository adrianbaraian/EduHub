package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Students;
import repository.AdminRepository;
import repository.ClassRepository;
import repository.StudentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentToClassPageController implements Initializable {
    ObservableList<Students> listS;
    ObservableList<String> listC;
    int index = -1;
    ClassRepository classRepository = new ClassRepository();
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private ChoiceBox<String> selectClassChoice;
    @FXML
    private TableColumn<Students, String> col_firstname;
    @FXML
    private TableColumn<Students, String> col_lastname;
    @FXML
    private TableColumn<Students, Integer> col_studentid;
    @FXML
    private TextField studentIDField;
    @FXML
    private TableView<Students> table_students;

    public void updateTable() {
        col_studentid.setCellValueFactory(new PropertyValueFactory<Students, Integer>("studentid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));

        listS = StudentRepository.getDataStudentsNotInClass();
        table_students.setItems(listS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        listC = ClassRepository.getClassName();
        selectClassChoice.setItems(listC);
    }

    @FXML
    public void getSelectedStudent(MouseEvent event) {
        index = table_students.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        studentIDField.setText(col_studentid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    @FXML
    void onAddToClassButtonClick(ActionEvent event) {
        if (Integer.parseInt(studentIDField.getText()) == -1 || selectClassChoice.getValue() == null)
            return;

        Integer selectedStudentID = Integer.parseInt(studentIDField.getText());
        String className = selectClassChoice.getValue();
        Integer classID = classRepository.getClassByName(className);
        successLabel.setVisible(true);
        adminRepository.addStudentToClass(selectedStudentID, classID);

        updateTable();
    }

}
