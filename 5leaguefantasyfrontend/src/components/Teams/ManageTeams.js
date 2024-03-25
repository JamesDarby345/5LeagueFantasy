import React from "react";
import NaviagtionBar from "../NavigationBar";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBBtn } from "mdb-react-ui-kit";
import * as apis from './TeamManagementAPI';
import PageableList from "../Reusable/PageableList";
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

	const [teamName, setTeamName] = useState("");

	const handleTeamNameChange = (e) => {
		setTeamName(e.target.value);
	}

	const handleTeamCreate = async () => {
		const data = await apis.createTeamWithName(teamName, formData.username, true);
		if (!data.ok) {
			alert(data.message)
		}
		fetchTeamsList();
	}

	useEffect(() => {
		fetchTeamsList();
	}, []);
	const [teamsList, setTeamsList] = useState([]);

	const fetchTeamsList = async () => {
		const data = await apis.getUserTeams(formData.username)
		setTeamsList(data);
	}

	const itemsFromTeamsList = () => {

		const arr = [];
		for (let i = 0; i < teamsList.length; ++i) {
			arr.push(
				<div className="teamCard" key={i}>
					<div>{teamsList[i].name}</div>
					<div>{teamsList[i].isActive ? "Active" : "Inactive"}</div>
					<div>{teamsList[i].numberOfForwards} Forwards</div>
					<div>{teamsList[i].numberOfKeepers} Keepers</div>
					<div>{teamsList[i].points} Points</div>
				</div>
			)
		}
		return arr;
	}
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
							<MDBRow>
								<MDBCol className="col-md-11">
									<form>
										<input type="text" className="form-control" id="teamNameEntry" placeholder="Input name here" value={teamName} onChange={handleTeamNameChange}></input>
									</form>
								</MDBCol>
								<MDBCol className="col-md-1">
									<MDBBtn type="button" onClick={handleTeamCreate}>Create</MDBBtn>
								</MDBCol>
							</MDBRow>
						</MDBCardBody>
					</MDBCard>
					<MDBCard>
						<MDBCardBody>
							<h2>Your teams</h2>
							<PageableList itemType="team(s)">
								{itemsFromTeamsList()}
							</PageableList>
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
										.teamCard {
											margin: 0.5em;
											padding: 0.7em;
											border-color: #AAAAAA;
											border-style: solid;
											border-width: 1px;
											border-radius: 10px;
											box-shadow: 0 3px 5px rgb(0,0,0,0.3);
											display: grid;
											grid-template-columns: 2fr 1fr 1fr 1fr 1fr;
											justify-items: start;
											align-items: center;
											background-color: rgb(256, 256, 256);
									}
									.teamCard:hover {
											transition-duration: 0.1s;
											background-color: rgb(244, 244, 244);
									}
                `
				}
			</style>
		</>
	);
}

export default ManageTeams;