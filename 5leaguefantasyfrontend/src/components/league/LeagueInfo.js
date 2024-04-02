import React, { useState, useEffect } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBIcon } from 'mdb-react-ui-kit';
import NaviagtionBar from '../NavigationBar';
import { useNavigate } from 'react-router-dom';
import PageableList from "../Reusable/PageableList";

function LeagueInfo(){
    const [formData, setFormData] = useState(() => {
        const storedData = localStorage.getItem("userData");
        return storedData ? JSON.parse(storedData) : null; // Set to null if userData is missing
    });

    const [error, setError] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        if (!formData) {
            navigate('/login');
        }
    }, [formData, navigate]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    return (
		<>
			<NaviagtionBar>
				<MDBContainer className="Container LeaguePage">
					<MDBCard>
						<MDBCardBody>
							<h1>Manage League</h1>
						</MDBCardBody>
					</MDBCard>
				</MDBContainer>
			</NaviagtionBar>
		</>
	);
}

export default LeagueInfo;