import { useEffect, useState } from "react";
import { Product } from "../../models/Product";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";

function ManageProducts() {
  const [products, setProducts] = useState<Product[]>([]);
  const [message, setMessage] = useState("");
  
  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(res => res.json())
      .then(json => setProducts(json));
  }, []);

  function deleteProduct(product: Product) {
    fetch("http://localhost:8080/products/" + product.id, {
      method: "DELETE"
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else {
          setProducts(json);
        }
      });
  }

  function changeProductActive(product: Product) {
 // fetch("http://localhost:8080/product-active?id=" + product.id + "&active=" + !product.active, {
    fetch(`http://localhost:8080/product-active?id=${product.id}&active=${!product.active}`, {
      method: "PATCH"
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else {
          setProducts(json);
        }
      })
  }

  return (
    <div>
      <div>{message}</div>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Active</th>
            <th>Image</th>
            <th>Category</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => 
              <tr key={product.id} className={product.active ? "active": "inactive"}>
                <td>{product.id}</td>
                <td>{product.name}</td>
                <td>{product.price} €</td>
                <td>{product.active ? "Active" : "Inactive"}</td>
                <td>{product.image}</td>
                <td>{product.category?.name}</td>
                <td style={{width: "500px", textAlign: "right"}}>
                  <button onClick={() => changeProductActive(product)}>Muuda {product.active ? "mitteaktiivseks": "aktiivseks"}</button>
                  <Button variant="outlined" onClick={() => deleteProduct(product)}>x</Button>
                  <Link to={"/edit-product/" + product.id}>
                  <button>Muuda</button>
                  </Link>  
                </td>
              </tr>
            )}
        </tbody>
      </table>

      <div>
          
        </div>
    </div>
  )
}

export default ManageProducts