import axios from 'axios';
import { baseURL } from '../../../config';


const API = axios.create({
    baseURL: baseURL,    
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
});

export async function searchForwardsByName(playerName) {
    try {
        const response = await API.get(`/forwards/name/${playerName}`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchForwardsByEuropeanLeague(league) {
    try {
        const response = await API.get(`/forwards/europeanLeague/${league}`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchForwardsByPosition(position) {
    try {
        const response = await API.get(`/forwards/position/${position}`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchKeepersByPosition(position) {
    try {
        const response = await API.get(`/players/${position}`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.GOALKEEPER);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchAllForwards() {
    try {
        const response = await API.get(`/forwards/all`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchAllForwardsGoalsAsc() {
    try {
        const response = await API.get(`/forwards/sortedByAscendingGoals`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchAllForwardsGoalsDesc() {
    try {
        const response = await API.get(`/forwards/sortedByDescendingGoals`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchAllForwardsAssistsAsc() {
    try {
        const response = await API.get(`/forwards/sortedByAscendingAssists`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function searchAllForwardsAssistsDesc() {
    try {
        const response = await API.get(`/forwards/sortedByDescendingAssists`);
        return response.data.map((entry) => {
            return dataToPlayerConverter(entry, PlayerTypes.FORWARD);
        });
    } catch (e) {
        console.log(e);
        return [];
    }
}

function dataToPlayerConverter(data, playerType) {
    return {
        name: data.name,
        position: data.position,
        team: data.team,
        goals: data.goals,
        assists: data.assists,
        cleanSheets: data?.cleanSheets || 0,
        europeanLeague: data.europeanLeague,
        playerType: playerType,
        id: data.id
    }
}

export const PlayerTypes = {
    FORWARD: 0,
    GOALKEEPER: 1
}
