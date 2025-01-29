import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { Product } from "../models/Product";

function SingleProduct() {
  // <Route path='/product/:id' element={<SingleProduct />} />
  const {id} = useParams();
  const [product, setProduct] = useState<Product>();

  useEffect(() => {
    fetch("http://localhost:8080/products/" + id)
      .then(res => res.json())
      .then(json => setProduct(json));
  }, [id]);

  if (product === undefined) {
    return <div>Loading...</div>
  }

  return (
    <div>
      <div>{product.name}</div>
      <div>{product.price}</div>
      <div>{product.category?.name}</div>
      <div>{product.image}</div>
      <div>{product.active === false && <i>Toode on mitteaktiivne</i> }</div>
    </div>
  )
}

export default SingleProduct