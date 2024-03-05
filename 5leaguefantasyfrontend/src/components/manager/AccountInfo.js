import React, { useState, useEffect } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBIcon } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom'; // Assuming you're using react-router-dom for navigation

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
        <>
            <MDBNavbar expand='lg' light bgColor='dark'>
                <MDBContainer fluid>
                    <MDBNavbarBrand href='/' style={{ color: "#fff", fontWeight: "bold", fontSize: "24px" }}>
                        <MDBIcon fas icon="futbol" style={{ marginRight: "10px" }} />
                        5 League Fantasy
                    </MDBNavbarBrand>
                    <MDBNavbarItem>
                        <MDBNavbarLink active aria-current='page' href='#!' style={{ color: "#fff"}} onClick={navigate('/queryPlayers')
                        }>
                            Query Players
                        </MDBNavbarLink>
                    </MDBNavbarItem>
                    <MDBNavbarNav right fullWidth={false} className="mb-2 mb-lg-0">
                        <MDBNavbarItem>

                            <MDBNavbarLink href='#!' onClick={handleLogout}>
                                <MDBIcon fas icon="sign-out-alt" className="me-2" />
                                Logout
                            </MDBNavbarLink>

                        </MDBNavbarItem>
                    </MDBNavbarNav>
                </MDBContainer>
            </MDBNavbar>

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
        </>
    );
}


export default AccountInfo;
