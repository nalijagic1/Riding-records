package ba.unsa.etf.rs.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class RidingDAOBase implements RidingDAO {
    private Connection connection;
    private PreparedStatement giveAdmin,changePass, giveRiders, addRider, deleteRider, giveTrainers,deleteTrainer,giveEvents,giveHorses,deleteHorse;
    public RidingDAOBase() {
        try {
            connection =  DriverManager.getConnection("jdbc:sqlite:riding.db");
            giveAdmin  = connection.prepareStatement("select * from admin order by id");
            giveRiders = connection.prepareStatement("select * from riders order by surname");
            addRider =  connection.prepareStatement("Insert into riders values (?,?,?,?,?,?,?)");
            giveTrainers = connection.prepareStatement("select * from trainers");
            giveEvents = connection.prepareStatement("select * from events");
            giveHorses = connection.prepareStatement("select * from horses order by age desc ");
            changePass = connection.prepareStatement("Update admin set password=? where id=?");
            deleteRider = connection.prepareStatement("Delete from riders where id=? ");
            deleteTrainer = connection.prepareStatement("Delete from trainers where trainer_id=?");
            deleteHorse = connection.prepareStatement("Delete from horses where id=?");
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

        try {
            PreparedStatement newRiderStatement=connection.prepareStatement("Select MAX(id)+1 from riders ");
            ResultSet result = newRiderStatement.executeQuery();
            if(result.next()){
                r.setId(result.getInt(1));
            }else{
                r.setId(1);
            }
            addRider.setInt(1,r.getId());
            addRider.setString(2,r.getName());
            addRider.setString(3,r.getSurname());
            addRider.setDate(4, Date.valueOf(r.getDateOfBirth()));
            addRider.setDate(5, Date.valueOf(r.getDateOfMembership()));
            addRider.setString(6,r.getJmbg());
            addRider.setString(7,r.getPicture());
            addRider.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public ObservableList<Event> getEvents() {
        ObservableList<Event> events = FXCollections.observableArrayList();
        try {
            ResultSet r = giveEvents.executeQuery();
            while(r.next()) {
                Event a = new Event(r.getInt(1), r.getString(2));
                events.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public ObservableList<Horse> getHorse() {
        ObservableList<Horse> horses =FXCollections.observableArrayList();
        try {
            ResultSet r = giveHorses.executeQuery();
            while(r.next()) {
                Event e = new Event(1,"");
                for(int i=0;i<getEvents().size();i++){
                    if(getEvents().get(i).getId() == r.getInt(9)) e = getEvents().get(i);
                }
                Horse a = new Horse(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5),r.getString(6),r.getString(7),r.getString(8),e);
                horses.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horses;
    }

    @Override
    public void editHorse(Horse h) {

    }

    @Override
    public void deleteHorse(Horse h) {
        try {
            deleteHorse.setInt(1,h.getId());
            deleteHorse.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHorse(Horse h) {

    }
}
