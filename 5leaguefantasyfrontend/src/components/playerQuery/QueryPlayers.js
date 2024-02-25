import React, { useEffect, useState } from 'react';
import * as apis from './helper/playerQueryAPI';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol,
  MDBIcon,
  MDBInput
} from 'mdb-react-ui-kit';
import PlayerCard from './helper/PlayerCard';

function QueryPlayers() {

  const st = {
    BY_NAME: 1,
    BY_LEAGUE: 2,
    BY_POSITION: 3,
    ALL: 4
  }

  const [searchType, setSearchType] = useState(st.BY_NAME);
  const [playerType, setPlayerType] = useState(apis.PlayerTypes.FORWARD);
  const [playerName, setPlayerName] = useState("");
  const [playersData, setPlayersData] = useState([]);
  const [playersDataHasChanged, setPlayersDataHasChanged] = useState(false);
  const [leagueToSearchFor, setLeagueToSearchFor] = useState("Bundesliga");
  const handleSearchTypeChange = (e) => {
    
    setSearchType(e.target.value);
    console.log(searchType)
  }

  const handlePlayerNameChange = (e) => {
    setPlayerName(e.target.value);
  }

  const handlePlayerTypeChange = (e) => {
    setPlayerType(e.target.value);
  }

  const handleLeagueChange = (e) => {
    setLeagueToSearchFor(e.target.value);
  }


  const handleSearchClicked = async () => {
    let receivedPlayers = [];
    if (playerType == apis.PlayerTypes.FORWARD) {
      if (searchType == st.BY_NAME) {
        receivedPlayers = await apis.searchForwardsByName(playerName);
      } else if (searchType == st.ALL) {
        receivedPlayers = await apis.searchAllForwards();
      } else if (searchType == st.BY_LEAGUE) {
        receivedPlayers = await apis.searchForwardsByEuropeanLeague(leagueToSearchFor);
      }
          
    }
    
    setPlayersData(receivedPlayers);
    setPlayersDataHasChanged(true);
  }
  return (
    <div>
      <MDBContainer className="Container">
        <MDBCard>
          <MDBCardBody>
            
            <h1>Search For Players</h1>

            <div>Search options: </div>
            <form>
              <input type="radio" class="btn-check" name="playerType" id="type1" onChange={handlePlayerTypeChange} checked={playerType == apis.PlayerTypes.FORWARD}  value={apis.PlayerTypes.FORWARD}/>
              <label className="btn btn-secondary" htmlFor="type1">Forward</label>
              <input type="radio" class="btn-check" name="playerType" id="type2" onChange={handlePlayerTypeChange} checked={playerType == apis.PlayerTypes.GOALKEEPER} value={apis.PlayerTypes.GOALKEEPER} />
              <label className="btn btn-secondary" htmlFor="type2">Goalkeeper</label>
            </form>
            <form>
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="searchType" id="search1" value={st.BY_NAME}
                  checked={searchType == st.BY_NAME} onChange={handleSearchTypeChange} />
                <label className="form-check-label" htmlFor="search1">Name</label>
              </div>

              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="searchType" id="search2" value={st.BY_LEAGUE}
                  checked={searchType == st.BY_LEAGUE} onChange={handleSearchTypeChange} />
                <label className="form-check-label" htmlFor="search2">League</label>
              </div>

              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="searchType" id="search3" value={st.BY_POSITION}
                  checked={searchType == st.BY_POSITION} onChange={handleSearchTypeChange} />
                <label className="form-check-label" htmlFor="search3">Position</label>
              </div>
              <div className="form-check form-check-inline">
                <input className="form-check-input" type="radio" name="searchType" id="search4" value={st.ALL}
                  checked={searchType == st.ALL} onChange={handleSearchTypeChange} />
                <label className="form-check-label" htmlFor="search4">All</label>
              </div>

            </form>
            <MDBRow>
              <MDBCol className="col-md-10">
                {
                  searchType == st.BY_NAME &&
                
                  <div className="byNameSearchBox">
                    <form>
                      <div className="form-group">
                        <input type="search" className="form-control" id="searchByName" placeholder="Input name here" value={playerName} onChange={handlePlayerNameChange}></input>
                      </div>
                    </form>
                    
                  </div>
                    
                }

                {
                  searchType == st.BY_LEAGUE &&
                  <div className="byLeagueSearch">
                    <form>
                      <div className="form-group">
                        <select className="form-control" id="searchByLeague" value={leagueToSearchFor} onChange={handleLeagueChange}>
                          <option value="Bundesliga">Bundesliga</option>
                          <option value="Ligue1">Ligue 1</option>
                          <option value="LaLiga">La Liga</option>
                          <option value="SerieA">Serie A</option>
                          <option value="PremierLeague">Premier League</option>
                        </select>
                      </div>
                    </form>
                  </div>
                }

                {
                  searchType == st.BY_POSITION &&
                  <div className="byPositionSearch">
                    <form>
                      <div className="form-group">
                        <select className="form-control" id="searchByPosition">
                          <option>Goalkeeper</option>
                          <option>Forward</option>
                        </select>
                      </div>
                    </form>
                  </div>
                }
              </MDBCol>
              <MDBCol className="col-md-2">
                <MDBBtn type="button" onClick={handleSearchClicked}>Search</MDBBtn>
              </MDBCol>
            </MDBRow>
          </MDBCardBody>
        </MDBCard>
      </MDBContainer>
      <MDBContainer className="resultsPanel">
        <MDBCard>
            <MDBCardBody>
               {!playersData.length && !playersDataHasChanged &&
                  <div>Click "Search" and players will appear here.</div>
               }
               {!playersData.length && playersDataHasChanged &&
                  <div>No players found. Please try again with different search criteria.</div>
               }
               {
                playersData.map((item, i) => {
                  return <PlayerCard props={item} key={i}/>
                })
               }
            </MDBCardBody>
        </MDBCard>
      </MDBContainer>
      <style>
        {
          `
            .resultsPanel {
              margin-top: 10px;
            }
          `
        }
      </style>
    </div>
  )
}

export default QueryPlayers;