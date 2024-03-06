import React, { useState, useEffect } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBIcon } from 'mdb-react-ui-kit';
import NaviagtionBar from '../NaviagationBar';
import { useNavigate } from 'react-router-dom';

function AccountInfo() {
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState(() => {
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

    const handleLogout = () => {
        localStorage.removeItem("userData"); // Clear userData from localStorage
        navigate('/'); // Redirect to login page or home page after logout
    };

    const handleEdit = () => setIsEditing(true);

    const handleSave = () => setIsEditing(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };



    return (
        <NaviagtionBar>
            <MDBContainer className="my-5">
                <MDBCard>
                    <MDBCardBody>
                        {formData ? (
                            <>
                                {isEditing ? (
                                    <>
                                        <div className="mb-3">
                                            <label className="form-label">Name</label>
                                            <input type="text" name="name" value={formData.name} onChange={handleChange} className="form-control" />
                                        </div>
                                        <div className="mb-3">
                                            <label className="form-label">Email</label>
                                            <input type="email" name="email" value={formData.email} onChange={handleChange} className="form-control" />
                                        </div>
                                        <div className="mb-3">
                                            <label className="form-label">Password</label>
                                            <input type="password" name="password" value={formData.password} onChange={handleChange} className="form-control" />
                                        </div>
                                        <MDBBtn onClick={handleSave} className="btn btn-primary">Save</MDBBtn>
                                    </>
                                ) : (
                                    <>
                                        <p><strong>Name:</strong> {formData.name}</p>
                                        <p><strong>Email:</strong> {formData.email}</p>
                                        <p><strong>Password:</strong> ••••••••</p>
                                        <MDBBtn onClick={handleEdit} className="btn btn-secondary">Edit</MDBBtn>
                                    </>
                                )}
                            </>
                        ) : (
                            <div>"401 unauthorized"</div>
                        )}
                    </MDBCardBody>
                </MDBCard>
            </MDBContainer>
        </NaviagtionBar>
    );
}


export default AccountInfo;
