import { Button } from "react-bootstrap";
import React from "react";
import { removeToken } from "../../utils/cookies";
import isLogin from "../../utils/isLogin";
import { useState } from "react";
const LandingPage = () => {


  const[num,setNum]=useState(0);
  const logout=()=>{
      window.localStorage.setItem('isLogin', false);
      
      window.localStorage.removeItem('token');
      removeToken();
      setNum(num+1);
      
      
      

  }

  return (
    <>
          <Button href='/sign-up'>회원가입 페이지</Button>
          <Button href='/sign-in'>로그인 페이지</Button>
          <Button href='/user-info'>유저 정보</Button>
          {
            isLogin()
        ? <Button onClick={logout}>로그아웃</Button>
        : <div></div>
      }
         
    </>
  );
};

export default LandingPage;