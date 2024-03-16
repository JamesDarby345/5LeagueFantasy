import logo from './logo.svg';
import './App.css';
import Login from './components/Login'; 
import QueryPlayers from './components/playerQuery/QueryPlayers';
import AccountInfo from './components/manager/AccountInfo';
import CreateAccount from './components/manager/CreateAccount';
import HomePage from './components/HomePage';
import LeagueInfo from './components/league/LeagueInfo';
import {BrowserRouter, Routes, Route} from "react-router-dom"
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage/>} />
        <Route path="/login" element = {<Login />}></Route>
        <Route path="/query_players" element = {<QueryPlayers />}></Route>
        <Route path="/account" element = {<AccountInfo />}></Route>
        <Route path="/new_account" element = {<CreateAccount />}></Route>
        <Route path="/league" element = {<LeagueInfo />}></Route>
        <Route path="/new_league" element = {<CreateLeague />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
export default App;