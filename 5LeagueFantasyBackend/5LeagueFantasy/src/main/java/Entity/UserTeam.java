package Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class UserTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "points", nullable = false)
    private int points;
    @Column(name = "weekStartDate", nullable = false)
    private Date weekStartDate;
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @ManyToOne
    @Column(name = "user", nullable = false)
    private User user;

    public UserTeam(String name, int points, Date weekStartDate, Boolean isActive) {
        this.name = name;
        this.points = points;
        this.weekStartDate = weekStartDate;
        this.isActive = isActive;
    }

    public UserTeam() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
