package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RidingDAOBase implements RidingDAO {
    private Connection connection;
    private PreparedStatement giveAdmin;
    public RidingDAOBase() {
        try {
            connection =  DriverManager.getConnection("jdbc:sqlite:riding.db");
            giveAdmin  = connection.prepareStatement("select * from admin order by id");
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
}
