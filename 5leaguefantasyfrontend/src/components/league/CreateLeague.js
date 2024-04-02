import React, { useState } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import * as apis from './LeagueAPI';

function CreateLeague() {
  const [formData, setFormData] = useState({
    name: '',
  });
  const [error, setError] = useState('');

  const navigate = useNavigate();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const API = axios.create({
    baseURL: 'http://localhost:5432/postgres', // Replace with your actual backend base URL
    // You can add more default settings here
    headers: {
      'Content-Type': 'application/json',
      //'Access-Control-Allow-Origin': 'http://localhost:3000'
      // Include other headers as needed, like Authorization for JWT
    },
  });

  const handleSubmit = async (event) => {
    const data = await apis.createLeague(formData.name);
    if (!data.ok) {
      try {
        const response = await API.post('/league/new', formData);
        if (response.status >= 200 && response.status < 300) {
          // Navigate to league page upon successful creation
          navigate('/league');
        } else {
          setError('An error occurred during league creation. Please try again.');
        }
      } catch (error) {
        console.error('Request failed', error);
      }
    } else {
      alert(`League created ${formData.name}`)
      navigate('/league');
    }
  }

  return (
    <MDBContainer className="my-5">
      <MDBCard>
        <MDBCardBody>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">League Name</label>
              <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} className="form-control" required />
            </div>
            {error && <div className="alert alert-danger" role="alert">{error}</div>}
            <MDBBtn type="submit" className="btn btn-primary">Create League</MDBBtn>
          </form>
        </MDBCardBody>
      </MDBCard>
    </MDBContainer>
  );
}

export default CreateLeague;