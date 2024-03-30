package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.entity.EuropeanLeague;
import com.example.LeagueFantasy.entity.Forward;
import com.example.LeagueFantasy.entity.Goalkeeper;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeeperService {

  @Autowired
  private GoalkeeperRepository keeperRepository;

  public List<Goalkeeper> getKeeperByPosition(String position) {
    if (position == null || position.isEmpty())
      throw new FiveLeagueFantasyException("Position can't be null.", HttpStatus.BAD_REQUEST);
    return keeperRepository.findByPosition(position);
  }

  @Transactional
  public List<Goalkeeper> getKeeperByName(String name) {
    if (name == null || name.isEmpty())
      throw new FiveLeagueFantasyException("Name can't be null.", HttpStatus.BAD_REQUEST);
    return keeperRepository.findByNameContainingIgnoreCase(name);
  }

  @Transactional
  public List<Goalkeeper> getKeeperByCleanSheet(int cleanSheets) {
    if (cleanSheets < 0)
      throw new FiveLeagueFantasyException("Cleansheets must be 0 or above.", HttpStatus.BAD_REQUEST);
    return keeperRepository.findByCleanSheets(cleanSheets);
  }

  @Transactional
  public List<Goalkeeper> getAllKeepers() {
    return keeperRepository.findAll();
  }
}
