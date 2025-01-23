import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';

function AdminHome() {
  return (
    <div>
      <Button as={Link} to="/admin/products" variant="primary">Manage products</Button>
      <Button as={Link} to="/admin/categories" variant="secondary">Manage categories</Button>
      <Button as={Link} to="/admin/add-product" variant="success">Add product</Button>
      <Button variant="warning">Warning</Button>
    </div>
  )
}

export default AdminHome