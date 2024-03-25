import axios from 'axios';
import { baseURL } from '../../config';


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