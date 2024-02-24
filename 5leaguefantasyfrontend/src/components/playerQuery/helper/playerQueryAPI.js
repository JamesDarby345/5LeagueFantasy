import axios from 'axios';
import { baseURL } from '../../../config';


const API = axios.create({
    baseURL: baseURL,    
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
});


export async function searchByName(playerName) {
    try {
        const response = await API.get(`/forwards/name/${playerName}`);
        console.log(response);
        return response;
    } catch (e) {
        console.log(e);
        return [];
    }
}

    