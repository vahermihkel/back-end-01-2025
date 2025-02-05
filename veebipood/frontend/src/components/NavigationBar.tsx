import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';

function NavigationBar() {
  const {t, i18n} = useTranslation();

  const setLanguage = (newLang: string) => {
    i18n.changeLanguage(newLang);
    localStorage.setItem("language", newLang);
  }

  return (
    <Navbar collapseOnSelect expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">Veebipood</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/admin">Admin</Nav.Link>
            <Nav.Link as={Link} to="/cart">{t("nav.cart")}</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link as={Link} to="/login">{t("nav.login")}</Nav.Link>
            <Nav.Link as={Link} to="/signup">{t("nav.signup")}</Nav.Link>
            <img className="icon lang" src="/english.png" onClick={() => setLanguage("en")} alt="" />
            <img className="icon lang" src="/estonian.png" onClick={() => setLanguage("et")} alt="" />
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavigationBar;