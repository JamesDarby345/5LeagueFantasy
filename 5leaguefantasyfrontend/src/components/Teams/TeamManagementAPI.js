import axios from 'axios';
import { baseURL } from '../../config';
import { PlayerTypes } from '../playerQuery/helper/PlayerQueryAPI';

const API = axios.create({
    baseURL: baseURL, 
    headers: {
        'Content-Type': 'application/json'
    }
});


export async function createTeamWithName(name, fantasyManagerUsername, setAsActive) {
    try {
        const response = await API.post(`/userTeam/newUserTeam/`, {
            name: name,
            setAsActive: setAsActive,
            fantasyManagerUsername: fantasyManagerUsername
        });
        return {
            ok: true,
            data: response.data
        };
    } catch (e) {
        console.log(e);
        return {
            ok: false,
            message: e?.response?.data?.message || "Unknown error"
        };
    }
}

export async function getUserTeams(username) {
    try {
        const response = await API.get(`/userTeam/getUserTeams/${username}`)
        return response.data;
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function addPlayerToTeam(playerID, playerType, teamID) {
    try {
        let response;
        if (playerType == PlayerTypes.FORWARD) {
            response = await API.post(`/userTeam/addForwardToTeam`, {
                userTeamId: teamID,
                forwardId: playerID,
                goalkeeperId: -1
            })
        } else {
            response = await API.post(`/userTeam/addGoalkeeperToTeam`, {
                userTeamId: teamID,
                forwardID: -1,
                goalkeeperId: playerID
            })
        }

        return {
            ok: true,
            data: response.data
        }
    } catch (e) {
        console.log(e);
        return {
            ok: false,
            message: e?.response?.data?.message || "Unknown error"
        }
    }
}