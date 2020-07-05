package ba.unsa.etf.rs.projekat;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Rider {
    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty surname;
    SimpleObjectProperty<LocalDate> dateOfBirth;
    SimpleObjectProperty<LocalDate> dateOfMembership;
    SimpleStringProperty jmbg;
    SimpleStringProperty picture;

    public Rider(int id, String name, String surname, LocalDate dateOfBirth, LocalDate dateOfMembership, String jmbg,String picture) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.dateOfMembership = new SimpleObjectProperty<>(dateOfMembership);
        this.jmbg = new SimpleStringProperty(jmbg);
        this.picture = new SimpleStringProperty(picture);
    }

    public String getPicture() {
        return picture.get();
    }

    public SimpleStringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfMembershipProperty() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership.set(dateOfMembership);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public SimpleStringProperty jmbgProperty() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg.set(jmbg);
    }

    @Override
    public String toString() {
        return getSurname() + " " + getName();
    }
}
