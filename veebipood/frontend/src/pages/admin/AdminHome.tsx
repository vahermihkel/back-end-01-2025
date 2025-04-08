import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';

function AdminHome() {
  return (
    <div>
      <Link to="/admin/products">
        <Button variant="primary">Manage products</Button>
      </Link>
      <Link to="/admin/categories">
        <Button variant="secondary">Manage categories</Button>
      </Link>
      <Link to="/admin/add-product">
        <Button variant="success">Add product</Button>
      </Link>
    </div>
  )
}

export default AdminHome