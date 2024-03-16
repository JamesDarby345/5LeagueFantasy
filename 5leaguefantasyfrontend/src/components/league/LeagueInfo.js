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
}

export default LeagueInfo;