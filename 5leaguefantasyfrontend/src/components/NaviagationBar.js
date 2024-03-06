import React from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBIcon } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';

const NaviagtionBar = ({ children }) => {
    const navigate = useNavigate(); // Hook to programmatically navigate

    const handleLogout = () => {
        // Clear userData from localStorage
        localStorage.removeItem("userData");
        // Redirect to login page
        navigate('/login');
    };
    return (
        <>
            <MDBNavbar expand='lg' light bgColor='dark'>
                <MDBContainer fluid>
                    <MDBNavbarBrand href='/'>
                        <MDBIcon fas icon="futbol" style={{ marginRight: "10px" }} />
                        5 League Fantasy
                    </MDBNavbarBrand>
                    {/* Left-aligned links */}
                    <MDBNavbarNav left fullWidth={false} className="mb-2 mb-lg-0">
                        <MDBNavbarLink active aria-current='account' href='/account' style={{ color: "#fff" }}>
                            Account
                        </MDBNavbarLink>
                        <MDBNavbarLink active aria-current='query' href='/queryPlayers' style={{ color: "#fff" }}>
                            Query Players
                        </MDBNavbarLink>
                    </MDBNavbarNav>
                    {/* Right-aligned Logout button */}
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
            <main>{children}</main>
        </>
    );
};

export default NaviagtionBar;
