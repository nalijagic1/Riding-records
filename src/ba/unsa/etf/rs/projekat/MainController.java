package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Tab eventInfo;
    public Tab ridingInfo;
    public Tab equipmentInfo;
    public Tab horseInfo;
    public Tab trainerInfo;
    public Tab riderInfo;
    public ListView<Rider> ridersList;
    public TextField membershipRider;
    public TextField birthRider;
    public TextField jmbgRider;
    public ImageView pictureRider;
    public TextField surnameRider;
    public TextField nameRider;
    private RidingDAO dao =new RidingDAOBase();


    private static ImageView buildImage(String imgPatch) {
        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        //You can set width and height
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setImage(i);
        return imageView;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //postavljanje ikonica na tabove
       eventInfo.setGraphic(buildImage("/img/trophy.png"));
        riderInfo.setGraphic(buildImage("/img/rider.png"));
        trainerInfo.setGraphic(buildImage("/img/trainer.png"));
        horseInfo.setGraphic(buildImage("/img/horse.png"));
        ridingInfo.setGraphic(buildImage("/img/horse-ride.png"));
        equipmentInfo.setGraphic(buildImage("/img/equipment.png"));
        //tab Riders
        ridersList.setItems(dao.getRiders());
        ridersList.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(newKorisnik==null){
                nameRider.setText("");
                surnameRider.setText("");
                birthRider.setText("");
                membershipRider.setText("");
                jmbgRider.setText("");
                pictureRider.setImage(null);
            }else{
                nameRider.setText(newKorisnik.getName());
                surnameRider.setText(newKorisnik.getSurname());
                birthRider.setText(newKorisnik.getDateOfBirth().toString());
                membershipRider.setText(newKorisnik.getDateOfMembership().toString());
                jmbgRider.setText(newKorisnik.getJmbg());
                pictureRider.setImage(new Image(newKorisnik.getPicture()));
            }

        });
    }

    public void riderDelete(ActionEvent actionEvent) {
        Rider r =ridersList.getSelectionModel().getSelectedItem();
        if(r!=null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to delete selected rider?");
            confirm.setTitle("Delete");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deleteRider(r);
                confirm.close();
            } else {
                confirm.close();

            }
            ridersList.getSelectionModel().selectFirst();
            ridersList.setItems(dao.getRiders());
        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No rider was selected!");
            error.setTitle("Deleteing feiled");
            error.show();
        }

    }
}
