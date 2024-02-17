package com.example.LeagueFantasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.LeagueFantasy.Entity.FantasyManager;
import com.example.LeagueFantasy.dto.FantasyManagerRequestDto;
import com.example.LeagueFantasy.dto.FantasyManagerResponseDto;
import com.example.LeagueFantasy.exception.FiveLeagueFantasyException;
import com.example.LeagueFantasy.service.FantasyManagerService;
import java.util.List;
import java.util.ArrayList;

@RestController
//@CrossOrigin()  FILL ONCE CONNNECTED TO FRONTEND
public class FantasyManagerController {

    @Autowired
    private FantasyManagerService fantasyManagerService;

    @PostMapping("/managers/newManager")
    public ResponseEntity<FantasyManagerResponseDto> createManager(@RequestBody FantasyManagerRequestDto fantasyManagerRequest) {
        
        FantasyManager userToCreate = new FantasyManager(
            fantasyManagerRequest.getUsername(), 
            fantasyManagerRequest.getName(), 
            fantasyManagerRequest.getEmail(), 
            fantasyManagerRequest.getPassword()
        );

        FantasyManager createdUser= fantasyManagerService.createFantasyManager(userToCreate);

        FantasyManagerResponseDto createdUserResponse = new FantasyManagerResponseDto(
            createdUser.getUsername(),
            createdUser.getName(),
            createdUser.getEmail(),
            createdUser.getPassword(),
            createdUser.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(createdUserResponse, HttpStatus.CREATED);
    }

    @GetMapping("/managers/login/{username}/{password}")
    public ResponseEntity<FantasyManagerResponseDto> loginManager(@PathVariable String username, @PathVariable String password) {
        
        FantasyManager retrievedUser = fantasyManagerService.getFantasyManagerByUsername(username);

        if(retrievedUser == null) {
            throw new FiveLeagueFantasyException("User with username " + username + " does not exist.", HttpStatus.NOT_FOUND);
        }

        if(retrievedUser.getPassword().equals(password)) {
            FantasyManagerResponseDto retrievedUserResponse = new FantasyManagerResponseDto(
                retrievedUser.getUsername(),
                retrievedUser.getName(),
                retrievedUser.getEmail(),
                retrievedUser.getPassword(),
                retrievedUser.getLeague()
            );

            return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
        } else {
            throw new FiveLeagueFantasyException("Invalid password.", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/managers/username/{username}")
    public ResponseEntity<FantasyManagerResponseDto> getManagerByUsername(@PathVariable String username) {

        FantasyManager retrievedUser = fantasyManagerService.getFantasyManagerByUsername(username);

        FantasyManagerResponseDto retrievedUserResponse = new FantasyManagerResponseDto(
            retrievedUser.getUsername(),
            retrievedUser.getName(),
            retrievedUser.getEmail(),
            retrievedUser.getPassword(),
            retrievedUser.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
    }

    @GetMapping("/managers/email/{email}")
    public ResponseEntity<FantasyManagerResponseDto> getManagerByEmail(@PathVariable String email) {

        FantasyManager retrievedUser = fantasyManagerService.getFantasyManagerByEmail(email);

        FantasyManagerResponseDto retrievedUserResponse = new FantasyManagerResponseDto(
            retrievedUser.getUsername(),
            retrievedUser.getName(),
            retrievedUser.getEmail(),
            retrievedUser.getPassword(),
            retrievedUser.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(retrievedUserResponse, HttpStatus.OK);
    }

    @GetMapping("/managers/name/{name}")
    public ResponseEntity<List<FantasyManagerResponseDto>> getManagerByName(@PathVariable String name) {

        List<FantasyManager> retrievedUser = fantasyManagerService.getFantasyManagersByName(name);
        List<FantasyManagerResponseDto> retrievedUsersResponse = new ArrayList<FantasyManagerResponseDto>();

        for(FantasyManager user: retrievedUser) {
            FantasyManagerResponseDto currentUserResponse = new FantasyManagerResponseDto(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getLeague()
            );
            retrievedUsersResponse.add(currentUserResponse);
        }

        return new ResponseEntity<List<FantasyManagerResponseDto>>(retrievedUsersResponse, HttpStatus.OK);
    }

    @GetMapping("/managers/league/id/{id}")
    public ResponseEntity<List<FantasyManagerResponseDto>> getManagersByLeague(@PathVariable int id) {
        
        List<FantasyManager> usersByLeague = fantasyManagerService.getFantasyManagersByLeague(id);
        List<FantasyManagerResponseDto> usersByLeagueResponse = new ArrayList<FantasyManagerResponseDto>();

        for(FantasyManager user: usersByLeague) {
            FantasyManagerResponseDto currentUserResponse = new FantasyManagerResponseDto(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getLeague()
            );

            usersByLeagueResponse.add(currentUserResponse);
        }

        return new ResponseEntity<List<FantasyManagerResponseDto>>(usersByLeagueResponse, HttpStatus.OK);
    }

    @GetMapping("/managers/league/name/{name}")
    public ResponseEntity<List<FantasyManagerResponseDto>> getManagersByLeague(@PathVariable String name) {
        
        List<FantasyManager> usersByLeague = fantasyManagerService.getFantasyManagersByLeague(name);
        List<FantasyManagerResponseDto> usersByLeagueResponse = new ArrayList<FantasyManagerResponseDto>();

        for(FantasyManager user: usersByLeague) {
            FantasyManagerResponseDto currentUserResponse = new FantasyManagerResponseDto(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getLeague()
            );

            usersByLeagueResponse.add(currentUserResponse);
        }

        return new ResponseEntity<List<FantasyManagerResponseDto>>(usersByLeagueResponse, HttpStatus.OK);
    }

    @PutMapping("/managers/username/{username}/{newName}") 
    public ResponseEntity<FantasyManagerResponseDto> updateManagerName(@PathVariable String username, @PathVariable String newName) {
        
        FantasyManager userToUpdate = fantasyManagerService.updateFantasyManagerName(username, newName);

        FantasyManagerResponseDto updatedUserResponse = new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
    }

    @PutMapping("/managers/league/{username}/{newEmail}")
    public ResponseEntity<FantasyManagerResponseDto> updateManagerEmail(@PathVariable String username, @PathVariable String newEmail) {
        
        FantasyManager userToUpdate = fantasyManagerService.updateFantasyManagerEmail(username, newEmail);

        FantasyManagerResponseDto updatedUserResponse = new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
    }

    @PutMapping("/managers/password/{username}/{newPassword}")
    public ResponseEntity<FantasyManagerResponseDto> updateManagerPassword(@PathVariable String username, @PathVariable String newPassword) {
        
        FantasyManager userToUpdate = fantasyManagerService.updateFantasyManagerPassword(username, newPassword);

        FantasyManagerResponseDto updatedUserResponse = new FantasyManagerResponseDto(
            userToUpdate.getUsername(),
            userToUpdate.getName(),
            userToUpdate.getEmail(),
            userToUpdate.getPassword(),
            userToUpdate.getLeague()
        );

        return new ResponseEntity<FantasyManagerResponseDto>(updatedUserResponse, HttpStatus.OK);
    }
}
