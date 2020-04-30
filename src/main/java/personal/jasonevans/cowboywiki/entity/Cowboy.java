package personal.jasonevans.cowboywiki.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * POJO class to represent the "cowboys" table.
 */
@Entity
@Table(name = "cowboys")
public class Cowboy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "biography")
    private String biography;

    @Column(name = "birth_date")
    private java.sql.Date birthdate;

    @Column(name = "death_date")
    private java.sql.Date deathdate;

    @Column(name = "image_path")
    private String imagePath;

    public Cowboy(){}

    public Cowboy(int id, String firstName, String lastName, String biography, Date birthdate, Date deathdate, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(Date deathdate) {
        this.deathdate = deathdate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Cowboy{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", biography='" + biography + '\'' +
                ", birthdate=" + birthdate +
                ", deathdate=" + deathdate +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}