import React from "react";
import NaviagtionBar from "../NavigationBar";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { MDBContainer, MDBCard, MDBCardBody, MDBBtn, MDBIcon } from "mdb-react-ui-kit";
import * as apis from './LeagueAPI';
import PageableList from "../Reusable/PageableList";

function AddToTeam(props) {

    const [formData, setFormData] = useState({
        name: '',
    });
    const [error, setError] = useState('');

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    const navigate = useNavigate()

    const handleSubmit = async (event) => {
      const data = await apis.getLeagueByName(formData.name);
    }

    return (
        <MDBContainer className="my-5">
          <MDBCard>
            <MDBCardBody>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">League Name</label>
                  <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} className="form-control" required />
                </div>
                {error && <div className="alert alert-danger" role="alert">{error}</div>}
                <MDBBtn type="submit" className="btn btn-primary">Search for Leagues</MDBBtn>
              </form>
            </MDBCardBody>
          </MDBCard>
        </MDBContainer>
      );
    }
export default AddToTeam;