import React, { useState } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function CreateLeague(){
  const [formData, setFormData] = useState({
    name: '',
});
const [error, setError] = useState('');''

const navigate = useNavigate(); // For redirecting the user after account creation

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
    'Access-Control-Allow-Origin': 'http://localhost:3000'
    // Include other headers as needed, like Authorization for JWT
  },
});

const handleSubmit = async (event) => {
  event.preventDefault();
  // Placeholder for actual API logic
  try {
      const response = await API.post('/leagues/newLeague', formData);
      // console.log('Form Data:', formData); // Replace with actual sign-up logic
      if (response.status >= 200 && response.status < 300) {
          // Navigate to league page upon successful creation
          navigate('/league');
        } else {
          // Handle any specific cases for unsuccessful request
          // For example, you can set an error message to display to the user
          setError('An error occurred during league creation. Please try again.');
          // Optionally refresh the page or handle the error in a specific way
          // window.location.reload(); // Use with caution
        }
      // navigate('/account-created'); // Redirect to a success page or login page
  } catch (error) {
      console.error('Request failed', error);
  }
};

return (
  <MDBContainer className="my-5">
      <MDBCard>
          <MDBCardBody>
              <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                      <label htmlFor="name" className="form-label">Name</label>
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