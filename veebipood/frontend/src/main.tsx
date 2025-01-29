import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css'
import './i18n';
import App from './App.tsx'
import { BrowserRouter } from 'react-router-dom'

// meie fail "./" või "../"
// node_module seest "react"   või  "react-router-dom"

// import SEE from "SEE" --> ainult siin failis
// import "SEE"; --> üle terve rakenduse

// import {SEE} --> tükk sellest moodulist. Funktsioonid:  export const
// import SEE --> terve see moodul. Page-d on terve:   export default

// Navigeerimiseks
// 1. installima react-router-dom
// 2. ümbritsema BrowserRouteriga <App /> faili (+ import)
// 3. Tegema App.tsx sees seoseid URLde ja failide vahel

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </StrictMode>,
)
