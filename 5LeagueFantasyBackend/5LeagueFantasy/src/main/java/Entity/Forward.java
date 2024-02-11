package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Forward extends User{
    @Column(name = "goals", nullable = false)
    private int goals;
    @Column(name = "assists", nullable = false)
    private int assists;

    @OneToMany
    @Column(name = "playersToTeam", nullable = false)
    private List<PlayerToTeam> playersToTeam;

    public Forward(String username, String name, String email, String password, int goals, int assists) {
        super(username, name, email, password);
        this.goals = goals;
        this.assists = assists;
    }


    public Forward() {
        super();
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public List<PlayerToTeam> getPlayersToTeam() {
        return playersToTeam;
    }

    public void setPlayersToTeam(List<PlayerToTeam> playersToTeam) {
        this.playersToTeam = playersToTeam;
    }
}
