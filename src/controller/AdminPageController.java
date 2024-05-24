package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.UserSession;

import java.io.IOException;

public class AdminPageController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button addNewStudentButton;
    @FXML
    private Button addNewTeacherButton;
    @FXML
    private Button addNewCourseButton;
    @FXML
    private Button deleteTeacherButton;
    @FXML
    private Button deleteStudentButton;
    @FXML
    private Button deleteCourseButton;
    @FXML
    private Button addCourseToStudentButton;
    @FXML
    private Button addCourseToTeacherButton;
    @FXML
    private Button addNewClassButton;
    @FXML
    private Button addNewDepartmentButton;
    @FXML
    private Button deleteClassButton;
    @FXML
    private Button deleteDepartmentButton;
    @FXML
    private Button backButton;
    @FXML
    private Button removeCourseFromStudentButton;
    @FXML
    private Button removeCourseFromTeacherButton;
    @FXML
    private Button addStudentToClassButton;
    @FXML
    private Button addTeacherToClassButton;
    @FXML
    private Button removeStudentFromClassButton;
    @FXML
    private Button removeTeacherFromClassButton;
    @FXML
    private Button insertNewRecordsInDatabaseButton;
    @FXML
    private Button deleteRecordsFromDatabaseButton;
    @FXML
    private Button manageStudentsButton;
    @FXML
    private Button manageTeachersButton;

    public void onAddNewStudentButtonClick() throws Exception {
        Stage addNewStudentStage = new Stage();
        addNewStudentStage.initModality(Modality.APPLICATION_MODAL);
        addNewStudentStage.initOwner((Stage) addNewStudentButton.getScene().getWindow());

        Scene addNewStudentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-new-student-page.fxml")));
        addNewStudentStage.setScene(addNewStudentScene);

        addNewStudentStage.show();
    }

    public void onAddNewTeacherButtonClick() throws Exception {
        Stage addNewTeacherStage = new Stage();
        addNewTeacherStage.initModality(Modality.APPLICATION_MODAL);
        addNewTeacherStage.initOwner((Stage) addNewTeacherButton.getScene().getWindow());

        Scene addNewTeacherScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-new-teacher-page.fxml")));
        addNewTeacherStage.setScene(addNewTeacherScene);

        addNewTeacherStage.show();
    }

    public void onAddNewCourseButtonClick() throws Exception {
        Stage addNewCourseStage = new Stage();
        addNewCourseStage.initModality(Modality.APPLICATION_MODAL);
        addNewCourseStage.initOwner((Stage) addNewCourseButton.getScene().getWindow());

        Scene addNewCourseScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-new-course-page.fxml")));
        addNewCourseStage.setScene(addNewCourseScene);

        addNewCourseStage.show();
    }

    public void onDeleteStudentButtonClick() throws Exception {
        Stage deleteStudentStage = new Stage();
        deleteStudentStage.initModality(Modality.APPLICATION_MODAL);
        deleteStudentStage.initOwner((Stage) deleteStudentButton.getScene().getWindow());

        Scene deleteStudentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/delete-student-page.fxml")));
        deleteStudentStage.setScene(deleteStudentScene);

        deleteStudentStage.show();
    }

    public void onDeleteTeacherButtonClick() throws Exception {
        Stage deleteTeacherStage = new Stage();
        deleteTeacherStage.initModality(Modality.APPLICATION_MODAL);
        deleteTeacherStage.initOwner((Stage) deleteTeacherButton.getScene().getWindow());

        Scene deleteTeacherScene = new Scene(FXMLLoader.load(getClass().getResource("/view/delete-teacher-page.fxml")));
        deleteTeacherStage.setScene(deleteTeacherScene);

        deleteTeacherStage.show();
    }

    public void onDeleteCourseButtonClick() throws Exception {
        Stage deleteCourseStage = new Stage();
        deleteCourseStage.initModality(Modality.APPLICATION_MODAL);
        deleteCourseStage.initOwner((Stage) deleteCourseButton.getScene().getWindow());

        Scene deleteCourseScene = new Scene(FXMLLoader.load(getClass().getResource("/view/delete-course-page.fxml")));
        deleteCourseStage.setScene(deleteCourseScene);

        deleteCourseStage.show();
    }

    public void onAddCourseToStudentButtonClick() throws Exception {
        Stage addCourseToStudentStage = new Stage();
        addCourseToStudentStage.initModality(Modality.APPLICATION_MODAL);
        addCourseToStudentStage.initOwner((Stage) addCourseToStudentButton.getScene().getWindow());

        Scene addCourseToStudentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-course-to-student-page.fxml")));
        addCourseToStudentStage.setScene(addCourseToStudentScene);

        addCourseToStudentStage.show();
    }

    public void onAddCourseToTeacherButtonClick() throws Exception {
        Stage addCourseToTeacherStage = new Stage();
        addCourseToTeacherStage.initModality(Modality.APPLICATION_MODAL);
        addCourseToTeacherStage.initOwner((Stage) addCourseToTeacherButton.getScene().getWindow());

        Scene addCourseToTeacherScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-course-to-teacher-page.fxml")));
        addCourseToTeacherStage.setScene(addCourseToTeacherScene);

        addCourseToTeacherStage.show();
    }

    @FXML
    public void onLogOutButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login-page.fxml"));

        Stage window = (Stage) logOutButton.getScene().getWindow();
        window.setTitle("Login Page");
        window.setScene(new Scene(root, 800, 440));

        UserSession.clear();
    }

    @FXML
    public void onAddNewClassButtonClick() throws IOException {
        Stage addNewClassStage = new Stage();
        addNewClassStage.initModality(Modality.APPLICATION_MODAL);
        addNewClassStage.initOwner((Stage) addNewClassButton.getScene().getWindow());

        Scene addNewClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-new-class-page.fxml")));
        addNewClassStage.setScene(addNewClassScene);

        addNewClassStage.show();
    }

    @FXML
    public void onDeleteClassButtonClick() throws IOException {
        Stage deleteClassStage = new Stage();
        deleteClassStage.initModality(Modality.APPLICATION_MODAL);
        deleteClassStage.initOwner((Stage) deleteClassButton.getScene().getWindow());

        Scene deleteClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/delete-class-page.fxml")));
        deleteClassStage.setScene(deleteClassScene);

        deleteClassStage.show();
    }

    @FXML
    public void onAddNewDepartmentButtonClick() throws IOException {
        Stage addNewDepartmentStage = new Stage();
        addNewDepartmentStage.initModality(Modality.APPLICATION_MODAL);
        addNewDepartmentStage.initOwner((Stage) addNewDepartmentButton.getScene().getWindow());

        Scene addNewDepartmentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-new-department-page.fxml")));
        addNewDepartmentStage.setScene(addNewDepartmentScene);

        addNewDepartmentStage.show();
    }

    @FXML
    public void onDeleteDepartmentButtonClick() throws IOException {
        Stage deleteDepartmentStage = new Stage();
        deleteDepartmentStage.initModality(Modality.APPLICATION_MODAL);
        deleteDepartmentStage.initOwner((Stage) deleteDepartmentButton.getScene().getWindow());

        Scene deleteDepartmentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/delete-department-page.fxml")));
        deleteDepartmentStage.setScene(deleteDepartmentScene);

        deleteDepartmentStage.show();
    }

    @FXML
    public void onInsertNewRecordsInDatabaseButtonClick() {
        manageStudentsButton.setVisible(false);
        manageTeachersButton.setVisible(false);
        insertNewRecordsInDatabaseButton.setVisible(false);
        deleteRecordsFromDatabaseButton.setVisible(false);
        addNewStudentButton.setVisible(true);
        addNewTeacherButton.setVisible(true);
        addNewClassButton.setVisible(true);
        addNewDepartmentButton.setVisible(true);
        addNewCourseButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    public void onDeleteRecordsFromDatabaseButtonClick() {
        manageStudentsButton.setVisible(false);
        manageTeachersButton.setVisible(false);
        insertNewRecordsInDatabaseButton.setVisible(false);
        deleteRecordsFromDatabaseButton.setVisible(false);
        deleteStudentButton.setVisible(true);
        deleteTeacherButton.setVisible(true);
        deleteCourseButton.setVisible(true);
        deleteClassButton.setVisible(true);
        deleteDepartmentButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    public void onManageStudentsButtonClick() {
        manageStudentsButton.setVisible(false);
        manageTeachersButton.setVisible(false);
        insertNewRecordsInDatabaseButton.setVisible(false);
        deleteRecordsFromDatabaseButton.setVisible(false);
        addCourseToStudentButton.setVisible(true);
        addStudentToClassButton.setVisible(true);
        removeCourseFromStudentButton.setVisible(true);
        removeStudentFromClassButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    public void onManageTeachersButtonClick() {
        manageStudentsButton.setVisible(false);
        manageTeachersButton.setVisible(false);
        insertNewRecordsInDatabaseButton.setVisible(false);
        deleteRecordsFromDatabaseButton.setVisible(false);
        removeCourseFromTeacherButton.setVisible(true);
        removeTeacherFromClassButton.setVisible(true);
        addTeacherToClassButton.setVisible(true);
        addCourseToTeacherButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    public void onBackButtonClick() {
        manageStudentsButton.setVisible(true);
        manageTeachersButton.setVisible(true);
        insertNewRecordsInDatabaseButton.setVisible(true);
        deleteRecordsFromDatabaseButton.setVisible(true);
        addNewStudentButton.setVisible(false);
        addNewTeacherButton.setVisible(false);
        addNewCourseButton.setVisible(false);
        addNewClassButton.setVisible(false);
        addNewDepartmentButton.setVisible(false);
        deleteClassButton.setVisible(false);
        deleteCourseButton.setVisible(false);
        deleteStudentButton.setVisible(false);
        deleteTeacherButton.setVisible(false);
        deleteDepartmentButton.setVisible(false);
        addCourseToStudentButton.setVisible(false);
        addCourseToTeacherButton.setVisible(false);
        removeCourseFromTeacherButton.setVisible(false);
        removeCourseFromStudentButton.setVisible(false);
        addStudentToClassButton.setVisible(false);
        addTeacherToClassButton.setVisible(false);
        removeStudentFromClassButton.setVisible(false);
        removeTeacherFromClassButton.setVisible(false);
        backButton.setVisible(false);
    }

    @FXML
    public void onRemoveCourseFromStudentButtonClick() throws IOException {
        Stage removeCourseFromStudentStage = new Stage();
        removeCourseFromStudentStage.initModality(Modality.APPLICATION_MODAL);
        removeCourseFromStudentStage.initOwner((Stage) removeCourseFromStudentButton.getScene().getWindow());

        Scene removeCourseFromStudentScene = new Scene(FXMLLoader.load(getClass().getResource("/view/remove-course-from-student-page.fxml")));
        removeCourseFromStudentStage.setScene(removeCourseFromStudentScene);

        removeCourseFromStudentStage.show();
    }

    @FXML
    public void onRemoveCourseFromTeacherButtonClick() throws IOException {
        Stage removeCourseFromTeacherStage = new Stage();
        removeCourseFromTeacherStage.initModality(Modality.APPLICATION_MODAL);
        removeCourseFromTeacherStage.initOwner((Stage) removeCourseFromTeacherButton.getScene().getWindow());

        Scene removeCourseFromTeacherScene = new Scene(FXMLLoader.load(getClass().getResource("/view/remove-course-from-teacher-page.fxml")));
        removeCourseFromTeacherStage.setScene(removeCourseFromTeacherScene);

        removeCourseFromTeacherStage.show();
    }

    @FXML
    public void onAddStudentToClassButtonClick() throws IOException {
        Stage addStudentToClassStage = new Stage();
        addStudentToClassStage.initModality(Modality.APPLICATION_MODAL);
        addStudentToClassStage.initOwner((Stage) addStudentToClassButton.getScene().getWindow());

        Scene addStudentToClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-student-to-class-page.fxml")));
        addStudentToClassStage.setScene(addStudentToClassScene);

        addStudentToClassStage.show();
    }

    @FXML
    public void onAddTeacherToClassButtonClick() throws IOException {
        Stage addTeacherToClassStage = new Stage();
        addTeacherToClassStage.initModality(Modality.APPLICATION_MODAL);
        addTeacherToClassStage.initOwner((Stage) addTeacherToClassButton.getScene().getWindow());

        Scene addTeacherToClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/add-teacher-to-class-page.fxml")));
        addTeacherToClassStage.setScene(addTeacherToClassScene);

        addTeacherToClassStage.show();
    }

    @FXML
    public void onRemoveStudentFromClassButtonClick() throws IOException {
        Stage removeStudentFromClassStage = new Stage();
        removeStudentFromClassStage.initModality(Modality.APPLICATION_MODAL);
        removeStudentFromClassStage.initOwner((Stage) removeStudentFromClassButton.getScene().getWindow());

        Scene removeStudentFromClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/remove-student-from-class-page.fxml")));
        removeStudentFromClassStage.setScene(removeStudentFromClassScene);

        removeStudentFromClassStage.show();
    }

    @FXML
    public void onRemoveTeacherFromClassButtonClick() throws IOException {
        Stage removeTeacherFromClassStage = new Stage();
        removeTeacherFromClassStage.initModality(Modality.APPLICATION_MODAL);
        removeTeacherFromClassStage.initOwner((Stage) removeTeacherFromClassButton.getScene().getWindow());

        Scene removeTeacherFromClassScene = new Scene(FXMLLoader.load(getClass().getResource("/view/remove-teacher-from-class-page.fxml")));
        removeTeacherFromClassStage.setScene(removeTeacherFromClassScene);

        removeTeacherFromClassStage.show();
    }
}
