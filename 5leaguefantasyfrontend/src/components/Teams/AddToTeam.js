import React from "react";
import NaviagtionBar from "../NavigationBar";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { MDBContainer, MDBCard, MDBCardBody, MDBBtn, MDBIcon } from "mdb-react-ui-kit";
import * as apis from './TeamManagementAPI';
import PageableList from "../Reusable/PageableList";

function AddToTeam(props) {

	const [formData, setFormData] = useState(() => {
		const storedData = localStorage.getItem("userData");
		return storedData ? JSON.parse(storedData) : null;
	});

	const navigate = useNavigate()

	useEffect(() => {
		if (!formData) {
			navigate('/login');
		}
	}, [formData, navigate]);

	useEffect(() => {
		fetchTeamsList();
	}, []);
	const [teamsList, setTeamsList] = useState([]);

	const fetchTeamsList = async () => {
		const data = await apis.getUserTeams(formData.username)
		setTeamsList(data?.sort((a,b) => a.name.localeCompare(b.name)));
	}

    const [playerData, setPlayerData] = useState(useLocation().state)

	const addPlayerToTeam = async (teamID, teamName) => {
		const data = await apis.addPlayerToTeam(playerData.id, playerData.playerType, teamID);
		if (!data.ok) {
			alert(data.message);
		} else {
			alert(`Successfully added ${playerData.name} to ${teamName}!`)
			navigate('/manage_teams')
		}
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
					<MDBBtn floating onClick={() => addPlayerToTeam(teamsList[i].id, teamsList[i].name)}>
                    	<MDBIcon icon="user-plus"/>
                	</MDBBtn>
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
							<h1>Add {playerData.name} to a team</h1>
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
											grid-template-columns: 2fr 1fr 1fr 1fr 1fr 0.3fr;
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

export default AddToTeam;