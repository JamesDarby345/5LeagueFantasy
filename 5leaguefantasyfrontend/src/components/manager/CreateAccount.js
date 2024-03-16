import React, { useState } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function CreateAccount() {
    const [formData, setFormData] = useState({
        username: '',
        name: '',
        email: '',
        password: '',
    });
    const [error, setError] = useState('');

    const navigate = useNavigate(); // For redirecting the user after account creation

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    const API = axios.create({
        baseURL: 'http://localhost:8080', // Replace with your actual backend base URL
        // You can add more default settings here
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': 'http://localhost:3000'
          // Include other headers as needed, like Authorization for JWT
        },
      });

    const handleSubmit = async (event) => {
        event.preventDefault();
        // Placeholder for actual account creation logic, such as calling an API
        try {
            const response = await API.post('/managers/newManager', formData);
            // console.log('Form Data:', formData); // Replace with actual sign-up logic
            if (response.status >= 200 && response.status < 300) {
                // Navigate to login upon successful creation
                navigate('/login');
              } else {
                // Handle any specific cases for unsuccessful request
                // For example, you can set an error message to display to the user
                setError('An error occurred during account creation. Please try again.');
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
                            <label htmlFor="username" className="form-label">Username</label>
                            <input type="text" id="username" name="username" value={formData.username} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="name" className="form-label">Name</label>
                            <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password</label>
                            <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} className="form-control" required />
                        </div>
                        {error && <div className="alert alert-danger" role="alert">{error}</div>}
                        <MDBBtn type="submit" className="btn btn-primary">Create Account</MDBBtn>
                    </form>
                </MDBCardBody>
            </MDBCard>
        </MDBContainer>
    );
}

export default CreateAccount;
