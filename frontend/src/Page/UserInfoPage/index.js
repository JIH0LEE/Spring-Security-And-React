import axios from "axios";
import React, { useState,useEffect } from "react";
import { Navigate } from 'react-router-dom';
import isLogin from "../../utils/isLogin";
import { header } from "../../config";

const UserInfoPage = () => {
    const[username,setUsername]=useState("ho");

    
    useEffect(() => {
        
        if(isLogin()){
            //로컬 storage를 이용한 방법
            // axios.defaults.headers.common['Authorization'] = window.localStorage.getItem('token');
            axios.get("/api/test",header).then(
            (res)=>setUsername(res.data.username)
        );
        }
      }, []);
    
      if (!isLogin()) {
        alert("로그인이 필요합니다");
        return (
            <Navigate to={{
                pathname: '/sign-in',
                state: {
                  from: '/'
                }
            }}/>
        );
      }

  return (    
           <div>{username}</div>
  );
};

export default UserInfoPage;