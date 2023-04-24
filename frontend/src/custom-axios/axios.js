import axios from "axios";

// setup na sve so ni treba so axios, koristime za da pravime vistiski povici
const instance = axios.create({
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

// za da ja pristapuvame od nadvor
export default instance;