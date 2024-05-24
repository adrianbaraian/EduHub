package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Courses;
import repository.AdminRepository;
import repository.CourseRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCoursePageController implements Initializable {
    ObservableList<Courses> listC;
    int index = -1;
    AdminRepository adminRepository = new AdminRepository();
    @FXML
    private Label successLabel;
    @FXML
    private TextField deleteTeacherIDField;
    @FXML
    private TableColumn<Courses, Integer> col_courseid;
    @FXML
    private TableColumn<Courses, String> col_coursename;
    @FXML
    private TableColumn<Courses, Integer> col_numberofcredits;
    @FXML
    private TableView<Courses> table_courses;

    public void updateTable() {
        col_courseid.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("courseid"));
        col_coursename.setCellValueFactory(new PropertyValueFactory<Courses, String>("course_name"));
        col_numberofcredits.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("number_of_credits"));

        listC = CourseRepository.getDataCourses();
        table_courses.setItems(listC);
    }

    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_courses.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        deleteTeacherIDField.setText(col_courseid.getCellData(index).toString());
        successLabel.setVisible(false);
    }

    @FXML
    void onDeleteCourseButtonClick() {
        Integer courseid = Integer.parseInt(deleteTeacherIDField.getText());
        successLabel.setVisible(true);
        adminRepository.deleteCourse(courseid);
        deleteTeacherIDField.clear();
        updateTable();
    }
}
