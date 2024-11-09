import React, { useState } from 'react'
import Loggedin from './Loggedin';

function Login() {
  let [userName,setUserName]=useState("");  
  let [password,setPassword]=useState("");
  let [pop,setPop]=useState("");
  let [checkClicked, setCheckClicked] = useState(false);
  let [logged, setlogged] = useState(false);
  let checkLoginDetails=(e)=>{
    // {setPop(userName+password)}

    if(password.length>3){

      alert("hi " + userName + "");
      console.log('logged in');
      setlogged(true)

        // setCheckClicked(true);


    }
    else{
        
        alert("wrong password")
        setCheckClicked(true);
    }
    

  }
  return (
    <>
    
      {!logged ?
        <div>
          {!checkClicked ?
            <form onSubmit={e => {
              e.preventDefault();
              checkLoginDetails();
            }}>

        
            
              <input type='text' placeholder='username' onChange={e => { setUserName(e.target.value) }} />
              <input type='password' placeholder='password' onChange={e => { setPassword(e.target.value) }} />
              {/* <h1> {userName}</h1>  */}
              <input type='submit'></input>

            </form>
            : <h1>Blocked from this website.</h1>}
        </div>
        :
        <h1>Logged In</h1>}
            


    </>

  )
}

export default Login