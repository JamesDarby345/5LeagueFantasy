import React, { useEffect, useState } from 'react';
import { MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import NaviagtionBar from '../NavigationBar';
import * as apis from './helper/PlayerQueryAPI';
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
  const [formData, setFormData] = useState(() => {
    const storedData = localStorage.getItem("userData");
    return storedData ? JSON.parse(storedData) : null;
  });

  const [error, setError] = useState('');

  const navigate = useNavigate()

  useEffect(() => {
    if (!formData) {
      setError('Unauthorized access. Default data cannot be used.');
      navigate('/'); // Change '/login' to your actual login route
    }
  }, [formData, navigate]);

  const st = {
    BY_NAME: 1,
    BY_LEAGUE: 2,
    BY_POSITION: 3,
    ALL: 4
  }

  const sortingMethods = {
    NONE: 0,
    GOALS_ASC: 1,
    GOALS_DESC: 2,
    ASST_ASC: 3,
    ASST_DESC: 4
  }
  const PAGE_SIZE = 10;

  const [searchType, setSearchType] = useState(st.BY_NAME);
  const [playerType, setPlayerType] = useState(apis.PlayerTypes.FORWARD);
  const [playerName, setPlayerName] = useState("");
  const [position, setPosition] = useState("");
  const [playersData, setPlayersData] = useState([]);
  const [playersDataHasChanged, setPlayersDataHasChanged] = useState(false);
  const [leagueToSearchFor, setLeagueToSearchFor] = useState("Bundesliga");
  const [page, setPage] = useState(1);
  const [sortingMethod, setSortingMethod] = useState(sortingMethods.NONE);

  useEffect(() => {
    setPlayersData([]);
  }, [searchType, playerType, playerName])
  const handleSearchTypeChange = (e) => {

    setSearchType(e.target.value);
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

  const handlePositionChange = (e) => {
    setPosition(e.target.value);
  }

  const handlePageChange = (e) => {
    setPage(e.target.value);
  }

  const handleSortingMethodChange = async (e) => {
    setSortingMethod(e.target.value);
    const newSort = e.target.value;
    let receivedPlayers = [];
    if (searchType == st.ALL && playerType == apis.PlayerTypes.FORWARD) {
      if (newSort == sortingMethods.NONE) {
        receivedPlayers = await apis.searchAllForwards();
      } else if (newSort == sortingMethods.GOALS_ASC) {
        receivedPlayers = await apis.searchAllForwardsGoalsAsc();
      } else if (newSort == sortingMethods.GOALS_DESC) {
        receivedPlayers = await apis.searchAllForwardsGoalsDesc();
      } else if (newSort == sortingMethods.ASST_ASC) {
        receivedPlayers = await apis.searchAllForwardsAssistsAsc();
      } else if (newSort == sortingMethods.ASST_DESC) {
        receivedPlayers = await apis.searchAllForwardsAssistsDesc();
      }
    }

    setPage(1);
    setPlayersData(receivedPlayers);
  }

  const handlePageUp = () => {
    if (page < getTotalPageCount()) {
      setPage(page + 1);
    }
  }


  const handlePageDown = () => {
    if (page > 1) {
      setPage(page - 1);
    }
  }

  const renderPageList = () => {
    const arr = [];
    for (let i = 1; i <= getTotalPageCount(); ++i) {
      arr.push(
        <option value={i}>{i}</option>
      )
    }
    return arr;
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
      } else if (searchType == st.BY_POSITION) {
        receivedPlayers = await apis.searchForwardsByPosition(position);
      }

    } else {
      if (searchType == st.BY_POSITION) {
        receivedPlayers = await apis.searchKeepersByPosition(position);
      }
    }
    setPage(1);
    setPlayersData(receivedPlayers);
    setPlayersDataHasChanged(true);
    setSortingMethod(sortingMethods.NONE);
  }

  const getTotalPageCount = () => {
    return Math.ceil(playersData.length / PAGE_SIZE);
  }

  const getNumItemsOnPage = () => {
    if (page < getTotalPageCount()) {
      return Math.min(playersData.length, PAGE_SIZE);
    } else {
      return playersData.length % PAGE_SIZE || PAGE_SIZE;
    }
  }

  return (
    <NaviagtionBar>
      <div>

        <MDBContainer className="Container">
          <MDBCard>
            <MDBCardBody>

              <h1>Search For Players</h1>
              <form>
                <input type="radio" class="btn-check" name="playerType" id="type1" onChange={handlePlayerTypeChange} checked={playerType == apis.PlayerTypes.FORWARD} value={apis.PlayerTypes.FORWARD} />
                <label className="btn btn-secondary searchOpts" htmlFor="type1">Forward 🏃‍♂️</label>
                <input type="radio" class="btn-check" name="playerType" id="type2" onChange={handlePlayerTypeChange} checked={playerType == apis.PlayerTypes.GOALKEEPER} value={apis.PlayerTypes.GOALKEEPER} />
                <label className="btn btn-secondary searchOpts" htmlFor="type2">Goalkeeper 🥅</label>
              </form>
              <form>
                <div className="form-check form-check-inline searchOpts">
                  <input className="form-check-input" type="radio" name="searchType" id="search1" value={st.BY_NAME}
                    checked={searchType == st.BY_NAME} onChange={handleSearchTypeChange} />
                  <label className="form-check-label" htmlFor="search1">Name</label>
                </div>

                <div className="form-check form-check-inline searchOpts">
                  <input className="form-check-input" type="radio" name="searchType" id="search2" value={st.BY_LEAGUE}
                    checked={searchType == st.BY_LEAGUE} onChange={handleSearchTypeChange} />
                  <label className="form-check-label" htmlFor="search2">League</label>
                </div>

                <div className="form-check form-check-inline searchOpts">
                  <input className="form-check-input" type="radio" name="searchType" id="search3" value={st.BY_POSITION}
                    checked={searchType == st.BY_POSITION} onChange={handleSearchTypeChange} />
                  <label className="form-check-label" htmlFor="search3">Position</label>
                </div>
                <div className="form-check form-check-inline searchOpts">
                  <input className="form-check-input" type="radio" name="searchType" id="search4" value={st.ALL}
                    checked={searchType == st.ALL} onChange={handleSearchTypeChange} />
                  <label className="form-check-label" htmlFor="search4">All</label>
                </div>

              </form>
              <MDBRow>
                <MDBCol className="col-md-11">
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

                    <select className="leagueSelect" id="searchByLeague" value={leagueToSearchFor} onChange={handleLeagueChange}>
                      <option value="Bundesliga">Bundesliga</option>
                      <option value="Ligue1">Ligue 1</option>
                      <option value="LaLiga">La Liga</option>
                      <option value="SerieA">Serie A</option>
                      <option value="PremierLeague">Premier League</option>
                    </select>

                  }

                  {
                    searchType == st.BY_POSITION &&
                    <div className="byPositionSearch">
                      <form>
                        <div className="form-group">
                          <input type="search" className="form-control" id="searchByPosition" placeholder="Input position here" value={position} onChange={handlePositionChange}></input>
                        </div>
                      </form>
                    </div>
                  }

                  {
                    searchType == st.ALL &&
                    <div>
                      Searching for all {playerType == apis.PlayerTypes.FORWARD ? "forwards" : "goalkeepers"}. 🧐
                    </div>
                  }
                </MDBCol>
                <MDBCol className="col-md-1">
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
                playersData.length > 0 &&
                <MDBRow>
                  <MDBCol className="col-md-8">Showing {getNumItemsOnPage()} out of {playersData.length} players</MDBCol>
                  <MDBCol className="col-md-4 paginationControl">
                    <MDBBtn floating size="sm" onClick={handlePageDown}>
                      <MDBIcon icon="caret-left" />
                    </MDBBtn>
                    Page


                    <select className="pageSelect" id="pageSelector" value={page} onChange={handlePageChange}>
                      {renderPageList()}
                    </select>
                    of {getTotalPageCount()}
                    <MDBBtn floating size="sm" onClick={handlePageUp}>
                      <MDBIcon icon="caret-right" />
                    </MDBBtn>
                  </MDBCol>
                </MDBRow>
              }
              {
                playersData.length > 0 && searchType == st.ALL &&
                <div className="sortingMethodsControl">
                  <div>
                    Sort by:
                  </div>
                  <select className="sortSelect" id="sortMethodsDropdown" value={sortingMethod} onChange={handleSortingMethodChange}>
                    <option value={sortingMethods.NONE}>None</option>
                    <option value={sortingMethods.GOALS_ASC}>Goals (Ascending)</option>
                    <option value={sortingMethods.GOALS_DESC}>Goals (Descending)</option>
                    <option value={sortingMethods.ASST_ASC}>Assists (Ascending)</option>
                    <option value={sortingMethods.ASST_DESC}>Assists (Descending)</option>
                  </select>
                </div>
              }
              {
                playersData.map((item, i) => {
                  let min = (page - 1) * PAGE_SIZE;
                  let max = min + PAGE_SIZE;
                  if (i >= min && i <= max) {
                    return <PlayerCard props={item} key={i} />
                  }

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
            .leagueSelect{
              width: 100%;
              height: 100%;
              
            }
            .searchOpts {
              margin: 5px 3px;
            }

            .paginationControl {
              display: flex;
              justify-content: flex-end;
              gap: 7px;
            }
            .sortingMethodsControl {
              display: flex;
              gap: 7px;
            }
          `
          }
        </style>
      </div>
    </NaviagtionBar>
  )
}

export default QueryPlayers;