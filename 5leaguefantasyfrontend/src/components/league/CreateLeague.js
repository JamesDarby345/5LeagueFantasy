import React, { useState } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function CreateLeague(){

    const API = axios.create({
        baseURL: 'http://localhost:5432/postgres', // Replace with your actual backend base URL
        // You can add more default settings here
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': 'http://localhost:3000'
          // Include other headers as needed, like Authorization for JWT
        },
      });


}

export default CreateLeague;