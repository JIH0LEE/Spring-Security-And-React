import axios from "axios";
import React, { useState } from "react";
import { Container,Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "./style.css"
const LoginPage = () => {
    const[username,setUsername]=useState("");
    const[password1,setPassword1]=useState("")
    const[password2,setPassword2]=useState("")
    const navigate=useNavigate();
    const register=()=>{
        var body={
            username:username,
            password1:password1,
            password2:password2
        }
        axios.post('/api/sign-up',body)
        
        .then(
            (res)=>{
                console.log(res.data)
                navigate('/');
            }
            
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

    const autoPress = (e) => {
        if (e.key === 'Enter') {
            register();
        }
    }
  return (
    
           
        <Container className="LoginContainer">
            <div>회원가입</div>
            <input onChange={usernameChange}  onKeyPress={autoPress} type='email'/>
            <input onChange={passwordChange1}  onKeyPress={autoPress} type='password'/>
            <input onChange={passwordChange2}  onKeyPress={autoPress} type='password'/>
            <Button onClick={register}>
                회원가입            
            </Button>
        </Container>
   
  );
};

export default LoginPage;