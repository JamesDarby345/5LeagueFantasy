package com.example.LeagueFantasy.dto;

public class LeagueResponseDto {
      private int id;
      private String name;
      private String leagueOwnerUsername;
      private String message;

      public LeagueResponseDto(int id, String name, String leagueOwnerUsername, String message) {
            this.id = id;
            this.name = name;
            this.leagueOwnerUsername = leagueOwnerUsername;
            this.message = message;
      }

      // Getters and Setters
      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getLeagueOwnerName() {
            return leagueOwnerUsername;
      }

      public void setLeagueOwnerName(String leagueOwnerName) {
            this.leagueOwnerUsername = leagueOwnerUsername;
      }

      public String getMessage() {
            return message;
      }
}
