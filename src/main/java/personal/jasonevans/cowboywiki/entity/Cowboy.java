package personal.jasonevans.cowboywiki.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "biography")
    private String biography;

    @NotNull(message = "is required")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private Date birthdate;

    @NotNull(message = "is required")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "death_date")
    private Date deathdate;

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

    public String getPrettyBirthDate(){
        Date date = birthdate;
        DateFormat df = new SimpleDateFormat("MMM d, yyyy");
        String text = df.format(date);

        return text;
    }

    public String getPrettyDeathDate(){
        Date date = deathdate;
        DateFormat df = new SimpleDateFormat("MMM d, yyyy");
        String text = df.format(date);

        return text;
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
