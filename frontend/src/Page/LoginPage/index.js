import axios from "axios";
import React, { useState } from "react";
import { Container,Button } from "react-bootstrap";
import { useNavigate,useLocation } from "react-router-dom";
import { setCookie } from "../../utils/cookies";
import "./style.css"
const LoginPage = (props) => {
    const[username,setUsername]=useState("");
    const[password,setPassword]=useState("")
    const navigate=useNavigate();
    const login=()=>{
       
        var body={
            username:username,
            password:password
        }
        axios.post('/api/sign-in',body)
        .then(
            (res)=>{
                
                if(res.data.success){

                    window.localStorage.setItem('isLogin', 'true');
                    window.localStorage.setItem('token', `Bearer ${res.headers.auth_token}`);
                    setCookie(`Bearer ${res.headers.auth_token}`);
                    navigate(-1);
                   
                }
                else{
                    alert(res.data.message);    
                }
            }
        ).catch((error)=>
            console.log(error)
            
        )
    }       
    const usernameChange=(e)=>{ 
        setUsername(e.target.value)
    }
    const passwordChange=(e)=>{
        setPassword(e.target.value)
    }
    const autoPress = (e) => {
        if (e.key === 'Enter') {
            login();
        }
    }

  return (
        <Container className="LoginContainer">
            <div>로그인</div> 
                <input onChange={usernameChange} onKeyPress={autoPress} type='email'/>
                <input onChange={passwordChange} onKeyPress={autoPress} type='password'/>
                <Button onClick={login}>
                로그인
                </Button>  
        </Container>
  );
};

export default LoginPage;