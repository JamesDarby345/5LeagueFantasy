package Entity;

import jakarta.persistence.*;

@MappedSuperclass
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "team", nullable = false)
    private String team;
    @Column(name = "position", nullable = false)
    private String position;
    @Column(name = "gamesPlayed", nullable = false)
    private int gamesPlayed;
    @Column(name = "europeanLeague", nullable = false)
    private EuropeanLeague europeanLeague;

    public Player(String name, String team, String position, int gamesPlayed, EuropeanLeague europeanLeague) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.gamesPlayed = gamesPlayed;
        this.europeanLeague = europeanLeague;
    }

    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public EuropeanLeague getEuropeanLeague() {
        return europeanLeague;
    }

    public void setEuropeanLeague(EuropeanLeague europeanLeague) {
        this.europeanLeague = europeanLeague;
    }
}
