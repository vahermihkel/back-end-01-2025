import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { Person } from "../../models/Person";

function Profile() {
  const [person, setPerson] = useState<Person>();
  const [message, setMessage] = useState("");
  const navigate = useNavigate();
    
  useEffect(() => {
    fetch("http://localhost:8080/person",{
        headers: {
          "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
        }
      })
      .then(res => res.json())
      .then(json => setPerson(json));
  }, []);

  function edit() {
    fetch("http://localhost:8080/person", {
      method: "PUT",
      body: JSON.stringify(person),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else {
          navigate("/");
        }
        
      });
  }

  if (person === undefined) {
    return <div>Loading...</div>
  }

  return (
    <div>
      <div>{message}</div>
      <label>ID</label><br />
      <input disabled defaultValue={person.id} type="text" /> <br />
      <label>Personal Code</label><br />
      <input disabled defaultValue={person.personalCode} type="text" /> <br />
      <label>First Name</label><br />
      <input onChange={(e) => setPerson({...person, firstName: e.target.value})} defaultValue={person.firstName} type="text" /> <br />
      <label>Last Name</label><br />
      <input onChange={(e) => setPerson({...person, lastName: e.target.value})} defaultValue={person.lastName} type="text" /> <br />
      <label>Email</label><br />
      <input onChange={(e) => setPerson({...person, email: e.target.value})} defaultValue={person.email} type="text" /> <br />
      <label>Phone</label><br />
      <input onChange={(e) => setPerson({...person, phone: e.target.value})} defaultValue={person.phone} type="text" /> <br />
      <label>Password</label><br />
      <input onChange={(e) => setPerson({...person, password: e.target.value})} defaultValue={person.password} type="password" /> <br />
      <button onClick={edit}>Edit</button>
    </div>
  )
}

export default Profile