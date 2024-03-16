package com.example.LeagueFantasy.dto;

public class LeagueResponseDto {
      private int id;
      private String name;
      private int leagueOwnerId;
      private String leagueOwnerName;

      public LeagueResponseDto(int id, String name, int leagueOwnerId, String leagueOwnerName) {
            this.id = id;
            this.name = name;
            this.leagueOwnerId = leagueOwnerId;
            this.leagueOwnerName = leagueOwnerName;
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

      public int getLeagueOwnerId() {
            return leagueOwnerId;
      }

      public void setLeagueOwnerId(int leagueOwnerId) {
            this.leagueOwnerId = leagueOwnerId;
      }

      public String getLeagueOwnerName() {
            return leagueOwnerName;
      }

      public void setLeagueOwnerName(String leagueOwnerName) {
            this.leagueOwnerName = leagueOwnerName;
      }
}
