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

function QueryPlayers() {

  const st = {
    BY_NAME: 1,
    BY_LEAGUE: 2,
    BY_POSITION: 3
  }

  const [searchType, setSearchType] = useState(st.BY_NAME);

  const [playerName, setPlayerName] = useState("");
  const handleSearchTypeChange = (e) => {
    
    setSearchType(e.target.value);
    console.log(searchType)
  }

  const handlePlayerNameChange = (e) => {
    setPlayerName(e.target.value);
  }

  const handleSearchClicked = () => {
    apis.searchByName(playerName);
  }
  return (
    <div>
      <MDBContainer className="Container">
        <MDBCard>
          <MDBCardBody>

            <h1>Search For Players</h1>

            <div>Search by: </div>
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
                        <select className="form-control" id="searchByLeague">
                          <option>Bundesliga</option>
                          <option>Ligue 1</option>
                          <option>La Liga</option>
                          <option>Serie A</option>
                          <option>Premier League</option>
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
    </div>
  )
}

export default QueryPlayers;