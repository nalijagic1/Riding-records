package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class RidingDAOBase implements RidingDAO {
    private Connection connection;
    private PreparedStatement giveAdmin,changePass, giveRiders, deleteRider, giveTrainers,deleteTrainer;
    public RidingDAOBase() {
        try {
            connection =  DriverManager.getConnection("jdbc:sqlite:riding.db");
            giveAdmin  = connection.prepareStatement("select * from admin order by id");
            giveRiders = connection.prepareStatement("select * from riders order by surname");
            giveTrainers = connection.prepareStatement("select * from trainers");
            changePass = connection.prepareStatement("Update admin set password=? where id=?");
            deleteRider = connection.prepareStatement("Delete from riders where id=? ");
            deleteTrainer = connection.prepareStatement("Delete from trainers where trainer_id=?");
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

    @Override
    public ObservableList<Rider> getRiders() {
        ObservableList<Rider> riders = FXCollections.observableArrayList();
        try {
            ResultSet r = giveRiders.executeQuery();
            while(r.next()) {
                Rider a = new Rider(r.getInt(1), r.getString(2), r.getString(3), r.getDate(4).toLocalDate(), r.getDate(5).toLocalDate(), r.getString(6),r.getString(7));
                riders.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return riders;
    }

    @Override
    public void editRider(Rider r) {

    }

    @Override
    public void deleteRider(Rider r) {
        try {
            deleteRider.setInt(1,r.getId());
            deleteRider.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRider(Rider r) {

    }

    @Override
    public ObservableList<Trainer> getTrainers() {
        ObservableList<Trainer> trainers =FXCollections.observableArrayList();
        try {
            ResultSet r = giveTrainers.executeQuery();
            while(r.next()) {
                Rider ride = new Rider(1,"","", LocalDate.now(),LocalDate.now(),"","");
                for(int i=0;i<getRiders().size();i++){
                    if(getRiders().get(i).getId() == r.getInt(2)) ride = getRiders().get(i);
                }
                Trainer a = new Trainer(r.getInt(1), ride, r.getInt(3));
                trainers.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }

    @Override
    public void editTrainer(Trainer t) {

    }

    @Override
    public void deleteTrainer(Trainer t) {
        try {
            deleteTrainer.setInt(1,t.getId());
            deleteTrainer.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTrainer(Trainer t) {

    }
}
