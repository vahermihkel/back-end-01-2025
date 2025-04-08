import { createContext, ReactNode, useEffect, useState } from "react";

// seda pean importima, et kätte saada muutujaid
// eslint-disable-next-line react-refresh/only-export-components
export const AuthContext = createContext({
  role: "",
  loggedIn: false,
  setRole: (value: string) => {console.log(value)},
  setLoggedIn: (value: boolean) => {console.log(value)},
  loading: true
});

// seda pean importima näitamaks mis on tema globaalsus
export const AuthContextProvider = ({children}: {children: ReactNode}) => {
  const [role, setRole] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (sessionStorage.getItem("token") !== null) {
      // fetch("http://localhost:8080/person?token=" + sessionStorage.getItem("token"))
      fetch("http://localhost:8080/person", {
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("token") || ""}
      })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          // viskame errori
        } else {
          setRole(json.role);
          setLoggedIn(true);
        }
        setLoading(false);
      });
    } else {
      setLoading(false);
    }
  }, []);

  return (
    <AuthContext.Provider value={{role, loggedIn, setRole, setLoggedIn, loading}}>
      {children}
    </AuthContext.Provider>
    )
}