import React from "react";
import { PlayerTypes } from "./playerQueryAPI";

function PlayerCard(props) {
    let player = props.props;
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
                <div>Goals</div>
                <div>{player.goals}</div>
            </div>
            
            <div className="statsDisplay"> 
                <div>Assists</div>
                <div>{player.assists}</div>
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
                grid-template-columns: minmax(10em, 1fr) 1fr 1fr 0.5fr 0.5fr;
                justify-items: start;
                align-items: center;
                background-color: rgb(250, 250, 250);
            }
            .playerCard:hover {
                transition-duration: 0.1s;
                background-color: rgb(256, 256, 256);
            }
            .statsDisplay {
                font-weight: bold;
                text-align: center;
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