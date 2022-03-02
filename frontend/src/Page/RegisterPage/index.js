import axios from "axios";
import React, { useState } from "react";
import { Container,Button } from "react-bootstrap";
import "./style.css"
const LoginPage = () => {
    const[username,setUsername]=useState("");
    const[password1,setPassword1]=useState("")
    const[password2,setPassword2]=useState("")

    const login=()=>{
        var body={
            username:username,
            password1:password1,
            password2:password2
        }
        axios.post('/api/sign-up',body)
        .then(
            (res)=>console.log(res.data)
        )
    }       
    const usernameChange=(e)=>{ 
        setUsername(e.target.value)
    }
    const passwordChange1=(e)=>{
        setPassword1(e.target.value)
    }
    const passwordChange2=(e)=>{
        setPassword2(e.target.value)
    }

  return (
    <Container className="LoginContainer">
            <div>회원가입</div>
            <input onChange={usernameChange} type='email'/>
            <input onChange={passwordChange1} type='password'/>
            <input onChange={passwordChange2} type='password'/>
        <Button onClick={login}>
            회원가입            
        </Button>
    </Container>
  );
};

export default LoginPage;