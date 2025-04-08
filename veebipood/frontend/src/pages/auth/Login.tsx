import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom"
import { EmailPassword } from "../../models/EmailPassword";
import { AuthContext } from "../../store/AuthContext";

function Login() {
  const [emailPassword, setEmailPassword] = useState<EmailPassword>({email: "", password: ""});
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); // import reac-router-dom
  const {setRole, setLoggedIn} = useContext(AuthContext);

  function login() {
    fetch("http://localhost:8080/login", {
      method: "POST",
      body: JSON.stringify(emailPassword),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else {
          setRole(json.role);
          setLoggedIn(true);
          sessionStorage.setItem("token", json.token)
          navigate("/admin");
        }
      });
  }

  return (
    <div>
      <div>{message}</div>
      <label>Email</label><br />
      <input onChange={(e) => setEmailPassword({...emailPassword, email: e.target.value})} type="text" /> <br />
      <label>Password</label><br />
      <input onChange={(e) => setEmailPassword({...emailPassword, password: e.target.value})} type="text" /> <br />
      <button onClick={login}>Login</button>
    </div>
  )
}

export default Login