package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.entity.FantasyManager;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.FantasyManagerRepository;
import com.example.LeagueFantasy.repository.LeagueRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FantasyManagerService {

  @Autowired private FantasyManagerRepository fantasyManagerRepository;

  @Autowired private LeagueRepository leagueRepository;

  @Transactional
  public FantasyManager createFantasyManager(FantasyManager fantasyManagerToCreate) throws  FiveLeagueFantasyException{

    if (fantasyManagerRepository.findByEmail(fantasyManagerToCreate.getEmail()) != null) {
      throw new FiveLeagueFantasyException(
          "User with email address " + fantasyManagerToCreate.getEmail() + " already exists.",
          HttpStatus.CONFLICT);
    } else if (fantasyManagerRepository.findByUsername(fantasyManagerToCreate.getUsername())
        != null) {
      throw new FiveLeagueFantasyException(
          "User with username " + fantasyManagerToCreate.getUsername() + " already exists.",
          HttpStatus.CONFLICT);
    } else if (fantasyManagerToCreate.getPassword() == ""
        || fantasyManagerToCreate.getPassword().length() < 8) {
      throw new FiveLeagueFantasyException(
          "Password must be at least 8 characters long.", HttpStatus.BAD_REQUEST);
    } else if (fantasyManagerToCreate.getName() == "") {
      throw new FiveLeagueFantasyException("Name can't be null.", HttpStatus.BAD_REQUEST);
    } else if (fantasyManagerToCreate.getUsername() == "") {
      throw new FiveLeagueFantasyException("Username can't be null.", HttpStatus.BAD_REQUEST);
    } else if (fantasyManagerToCreate.getEmail() == "") {
      throw new FiveLeagueFantasyException("Email can't be null", HttpStatus.BAD_REQUEST);
    }

    fantasyManagerToCreate.setLeague(null);
    return fantasyManagerRepository.save(fantasyManagerToCreate);
  }

  @Transactional
  public FantasyManager loginFantasyManager(String username, String password) {

    FantasyManager fantasyManagerToLogin = fantasyManagerRepository.findByUsername(username);

    if (fantasyManagerToLogin == null) {
      throw new FiveLeagueFantasyException(
          "User with username " + username + " does not exist.", HttpStatus.NOT_FOUND);
    } else if (!fantasyManagerToLogin.getPassword().equals(password)) {
      throw new FiveLeagueFantasyException("Incorrect password.", HttpStatus.UNAUTHORIZED);
    }

    return fantasyManagerToLogin;
  }

  @Transactional
  public FantasyManager getFantasyManagerByUsername(String name) {

    if (fantasyManagerRepository.findByUsername(name) == null) {
      throw new FiveLeagueFantasyException(
          "User with username " + name + " does not exist.", HttpStatus.NOT_FOUND);
    }

    return fantasyManagerRepository.findByUsername(name);
  }

  @Transactional
  public FantasyManager getFantasyManagerByEmail(String email) {

    if (fantasyManagerRepository.findByEmail(email) == null) {
      throw new FiveLeagueFantasyException(
          "User with email " + email + " does not exist.", HttpStatus.NOT_FOUND);
    }

    return fantasyManagerRepository.findByEmail(email);
  }

  @Transactional
  public List<FantasyManager> getFantasyManagersByName(String name) {

    if (fantasyManagerRepository.findByName(name) == null) {
      throw new FiveLeagueFantasyException(
          "User with name " + name + " does not exist.", HttpStatus.NOT_FOUND);
    }

    return fantasyManagerRepository.findByName(name);
  }

  @Transactional
  public List<FantasyManager> getFantasyManagersByLeague(int leagueID) {

    if (fantasyManagerRepository.findByLeague(leagueRepository.findById(leagueID)) == null) {
      throw new FiveLeagueFantasyException(
          "No users belong to league " + leagueRepository.findById(leagueID).getName() + " .",
          HttpStatus.NOT_FOUND);
    }

    return fantasyManagerRepository.findByLeague(leagueRepository.findById(leagueID));
  }

  @Transactional
  public List<FantasyManager> getFantasyManagersByLeague(String leagueName) {

    if (fantasyManagerRepository.findByLeague(leagueRepository.findByName(leagueName)) == null) {
      throw new FiveLeagueFantasyException(
          "No users belong to league " + leagueRepository.findByName(leagueName).getName() + " .",
          HttpStatus.NOT_FOUND);
    }

    return fantasyManagerRepository.findByLeague(leagueRepository.findByName(leagueName));
  }

  @Transactional
  public FantasyManager updateFantasyManagerName(String username, String newName) {
    FantasyManager fantasyManagerToUpdate = fantasyManagerRepository.findByUsername(username);

    if (fantasyManagerToUpdate == null) {
      throw new FiveLeagueFantasyException(
          "Fantasy Manager with username " + username + " does not exist.", HttpStatus.NOT_FOUND);
    }

    fantasyManagerToUpdate.setName(newName);
    return fantasyManagerRepository.save(fantasyManagerToUpdate);
  }

  @Transactional
  public FantasyManager updateFantasyManagerEmail(String username, String newEmail) {
    FantasyManager fantasyManagerToUpdate = fantasyManagerRepository.findByUsername(username);

    if (fantasyManagerToUpdate == null) {
      throw new FiveLeagueFantasyException(
          "Fantasy Manager with username " + username + " does not exist.", HttpStatus.NOT_FOUND);
    }

    fantasyManagerToUpdate.setEmail(newEmail);
    return fantasyManagerRepository.save(fantasyManagerToUpdate);
  }

  @Transactional
  public FantasyManager updateFantasyManagerPassword(String username, String newPassword) {
    FantasyManager fantasyManagerToUpdate = fantasyManagerRepository.findByUsername(username);

    if (fantasyManagerToUpdate == null) {
      throw new FiveLeagueFantasyException(
          "Fantasy Manager with username " + username + " does not exist.", HttpStatus.NOT_FOUND);
    }

    fantasyManagerToUpdate.setPassword(newPassword);
    return fantasyManagerRepository.save(fantasyManagerToUpdate);
  }
}
