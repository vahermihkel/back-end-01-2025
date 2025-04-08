import { useEffect, useState } from "react"
import styles from "../../css/Pagination.module.css";
import { Person } from "../../models/Person";
import { Button } from "@mui/material";

function Persons() {
  const [persons, setPersons] = useState<Person[]>([]);
  const size = 4;  
  const [activePage, setActivePage] = useState(1);
  const [pages, setPages] = useState<number[]>([]);

  useEffect(() => {
   fetch(`http://localhost:8080/persons?size=${size}&page=${activePage-1}`, {
    headers: {"Authorization": "Bearer " + sessionStorage.getItem("token") || ""}
   }).then(res => res.json())
    .then(json => {
      setPersons(json.content);
      const pagesArray = [];
        for (let page = 1; page <= json.totalPages; page++) {
          pagesArray.push(page);
        }
        setPages(pagesArray);
    });
  }, [activePage]);

  function changePage(newPage: number) {
    setActivePage(newPage);
  }

  function changePersonAdmin(person: Person) {
    fetch(`http://localhost:8080/person-admin?size=${size}&page=${activePage-1}&personId=${person.id}&isAdmin=${person.role === "CUSTOMER"}`, {
      method: "PATCH",
      headers: {"Authorization": "Bearer " + sessionStorage.getItem("token") || ""}
     }).then(res => res.json())
      .then(json => {
        setPersons(json.content);
      });
  }

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {persons.map(person => 
              <tr key={person.id} className={person.role === "ADMIN" ? styles.admin: styles.customer}>
                <td>{person.id}</td>
                <td>{person.firstName}</td>
                <td>{person.lastName} €</td>
                <td>{person.role === "ADMIN" ? "Admin" : person.role === "SUPERADMIN" ? "Superadmin": "Customer"}</td>
                <td>{person.email}</td>
                <td>{person.phone}</td>
                <td style={{width: "500px", textAlign: "right"}}>
                  {person.role !== "SUPERADMIN" ? <Button onClick={() => changePersonAdmin(person)}>
                    Muuda {person.role === "ADMIN" ? "tavakasutajaks": "administraatoriks"}
                  </Button>:
                  <div>Superadmin</div>
                  }
                </td>
              </tr>
            )}
        </tbody>
      </table>

      <button disabled={activePage === 1} onClick={() => changePage(activePage-1)}>{"<"}</button>
      {
        pages.map(page => 
          <button 
            hidden={page < activePage - 2 || page > activePage + 2}
            key={page} 
            className={page === activePage ? styles.active : undefined} 
            onClick={() => changePage(page)}>
              {page}
          </button> )
      }
      <button disabled={activePage === pages.length} onClick={() => changePage(activePage+1)}>{">"}</button>

    </div>
  )
}

export default Persons