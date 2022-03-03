
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect } from 'react';
import axios from 'axios';
import { Route,Routes } from 'react-router-dom';
import LandingPage from './Page/LandingPage';
import LoginPage from './Page/LoginPage';
import UserInfoPage from './Page/UserInfoPage';
import RegisterPage from './Page/RegisterPage'
function App() {
  useEffect(() => {
    if (window.localStorage.getItem('isLogin')) {
      if (JSON.parse(window.localStorage.getItem('isLogin'))) {
        axios.defaults.headers.common['Authorization'] = window.localStorage.getItem('token');
      }
    }
  }, []);
  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<LandingPage/>} />
        <Route path="/sign-in" element={<LoginPage/>} />
        <Route path="/sign-up" element={<RegisterPage/>} />
        <Route exact path="/user-info" element={<UserInfoPage/>} />
      </Routes>
    </div>
  );
}

export default App;
