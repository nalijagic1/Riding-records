package ba.unsa.etf.rs.projekat;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePasswordController {
    public PasswordField repPass;
    public PasswordField newPass;
    public TextField userName;
    private RidingDAO d= new RidingDAOBase();

    public void change(ActionEvent actionEvent) {
        Admin a = d.getAdmin().get(0);
        boolean valid = true;
        if(!a.getUsername().equals(userName.getText())) {
            userName.getStyleClass().removeAll("right");
            userName.getStyleClass().add("wrong");
            valid = false;
        }else{
            userName.getStyleClass().removeAll("wrong");
            userName.getStyleClass().add("right");
        }
        if(!newPass.getText().equals(repPass.getText()) || newPass.getText().isEmpty() || repPass.getText().isEmpty()) {
            newPass.getStyleClass().removeAll("right");
            newPass.getStyleClass().add("wrong");
            repPass.getStyleClass().removeAll("right");
            repPass.getStyleClass().add("wrong");
            valid = false;
        }else{
            newPass.getStyleClass().removeAll("wrong");
            newPass.getStyleClass().add("right");
            repPass.getStyleClass().removeAll("wrong");
            repPass.getStyleClass().add("right");
        }
        if(valid){
            Admin novi = new Admin(a.getId(),a.getName(),a.getSurname(),a.getUsername(),a.getMail(),newPass.getText());
            d.changePassword(novi);
            Stage change=(Stage)repPass.getScene().getWindow();
            change.close();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Stage change=(Stage)repPass.getScene().getWindow();
        change.close();
    }
}
