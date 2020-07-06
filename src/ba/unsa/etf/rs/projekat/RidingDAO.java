package ba.unsa.etf.rs.projekat;

import javafx.collections.ObservableList;

public interface RidingDAO {
    ObservableList<Admin> getAdmin();
    void changePassword(Admin a);
    ObservableList<Rider> getRiders();
    void editRider(Rider r);
    void deleteRider(Rider r);
    void addRider(Rider r);
    ObservableList<Trainer> getTrainers();
    void editTrainer(Trainer t);
    void deleteTrainer(Trainer t);
    void addTrainer(Trainer t);
}
