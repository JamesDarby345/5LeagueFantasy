import React, { useState } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';

function CreateAccount() {
    const [formData, setFormData] = useState({
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

    const handleSubmit = async (event) => {
        event.preventDefault();
        // Placeholder for actual account creation logic, such as calling an API
        try {
            console.log('Form Data:', formData); // Replace with actual sign-up logic
            navigate('/account-created'); // Redirect to a success page or login page
        } catch (error) {
            setError('An error occurred during account creation. Please try again.'); // Set your actual error message here
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
