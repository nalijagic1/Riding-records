package ba.unsa.etf.rs.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditRiderController implements Initializable {
    public ImageView pictureView;
    public DatePicker dpBirth;
    public DatePicker dpMembership;
    public TextField jmbgfield;
    public TextField surnameField;
    public TextField nameField;
    private Rider r;
    public boolean help = false;
    RidingDAOBase dao = new RidingDAOBase();
    public EditRiderController(Rider rider) {
        r = rider;
    }

    public Rider getRider() {
        return r;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pictureView.setImage(new Image("/img/johnDoe.png"));
        if(r != null){
            pictureView.setImage(new Image(r.getPicture()));
            dpBirth.setValue(r.getDateOfBirth());
            dpMembership.setValue(r.getDateOfMembership());
            nameField.setText(r.getName());
            surnameField.setText(r.getSurname());
            jmbgfield.setText(r.getJmbg());
        }
    }
    public void addPicture(ActionEvent actionEvent) {
        FileChooser file = new FileChooser();
        file.setTitle("Select riders picture");
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pictures",".png"));
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pictures JPG",".jpg"));
        File picture = file.showOpenDialog(nameField.getScene().getWindow());
        pictureView.setImage(new Image(picture.getPath()));
    }
    private boolean checkJMBG(LocalDate birth, String jmbg){
        boolean same = true;
        String maticni = "";
        if(birth.getDayOfMonth() <= 9) maticni += 0 + String.valueOf(birth.getDayOfMonth());
        else maticni+= String.valueOf(birth.getDayOfMonth());
        if(birth.getMonth().getValue() <= 9)maticni += 0 + birth.getMonth().getValue();
        else maticni += birth.getMonth().getValue();
        maticni += String.valueOf(birth.getYear()).substring(1);
        if(!maticni.equals(jmbg.substring(0,7))) same = false;
        /*The checksum is calculated from the mapping DDMMYYYRRBBBK = abcdefghijklm,
        using the formula: m = 11 − (( 7*(a+g) + 6*(b+h) + 5*(c+i) + 4*(d+j) + 3*(e+k) + 2*(f+l) ) mod 11)*/
        int m = 11 - ((7 * (Integer.parseInt(String.valueOf(jmbg.charAt(0))) + Integer.parseInt(String.valueOf(jmbg.charAt(6))))+6*(Integer.parseInt(String.valueOf(jmbg.charAt(1)))+Integer.parseInt(String.valueOf(jmbg.charAt(7))))+5*(Integer.parseInt(String.valueOf(jmbg.charAt(2)))+Integer.parseInt(String.valueOf(jmbg.charAt(8))))+4*(Integer.parseInt(String.valueOf(jmbg.charAt(3)))+Integer.parseInt(String.valueOf(jmbg.charAt(9))))+3*(Integer.parseInt(String.valueOf(jmbg.charAt(4)))+Integer.parseInt(String.valueOf(jmbg.charAt(10))))+2*(Integer.parseInt(String.valueOf(jmbg.charAt(5)))+Integer.parseInt(String.valueOf(jmbg.charAt(11)))))%11);
        if(m >= 10) m = 0;
        if(m != Integer.parseInt(String.valueOf(jmbg.charAt(12)))) same = false;
        return same;
    }
    public void okay(ActionEvent actionEvent) {
        boolean isDataValid = true;
        if(nameField.getText().isEmpty() || nameField.getText().length()<=1) {
            nameField.getStyleClass().removeAll("right");
            nameField.getStyleClass().add("wrong");
            isDataValid = false;
        }else{
            nameField.getStyleClass().removeAll("wrong");
            nameField.getStyleClass().add("right");
        }
        if(surnameField.getText().isEmpty() || surnameField.getText().length()<=1) {
            surnameField.getStyleClass().removeAll("right");
            surnameField.getStyleClass().add("wrong");
            isDataValid = false;
        }else{
            surnameField.getStyleClass().removeAll("wrong");
            surnameField.getStyleClass().add("right");
        }

        if(dpBirth.getValue().isAfter(LocalDate.now())){
            dpBirth.getStyleClass().removeAll("right");
            dpBirth.getStyleClass().add("wrong");
            isDataValid = false;
        }else{
            dpBirth.getStyleClass().removeAll("wrong");
            dpBirth.getStyleClass().add("right");
        }
        if(dpMembership.getValue().isAfter(LocalDate.now())){
            dpMembership.getStyleClass().removeAll("right");
            dpMembership.getStyleClass().add("wrong");
            isDataValid = false;
        }else {
            dpMembership.getStyleClass().removeAll("wrong");
            dpMembership.getStyleClass().add("right");
        }
        if(pictureView.getImage().getUrl().equals("/img/johnDoe.png")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No picture was selected!");
            error.setTitle("Missing information");
            error.show();
            isDataValid = false;
        }
        if(jmbgfield.getText().length() != 13 || checkJMBG(dpBirth.getValue(),jmbgfield.getText())){
            jmbgfield.getStyleClass().removeAll("right");
            jmbgfield.getStyleClass().add("wrong");
            isDataValid = false;
        }else {
            jmbgfield.getStyleClass().removeAll("wrong");
            jmbgfield.getStyleClass().add("right");
        }
        if(isDataValid){
            if(r == null) r = new Rider(1,nameField.getText(),surnameField.getText(),dpBirth.getValue(),dpMembership.getValue(),jmbgfield.getText(),pictureView.getImage().getUrl());

            else r = new Rider(r.getId(),nameField.getText(),surnameField.getText(),dpBirth.getValue(),dpMembership.getValue(),jmbgfield.getText(),pictureView.getImage().getUrl());
            Stage edit_add= (Stage)nameField.getScene().getWindow();
            edit_add.close();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        r = null;
        Stage edit_add= (Stage)nameField.getScene().getWindow();
        edit_add.close();
    }


}
