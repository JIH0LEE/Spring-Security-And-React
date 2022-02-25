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
        axios.post('/api/sign-up',body)
        .then(
            (res)=>console.log(res.data)
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
            <div>회원가입</div>
            <input onChange={usernameChange} type='email'/>
            <input onChange={passwordChange} type='password'/>
        <Button onClick={login}>
            회원가입            
        </Button>
    </Container>
  );
};

export default LoginPage;