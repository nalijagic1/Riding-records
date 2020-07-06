package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Trainer {
    SimpleIntegerProperty id;
    SimpleObjectProperty<Rider> rider;
    SimpleIntegerProperty salary;

    public Trainer(int id, Rider rider, int salary) {
        this.id = new SimpleIntegerProperty(id);
        this.rider = new SimpleObjectProperty<>(rider);
        this.salary = new SimpleIntegerProperty(salary);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Rider getRider() {
        return rider.get();
    }

    public SimpleObjectProperty<Rider> riderProperty() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider.set(rider);
    }

    public int getSalary() {
        return salary.get();
    }

    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    @Override
    public String toString() {
        return getRider().getSurname() + " " + getRider().getName();
    }
}
