import React from "react";
import NaviagtionBar from "../NavigationBar";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { MDBContainer, MDBCard, MDBCardBody } from "mdb-react-ui-kit";
function ManageTeams() {

    const [formData, setFormData] = useState(() => {
        const storedData = localStorage.getItem("userData");
        return storedData ? JSON.parse(storedData) : null;
      });
    
    const navigate = useNavigate()

    useEffect(() => {
      if (!formData) {
        navigate('/');
      }
    }, [formData, navigate]);
    return (
        <>
        <NaviagtionBar>
            <MDBContainer className="Container TeamsPage">
                <MDBCard>
                    <MDBCardBody>
                        <h1>Manage Teams</h1>
                    </MDBCardBody>
                </MDBCard>
                <MDBCard>
                    <MDBCardBody>
                        <h2>Create a team</h2>

                    </MDBCardBody>
                </MDBCard>
                <MDBCard>
                    <MDBCardBody>
                        <h2>Your teams</h2>
                    </MDBCardBody>
                </MDBCard>
            </MDBContainer>
        </NaviagtionBar>

        <style>
            {
                `
                    .TeamsPage {
                        display: flex;
                        flex-direction: column;
                        gap: 5px;
                        margin-top: 20px;
                    }
                `
            }
        </style>
        </>
    );
}

export default ManageTeams;