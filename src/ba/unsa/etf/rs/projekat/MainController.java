package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    public ListView<Trainer> trainerList;
    public TextField nameTrainer;
    public TextField surnameTrainer;
    public ImageView pictureTrainer;
    public TextField jmbgTrainer;
    public TextField birthTrainer;
    public TextField membershipTrainer;
    public TextField salaryTrainer;

    public ListView<Horse> horseList;
    public TextField nameHorse;
    public TextField breedHorse;
    public ImageView pictureHorse;
    public TextField colorHorse;
    public TextField originHorse;
    public TextField ageHorse;
    public TextArea descriptionHorse;
    public TextField eventHorse;
    public ImageView logoImage;
    public VBox infoBox;
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
        logoImage.setImage(new Image("/img/logo hidalgo png.png"));
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
        //tab Trainer
        trainerList.setItems(dao.getTrainers());
        trainerList.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(newKorisnik==null){
                nameTrainer.setText("");
                surnameTrainer.setText("");
                birthTrainer.setText("");
                membershipTrainer.setText("");
                jmbgTrainer.setText("");
                pictureTrainer.setImage(null);
                salaryTrainer.setText("");
            }else{
                nameTrainer.setText(newKorisnik.getRider().getName());
                surnameTrainer.setText(newKorisnik.getRider().getSurname());
                birthTrainer.setText(newKorisnik.getRider().getDateOfBirth().toString());
                membershipTrainer.setText(newKorisnik.getRider().getDateOfMembership().toString());
                jmbgTrainer.setText(newKorisnik.getRider().getJmbg());
                pictureTrainer.setImage(new Image(newKorisnik.getRider().getPicture()));
                salaryTrainer.setText(String.valueOf(newKorisnik.getSalary()));
            }
        });
        //tab Horses
        horseList.setItems(dao.getHorse());
        horseList.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(newKorisnik==null){
                nameHorse.setText("");
                breedHorse.setText("");
                originHorse.setText("");
                descriptionHorse.setText("");
                eventHorse.setText("");
                pictureHorse.setImage(null);
                ageHorse.setText("");
                colorHorse.setText("");
            }else{
                nameHorse.setText(newKorisnik.getName());
                breedHorse.setText(newKorisnik.getBreed());
                originHorse.setText(newKorisnik.getOrigin());
                descriptionHorse.setText(newKorisnik.getDescription());
                eventHorse.setText(newKorisnik.getEvent().getName());
                pictureHorse.setImage(new Image(newKorisnik.getPicture()));
                ageHorse.setText(String.valueOf(newKorisnik.getAge()));
                colorHorse.setText(newKorisnik.getColor());
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
                int j = 0;
                for(int i =0;i<dao.getTrainers().size();i++){
                    if(dao.getTrainers().get(i).getRider().getId() == r.getId()){
                        j++;
                    }
                }
                if(j == 0){
                    dao.deleteRider(r);
                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("You cannot delete rider who is a trainer");
                    error.setTitle("Deleteing failed");
                    error.show();
                }
            }
            confirm.close();
            ridersList.getSelectionModel().selectFirst();
            ridersList.setItems(dao.getRiders());

        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No rider was selected!");
            error.setTitle("Deleteing failed");
            error.show();
        }

    }

    public void trainerDelete(ActionEvent actionEvent) {
        Trainer t =trainerList.getSelectionModel().getSelectedItem();
        if(t!=null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to delete selected trainer?");
            confirm.setTitle("Delete");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deleteTrainer(t);
            }
            confirm.close();
            trainerList.getSelectionModel().selectFirst();
            trainerList.setItems(dao.getTrainers());

        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No trainer was selected!");
            error.setTitle("Deleteing failed");
            error.show();
        }
    }

    public void horseDelete(ActionEvent actionEvent) {
        Horse h = horseList.getSelectionModel().getSelectedItem();
        if(h!=null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to delete selected horse?");
            confirm.setTitle("Delete");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deleteHorse(h);
            }
            confirm.close();
            horseList.getSelectionModel().selectFirst();
            horseList.setItems(dao.getHorse());

        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No horse was selected!");
            error.setTitle("Deleteing failed");
            error.show();
        }
    }

    public void addRider(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        EditRiderController cont=new EditRiderController(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editRider.fxml"));
        loader.setController(cont);
        Parent root = loader.load();
        stage.setTitle("Add Book");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.showAndWait();
        if(cont.getRider() != null) dao.addRider(cont.getRider());
        ridersList.setItems(dao.getRiders());

    }
}
