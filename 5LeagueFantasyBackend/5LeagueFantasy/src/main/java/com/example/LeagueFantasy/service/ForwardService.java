package com.example.LeagueFantasy.service;

import com.example.LeagueFantasy.Entity.EuropeanLeague;
import com.example.LeagueFantasy.Entity.Forward;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.repository.ForwardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForwardService {

    @Autowired
    private ForwardRepository forwardRepository;

    @Transactional
    public List<Forward> getAllForwards(){
        return forwardRepository.findAll();
    }

    @Transactional
    public List<Forward> getForwardsByName(String name){
        if(name==null || name.isEmpty()) throw new FiveLeagueFantasyException("Name can't be null." , HttpStatus.BAD_REQUEST);
        return forwardRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public List<Forward> getForwardsByLeague(String europeanLeague) {
        try {
            EuropeanLeague leagueEnum = EuropeanLeague.valueOf(europeanLeague);
            return forwardRepository.findByEuropeanLeague(leagueEnum);
        } catch (IllegalArgumentException | NullPointerException e) {
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
            throw new IllegalArgumentException("Invalid or null EuropeanLeague value: " + europeanLeague, e);
        }
    }

    @Transactional
    public List<Forward> getForwardsByGoals(int goals) {
        if (goals  < 0) throw new FiveLeagueFantasyException("Goals scored must be above 0.", HttpStatus.BAD_REQUEST);
        return forwardRepository.findByGoals(goals);
    }

    @Transactional
    public List<Forward> getForwardsByAssists(int assists) {
        if (assists < 0) throw new FiveLeagueFantasyException("Assists bust me above 0.", HttpStatus.BAD_REQUEST);
        return forwardRepository.findByAssists(assists);
     }

     @Transactional
    public List<Forward> getForwardsByDescendingGoals() {
        return forwardRepository.findAllByOrderByGoalsDesc();
     }

     @Transactional
    public List<Forward> getForwardsByDescendingAssists() {
        return forwardRepository.findAllByOrderByAssistsDesc();
     }
}
