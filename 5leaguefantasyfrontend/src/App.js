import logo from './logo.svg';
import './App.css';
import Login from './components/Login'; 
import QueryPlayers from './components/playerQuery/QueryPlayers';
import AccountInfo from './components/manager/AccountInfo';
import {BrowserRouter, Routes, Route} from "react-router-dom"
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element = {<Login />}></Route>
        <Route path="/queryPlayers" element = {<QueryPlayers />}></Route>
        <Route path="/account" element = {<AccountInfo />}></Route>
      </Routes>
    </BrowserRouter>
  );
}
export default App;