import React, { useState, useEffect } from 'react';
import { MDBBtn, MDBContainer, MDBCard, MDBCardBody, MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBIcon } from 'mdb-react-ui-kit';
import NaviagtionBar from '../NavigationBar';
import { useNavigate } from 'react-router-dom';

function LeagueInfo(){
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

    const handleEdit = () => setIsEditing(true);

    const handleSave = () => setIsEditing(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };
}

export default LeagueInfo;