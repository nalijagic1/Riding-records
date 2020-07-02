package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RidingDAOBase implements RidingDAO {
    private Connection connection;
    private PreparedStatement giveAdmin,changePass;
    public RidingDAOBase() {
        try {
            connection =  DriverManager.getConnection("jdbc:sqlite:riding.db");
            giveAdmin  = connection.prepareStatement("select * from admin order by id");
            changePass = connection.prepareStatement("Update admin set password=? where id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ObservableList<Admin> getAdmin() {
        ObservableList<Admin> owner = FXCollections.observableArrayList();
        try {
            ResultSet r = giveAdmin.executeQuery();
            while(r.next()) {
                Admin a = new Admin(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6));
                owner.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    @Override
    public void changePassword(Admin a) {
        try {
            changePass.setInt(2,a.getId());
            changePass.setString(1,a.getPassword());
            changePass.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
