import React, { useState,useEffect } from "react";
import Loggedin from "./Loggedin";
import axios from 'axios';

function Login() {
  let [userName, setUserName] = useState("");
  let [password, setPassword] = useState("");
  let [checkClicked, setCheckClicked] = useState(false);
  let [logged, setlogged] = useState(false);
  
  let [data, setData] = useState([]);
  // State for handling loading state
  let [loading, setLoading] = useState(true);


  console.log(data);

  

  // Fetch data when the component mounts
  useEffect(() => {
    // Use Axios to make a GET request
    axios.post('http://localhost:8080/api/login')  // Example API
      .then((response) => {
        setData(response.data);// Store data in state
        setLoading(false);  // Set loading to false after data is fetched
      })
      .catch((error) => {
        console.error('Error fetching data: ', error);  // Handle errors
        setLoading(false);  // Set loading to false if there is an error
      });
  }, []); 

  let checkLoginDetails = (e) => {
    if (password.length > 3) {
      alert("hi " + userName + "");
      console.log("logged in");
      setlogged(true);

    } else {


      alert("wrong password");
      setCheckClicked(true);
    }
  };

  if (loading)
    return loading;

  return (
    <>
      {!logged ? (
        <div>
          {!checkClicked ? (
            <form 
              onSubmit={(e) => {
                e.preventDefault();
                checkLoginDetails();
              }}  >
              <input
                type="text"
                placeholder="username"
                onChange={(e) => {
                  setUserName(e.target.value);
                }}
              />
              <input
                type="password"
                placeholder="password"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
              />
              {/* <h1> {userName}</h1>  */}
              <input type="submit"></input>
            </form>
          ) : (
            <h1>Blocked from this website.</h1>
          )}
        </div>
      ) :  (
          <>
          <Loggedin>
            </Loggedin>
            </>
      )}
    
    </>
  );
}

export default Login;
