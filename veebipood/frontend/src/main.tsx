import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css'
import App from './App.tsx'
import { BrowserRouter } from 'react-router-dom'

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
