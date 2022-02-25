import axios from "axios";
import React, { useState } from "react";
import { Container,Button } from "react-bootstrap";
import "./style.css"
const LoginPage = () => {
    const[username,setUsername]=useState("");
    const[password,setPassword]=useState("")


    const login=()=>{
        var body={
            username:username,
            password:password
        }
        axios.post('/api/sign-in',body)
        .then(
            (res)=>console.log(res)
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

  return (
    <Container className="LoginContainer">
            <div>로그인</div>
            <input onChange={usernameChange} type='email'/>
            <input onChange={passwordChange} type='password'/>
        <Button onClick={login}>
            로그인
        </Button>
    </Container>
  );
};

export default LoginPage;