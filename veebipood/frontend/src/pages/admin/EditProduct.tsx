import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import { Product } from "../../models/Product";

function EditProduct() {
  // http://localhost:5173/edit-product/1    :id
  const {id} = useParams();

  // Hookid (Reacti erikood)
  // 1. peab olema use.. ees
  // 2. peab alati importima
  // 3. peab alati käima minema -> sulud lõpus
  // 4. ei tohi olla tingimuslikult loodud (if sees)
  // 5. ei tohi olla loodud korduvalt (funktsiooni sees)

  const [product, setProduct] = useState<Product>();
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); // import reac-router-dom

  useEffect(() => {
    fetch("http://localhost:8080/products/" + id)
      .then(res => res.json())
      .then(json => setProduct(json));
  }, [id]);

  function edit() {
    fetch("http://localhost:8080/products", {
      method: "PUT",
      body: JSON.stringify(product),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          setMessage(json.error);
        } else {
          //window.location.href = "/admin/products";
          navigate("/admin/products");
        }
        
      });
  }

  if (product === undefined) {
    return <div>Loading...</div>
  }

  return (
    <div>
      {/* <div>{t(message)}</div> "ERROR_PRODUCT_CAPITAL_LETTER": "sdasd" */}
      <div>{message}</div>
      {/* {JSON.stringify(product)} */}
      <label>ID</label><br />
      <input disabled defaultValue={product.id} type="text" /> <br />
      <label>Name</label><br />
      <input onChange={(e) => setProduct({...product, name: e.target.value})} defaultValue={product.name} type="text" /> <br />
      <label>Price</label><br />
      <input onChange={(e) => setProduct({...product, price: Number(e.target.value)})} defaultValue={product.price} type="number" /> <br />
      <label>Image</label><br />
      <input onChange={(e) => setProduct({...product, image: e.target.value})} defaultValue={product.image} type="text" /> <br />
      <label>Category</label><br />
      <input disabled defaultValue={product.category?.name} type="text" /> <br />
      <label>Active</label><br />
      <input onChange={(e) => setProduct({...product, active: e.target.checked})} defaultChecked={product.active} type="checkbox" /> <br />
      {/* <Link to="/admin/products"> */}
      <button onClick={edit}>Edit</button>
      {/* </Link> */}
    </div>
  )
}

export default EditProduct