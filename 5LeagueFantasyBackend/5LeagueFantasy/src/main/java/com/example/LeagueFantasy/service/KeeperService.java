package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.Entity.Goalkeeper;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.GoalkeeperRepository;
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
}