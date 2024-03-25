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
    const isAuthenticated = localStorage.getItem("userData");
    return (
        <>
            <MDBNavbar expand='lg' light bgColor='dark'>
                <MDBContainer fluid>
                    <MDBNavbarBrand href='/' className="navContents">
                        <MDBIcon fas icon="futbol" style={{ marginRight: "10px"}} />
                        5 League Fantasy
                    </MDBNavbarBrand>
                    {/* Left-aligned links */}
                    <MDBNavbarNav left fullWidth={false} className="mb-2 mb-lg-0">
                        <MDBNavbarLink active aria-current='account' href='/account' style={{ color: "#fff" }}>
                            Account
                        </MDBNavbarLink>
                        <MDBNavbarLink active aria-current='query' href='/query_players' style={{ color: "#fff" }}>
                            Query Players
                        </MDBNavbarLink>
                        <MDBNavbarLink active aria-current='manage teams' href='/manage_teams' style={{ color: "#fff" }}>
                            Manage Teams
                        </MDBNavbarLink>
                    </MDBNavbarNav>
                    {/* Right-aligned Logout button */}
                    <MDBNavbarNav right fullWidth={false} className="mb-2 mb-lg-0">
                        <MDBNavbarItem>
                            {isAuthenticated ? (
                                // If user is authenticated, show logout option
                                <MDBNavbarItem>
                                    <MDBNavbarLink className="navContents" href='#' onClick={handleLogout}>Logout</MDBNavbarLink>
                                </MDBNavbarItem>
                            ) : (
                                // If user is not authenticated, show login option
                                <MDBNavbarItem>
                                    <MDBNavbarLink className="navContents" href='/login'>Login</MDBNavbarLink>
                                </MDBNavbarItem>
                            )}
                        </MDBNavbarItem>
                    </MDBNavbarNav>
                </MDBContainer>
            </MDBNavbar>
            <style>
                {`
                    .navContents {
                        color: white;
                    }
                `}
            </style>
            <main>{children}</main>
        </>
    );
};

export default NaviagtionBar;
