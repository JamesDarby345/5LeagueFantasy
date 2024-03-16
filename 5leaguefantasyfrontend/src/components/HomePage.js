import React, { useState, useEffect } from 'react';
import { MDBContainer, MDBCard, MDBCardBody, MDBRow, MDBCol, MDBTypography } from 'mdb-react-ui-kit';
import NaviagtionBar from './NavigationBar';
import { useNavigate } from 'react-router-dom';

function HomePage() {
    const [formData] = useState(() => {
        const storedData = localStorage.getItem("userData");
        return storedData ? JSON.parse(storedData) : null; // Set to null if userData is missing
    });

    const [error, setError] = useState('');

    const navigate = useNavigate(); // For redirecting the user

    useEffect(() => {
        // Check if formData is null (which means userData was missing or default data would have been used)
        if (!formData) {
            // Simulate throwing a 401 unauthorized error
            setError('Unauthorized access. Default data cannot be used.');
            // Optionally, redirect the user to a login page or show an error message
            navigate('/'); // Change '/login' to your actual login route
        }
    }, [formData, navigate]);
  return (
    <>
      <NaviagtionBar>
        <MDBContainer className="my-5">
            <MDBRow className="justify-content-center">
            <MDBCol md="8">
                <MDBCard alignment="center">
                <MDBCardBody>
                    {formData ? (
                        <>
                            <MDBTypography tag='h2' className='text-center mb-4'>Welcome to Our Platform!</MDBTypography>
                            <p className="text-center">Welcome. Write something here.</p>
                        </>
                        ) : (
                            <div>"401 unauthorized"</div>
                        )}
                </MDBCardBody>
                </MDBCard>
            </MDBCol>
            </MDBRow>
        </MDBContainer>
      </NaviagtionBar>
    </>
  );
}

export default HomePage;
