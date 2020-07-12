package ba.unsa.etf.rs.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Horse {
    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty breed;
    SimpleStringProperty origin;
    SimpleIntegerProperty age;
    SimpleStringProperty color;
    SimpleStringProperty picture;
    SimpleStringProperty description;
    SimpleObjectProperty<Event> event;

    public Horse(int id, String name, String breed, String origin, int age, String color, String picture, String description, Event event) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.origin = new SimpleStringProperty(origin);
        this.age = new SimpleIntegerProperty(age);
        this.color = new SimpleStringProperty(color);
        this.picture = new SimpleStringProperty(picture);
        this.description = new SimpleStringProperty(description);
        this.event = new SimpleObjectProperty<>(event);
    }
    public Horse(int id, String name, String breed, String origin, int age, String color,String picture,Event event) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.origin = new SimpleStringProperty(origin);
        this.age = new SimpleIntegerProperty(age);
        this.color = new SimpleStringProperty(color);
        this.picture = new SimpleStringProperty(picture);
        this.description = new SimpleStringProperty("");
        this.event = new SimpleObjectProperty<>(event);
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

    public String getBreed() {
        return breed.get();
    }

    public SimpleStringProperty breedProperty() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed.set(breed);
    }

    public String getOrigin() {
        return origin.get();
    }

    public SimpleStringProperty originProperty() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Event getEvent() {
        return event.get();
    }

    public SimpleObjectProperty<Event> eventProperty() {
        return event;
    }

    public void setEvent(Event event) {
        this.event.set(event);
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

    @Override
    public String toString() {
        return  getName();
    }
}
