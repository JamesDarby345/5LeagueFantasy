import axios from 'axios';
import { baseURL } from '../../config';

const API = axios.create({
    baseURL: baseURL, 
    headers: {
        'Content-Type': 'application/json'
    }
});

export async function getLeagueByName(name) {
    try {
        const response = await API.get(`/league/${name}`)
        return response.data;
    } catch (e) {
        console.log(e);
        return [];
    }
}

export async function createLeague(name) {
    try {
        const response = await API.post(`/league/new/`, {
            name: name

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