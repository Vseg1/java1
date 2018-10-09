package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Controller implements Initializable{
    @FXML
    ComboBox sgender;

    @FXML
    ImageView simage;

    @FXML
    ListView slist;

    @FXML
    TextArea scomments;

    @FXML
    TextField smark;

    @FXML
    TextField sname;

    @FXML
    DatePicker sdate;

    @FXML
    Label ImagePath;

    @FXML
    Button sadd;

    @FXML
    Button sdelete;

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        ImagePath.setText(file.getName());
        Image image = new Image(file.toURI().toString());
        simage.setImage(image);
    }

    @FXML
    private void handleDeletePerson() {
        String selectedName = (String) slist.getSelectionModel().getSelectedItem();
        if (manager.removeStudentByName(selectedName)) {
            fetchStudents();
        }
    }

    boolean isAdding = true;

    @FXML
    private void onSave() {
        String selectedName = (String) slist.getSelectionModel().getSelectedItem();
        Student s = new Student();
        if (!isAdding) {
            s = manager.fetchStudentByName(selectedName);
        } else {
            s.setName(sname.getText());
        }
        s.setBirth_date(sdate.getValue());
        s.setComment(scomments.getText());
        s.setGender(sgender.getValue().toString());
        s.setMark(Float.parseFloat(smark.getText()));
        s.setURL(simage.getAccessibleText());
        if (isAdding) {
            System.out.println("Add");
            manager.addStudent(s);
        } else {
            System.out.println("Edit");
            manager.updateStudent(s);
        }
        isAdding = false;
    }

    DBControl manager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manager = new DBControl();

        List<String> gvalues = new ArrayList<String>();
        gvalues.add("Male");
        gvalues.add("Female");
        ObservableList<String> gender = FXCollections.observableArrayList(gvalues);
        sgender.setItems(gender);

        slist.getSelectionModel().selectedItemProperty().addListener(e -> displayStudentDetails((String) slist.getSelectionModel().getSelectedItem()));
        fetchStudents();
    }

    public void fetchStudents() {
        ObservableList<String> students;
        if (manager.loadStudents()!=null) {
            students= FXCollections.observableArrayList(manager.loadStudents());
            slist.setItems(students);}}

    private void displayStudentDetails(String name) {
        try {
            Student s = manager.fetchStudentByName(name);
            sname.setText(s.getName());
            sgender.setValue(s.getGender());
            sdate.setValue(s.getBirth_date());
            Image image = null;

            if (s.getURL() != null) {
                File file = new File(s.getURL());
                ImagePath.setText(file.getName());
                image = new Image(file.toURI().toString());
                simage.setImage(image);
            } else {
                simage.setImage(image);
            }
            smark.setText(String.valueOf(s.getMark()));
            scomments.setText(s.getComment());
            System.out.println(s.getName());
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}