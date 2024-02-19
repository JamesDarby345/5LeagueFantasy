package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.ForwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForwardService {

    @Autowired
    private ForwardRepository forwardRepository;

    public List<Forward> getForwardsByName(String name){
        if(name==null || name.isEmpty()) throw new FiveLeagueFantasyException("Name can't be null." , HttpStatus.BAD_REQUEST);
        return forwardRepository.findByNameContainingIgnoreCase(name);
    }
}
