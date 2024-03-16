import React, { useEffect, useState } from 'react';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol,
  MDBIcon,
  MDBInput
} from 'mdb-react-ui-kit';
import axios from 'axios';

import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  useEffect(() => {
    if (error !== '') {
      alert(error);
    }
  }, [error]); // This effect depends on the `error` variable

  const API = axios.create({
    baseURL: 'http://localhost:8080', // Replace with your actual backend base URL
    // You can add more default settings here
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:3000'
      // Include other headers as needed, like Authorization for JWT
    },
  });
  
  const handleLogin = async (e) => {
    e.preventDefault(); // Prevent default form submission behavior
  
    try {
      const response = await API.get('/managers/login/' + username + '/' + password);
      console.log(response.data);
      localStorage.setItem("userData", JSON.stringify(response.data));
      navigate("/") // use as reference to make landing page AKA home page
      // navigate("/queryPlayers");
      // Here, you could redirect the user to another page or save the login info (e.g., token) as needed
    } catch (error) {
      if (error.response) {
        // The request was made and the server responded with a status code that falls out of the range of 2xx
        console.error("Login error", error.response.data);
        setError(error.response.data.message);
        // Handle login error (e.g., show an error message based on error.response.data)
      } else if (error.request) {
        // The request was made but no response was received, `error.request` is an instance of XMLHttpRequest in the browser and an instance of http.ClientRequest in node.js
        console.error("Login error", "The request was made but no response was received");
        setError('The request was made but no response was received');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error("Login error", "Error", error.message);
        setError(error.messsage);
      }
    }
  };
  
  const handleRegisterNavigation = () => {
    navigate('/new_account'); // Navigate to the new_account route
  };

  return (
        <MDBContainer className="my-5">
        <MDBCard>
          <MDBRow className='g-0'>
            <MDBCol md='6'>
              <MDBCardImage src='https://tasbihdigital.com/wp-content/uploads/2023/08/Messi-iPhone-Wallpaper-HD-Lock-Screen.jpg' alt="login form" className='rounded-start w-100'/>
            </MDBCol>
            <MDBCol md='6'>
              <MDBCardBody className='d-flex flex-column'>
                <div className='d-flex flex-row mt-2'>
                  <MDBIcon fas icon="futbol fa-3x me-3" style={{ color: '#ff6219' }}/>
                  <span className="h1 fw-bold mb-0">The Best Play Here</span>
                </div>
                <h5 className="fw-normal my-4 pb-3" style={{letterSpacing: '1px'}}>Sign into your account</h5>
                <form onSubmit={handleLogin}>
                  <MDBInput wrapperClass='mb-4' label='Username' id='formControlLg' type='text' size="lg" value={username} onChange={(e) => setUsername(e.target.value)}/>
                  <MDBInput wrapperClass='mb-4' label='Password' id='formControlLg' type='password' size="lg" value={password} onChange={(e) => setPassword(e.target.value)}/>
                  <MDBBtn className="mb-4 px-5" color='' size='lg' type="submit">Login</MDBBtn>
                </form>
                <a className="small text-muted" href="#!">Forgot password?</a>
                <p className="mb-5 pb-lg-2" style={{color: 'black'}}>Don't have an account? <button onClick={handleRegisterNavigation} style={{color: '#393f81', background: 'none', border: 'none', padding: '0', font: 'inherit', cursor: 'pointer', outline: 'inherit'}}>Register Here</button></p>
              </MDBCardBody>
            </MDBCol>
          </MDBRow>
        </MDBCard>
      </MDBContainer>
  );
}

export default Login;
