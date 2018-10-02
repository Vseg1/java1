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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> gvalues = new ArrayList<String>();
        gvalues.add("Male");
        gvalues.add("Female");
        ObservableList<String> gender = FXCollections.observableArrayList(gvalues);
        sgender.setItems(gender);
    }
}