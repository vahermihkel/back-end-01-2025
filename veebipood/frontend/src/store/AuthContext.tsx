import { createContext, ReactNode, useEffect, useState } from "react";

// seda pean importima, et kätte saada muutujaid
export const AuthContext = createContext({
  admin: false,
  loggedIn: false,
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  setAdmin: (value: boolean) => {},
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  setLoggedIn: (value: boolean) => {},
  loading: true
});

// seda pean importima näitamaks mis on tema globaalsus
export const AuthContextProvider = ({children}: {children: ReactNode}) => {
  const [admin, setAdmin] = useState(false);
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
          setAdmin(true);
          setLoggedIn(true);
        }
        setLoading(false);
      });
    } else {
      setLoading(false);
    }
  }, []);

  return (
    <AuthContext.Provider value={{admin, loggedIn, setAdmin, setLoggedIn, loading}}>
      {children}
    </AuthContext.Provider>
    )
}