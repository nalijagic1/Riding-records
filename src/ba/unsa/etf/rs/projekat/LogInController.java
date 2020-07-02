package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LogInController {
    public TextField fldPassword;
    public TextField fldMail;
    private RidingDAOBase dao = new RidingDAOBase();

    public void guest(ActionEvent actionEvent) {
        Stage login=(Stage)fldMail.getScene().getWindow();
        login.close();
    }

    public void logIn(ActionEvent actionEvent) {
        ObservableList<Admin> admins = dao.getAdmin();
        int j = 0;
        for(int i = 0;i <admins.size();i++){
            if((fldMail.getText().equals(admins.get(i).getUsername() )|| fldMail.getText().equals(admins.get(i).getMail())) && fldPassword.getText().equals(admins.get(i).getPassword())){
                j++;
                break;
            }
        }
        if(j == 0){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("You inputed wrong log in information! Please try again or log in as a guest.");
            error.setTitle("Wrong information");
            error.show();
        }else{
            Stage login=(Stage)fldMail.getScene().getWindow();
            login.close();
        }

    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/changePassword.fxml"));
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        stage.show();
    }
}
