// import { useState } from 'react';
import { useContext } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../store/AuthContext';
import { CartSumContext } from '../store/CartSumContext';

function NavigationBar() {
  const {t, i18n} = useTranslation();
  // const [admin, setAdmin] = useState(true);
  const {admin, setAdmin, loggedIn, setLoggedIn} = useContext(AuthContext);
  const {cartSum} = useContext(CartSumContext);
  const navigate = useNavigate();

  const setLanguage = (newLang: string) => {
    i18n.changeLanguage(newLang);
    localStorage.setItem("language", newLang);
  }

  const logout = () => {
    setAdmin(false);
    setLoggedIn(false);
    sessionStorage.removeItem("token");
    navigate("/");
  }

  return (
    <Navbar collapseOnSelect expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">Veebipood</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            {loggedIn === true && admin === true && <Nav.Link as={Link} to="/admin">Admin</Nav.Link>}
            <Nav.Link as={Link} to="/cart">{t("nav.cart")}</Nav.Link>
          </Nav>
          <Nav>
            {loggedIn === true ? 
              <>
                <Nav.Link as={Link} to="/profile">{t("nav.profile")}</Nav.Link>
                <Nav.Link onClick={logout}>{t("nav.logout")}</Nav.Link>
              </> :
              <>
                <Nav.Link as={Link} to="/login">{t("nav.login")}</Nav.Link>
                <Nav.Link as={Link} to="/signup">{t("nav.signup")}</Nav.Link>
              </>
              }
            <div>{cartSum} €</div>
            <img className="icon lang" src="/english.png" onClick={() => setLanguage("en")} alt="" />
            <img className="icon lang" src="/estonian.png" onClick={() => setLanguage("et")} alt="" />
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavigationBar;