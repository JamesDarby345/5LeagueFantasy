package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Goalkeeper extends Player{
    @Column(name = "saves", nullable = false)
    private int saves;
    @Column(name = "cleanSheets", nullable = false)
    private int cleanSheets;

    @OneToMany
    @Column(name = "playersToTeam", nullable = false)
    private List<PlayerToTeam> playersToTeam;

    public Goalkeeper(String name, String team, String position, int gamesPlayed, EuropeanLeague europeanLeague, int saves, int cleanSheets) {
        super(name, team, position, gamesPlayed, europeanLeague);
        this.saves = saves;
        this.cleanSheets = cleanSheets;
    }

    public Goalkeeper() {
        super();
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    public void setCleanSheets(int cleanSheets) {
        this.cleanSheets = cleanSheets;
    }

    public List<PlayerToTeam> getPlayersToTeam() {
        return playersToTeam;
    }

    public void setPlayersToTeam(List<PlayerToTeam> playersToTeam) {
        this.playersToTeam = playersToTeam;
    }
}
