package com.example.LeagueFantasy.util;

// import com.example.LeagueFantasy.entity.Player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
  public static List<Root> loadData() {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Root> roots = new ArrayList<>();
    File file = new File("src/main/resources/players.txt");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        Root root = objectMapper.readValue(line, Root.class);
        roots.add(root);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return roots;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Root {
    @JsonProperty("response")
    public List<PlayerInfo> response;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PlayerInfo {
    public Player player;
    public List<Statistic> statistics;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Player {
    public int id;
    public String name;
    public String firstname;
    public String lastname;
    public String age;
    public Birth birth;
    public String nationality;
    public String height;
    public String weight;
    public String injured;
    // Add other fields as necessary
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Birth {
    public String date;
    public String place;
    public String country;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Statistic {
    // Define statistic fields
    public Team team;
    public League league;
    public Games games;
    public Substitutes substitutes;
    public Shots shots;
    public Goals goals;
    public Passes passes;
    public Tackles tackles;
    public Duels duels;
    public Dribbles dribbles;
    public Fouls fouls;
    public Cards cards;
    public Penalty penalty;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Team {
    public int id;
    public String name;
    public String logo;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class League {
    public int id;
    public String name;
    public String country;
    public String logo;
    public String flag;
    public String season;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Games {
    public int appearences;
    public int lineups;
    public int minutes;
    public int number;
    public String position;
    public String rating;
    public boolean captain;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Substitutes {
    public int in;
    public int out;
    public int bench;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Shots {
    public int total;
    public int on;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Goals {
    public int total;
    public int conceded;
    public int assists;
    public int saves;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Passes {
    public int total;
    public int key;
    public int accuracy;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Tackles {
    public int total;
    public int blocks;
    public int interceptions;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Duels {
    public int total;
    public int won;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Dribbles {
    public int attempts;
    public int success;
    public int past;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Fouls {
    public int drawn;
    public int committed;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Cards {
    public int yellow;
    public int yellowred;
    public int red;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Penalty {
    public int won;
    public int commited;
    public int scored;
    public int missed;
    public int saved;
  }
}
