import { useState } from "react";
import { useNavigate } from "react-router-dom"
import { Person } from "../../models/Person";

function Signup() {
  const [person, setPerson] = useState<Person>({firstName: "", lastName: "", email: "", password: ""});
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); // import reac-router-dom

  function signup() {
    fetch("http://localhost:8080/signup", {
      method: "POST",
      body: JSON.stringify(person),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else if (json.firstName && json.lastName) {
          navigate("/login");
        } else {
          console.log("UNEXPECTED RETURN");
        }
      });
  }

  return (
    <div>
      {/* <div>{t(message)}</div> "ERROR_PRODUCT_CAPITAL_LETTER": "sdasd" */}
      <div>{message}</div>
      {/* {JSON.stringify(product)} */}
      <label>First Name</label><br />
      <input onChange={(e) => setPerson({...person, firstName: e.target.value})} type="text" /> <br />
      <label>Last Name</label><br />
      <input onChange={(e) => setPerson({...person, lastName: e.target.value})} type="text" /> <br />
      <label>Email</label><br />
      <input onChange={(e) => setPerson({...person, email: e.target.value})} type="text" /> <br />
      <label>Password</label><br />
      <input onChange={(e) => setPerson({...person, password: e.target.value})} type="text" /> <br />
      <button onClick={signup}>Signup</button>
    </div>
  )
}

export default Signup