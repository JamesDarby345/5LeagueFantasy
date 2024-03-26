import React from "react";
import { PlayerTypes } from "./PlayerQueryAPI";
import { MDBBtn, MDBIcon } from "mdb-react-ui-kit";
import { useNavigate } from "react-router-dom";
function PlayerCard(props) {
    let player = props.props;
    const navigate = useNavigate();
    const addPlayerToTeam = () => {
        navigate("/add_to_team", {state: player})
    }

    return (
        <>
        <div className="playerCard">
            
            <div>
                
            <div className="playerName">
                {player.name}
            </div>
            
            <div>
                {player.playerType == PlayerTypes.FORWARD && "Forward"}
                {player.playerType == PlayerTypes.GOALKEEPER && "Goalkeeper"}
                {` (${player.position})`}
            </div>
            </div>
            
            
            <div>
                {player.europeanLeague}
            </div>
            
            <div>{player.team}</div>
            
            <div className="statsDisplay">
                <div>
                    <div>Goals</div>
                    <div>{player.goals}</div>
                </div>
                <div>
                    <div>Assists</div>
                    <div>{player.assists}</div>
                </div>
                {
                    player.playerType == PlayerTypes.GOALKEEPER &&
                    <div> 
                    <div>Clean Sheets</div>
                    <div>{player.cleanSheets}</div>
                    </div>
                }
                
            </div>
            <div>
                <MDBBtn floating about="" onClick={addPlayerToTeam}>
                    <MDBIcon icon="user-plus"/>
                </MDBBtn>
            </div>
        </div>
        <style>
            {`
            .playerCard {
                margin: 0.5em;
                padding: 0.7em;
                border-color: #AAAAAA;
                border-style: solid;
                border-width: 1px;
                border-radius: 10px;
                box-shadow: 0 3px 5px rgb(0,0,0,0.3);
                display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr 0.3fr;
                justify-items: start;
                align-items: center;
                background-color: rgb(256, 256, 256);
            }
            .playerCard:hover {
                transition-duration: 0.1s;
                background-color: rgb(244, 244, 244);
            }
            .statsDisplay {
                font-weight: bold;
                text-align: center;
                display: flex;
                justify-content: space-evenly;
                width: 100%;
            }
            .playerName {
                font-size: 1.2em;
                font-weight: bold;
                
            }
            `}
        </style>
        </>
    );
}

export default PlayerCard;