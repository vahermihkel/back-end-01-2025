import { useEffect, useRef, useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import { Category } from '../../models/Category';

function AddProduct() {
  const nameRef = useRef<HTMLInputElement>(null); // ref ---> inputi seest väärtuse kättesaamiseks
  const priceRef = useRef<HTMLInputElement>(null);
  const imageRef = useRef<HTMLInputElement>(null);
  const categoryRef = useRef<HTMLSelectElement>(null);
  const activeRef = useRef<HTMLInputElement>(null);

  const [categories, setCategories] = useState<Category[]>([]);
  
  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then(res => res.json())
      .then(json => setCategories(json));
  }, []);

  function add() {
    if (nameRef.current === null || priceRef.current === null || imageRef.current === null ||
      categoryRef.current === null || activeRef.current === null
    ) {
      console.log("Mingi REF jäi HTMLi panemata!");
      return; // returni siit
    }

    const product = {
      "name": nameRef.current.value,
      "price": priceRef.current.value,
      "image": imageRef.current.value,
      "category": {"id": categoryRef.current.value},
      "active": activeRef.current.checked
    };

    fetch("http://localhost:8080/products", {
      method: "POST",
      body: JSON.stringify(product),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          toast.error(json.error);
        } else {
          toast.success("Edukalt lisatud!");
        }
      });
  }

  return (
    <div>
      <label>Name</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label>Price</label> <br />
      <input ref={priceRef} type="text" /> <br />
      <label>Image</label> <br />
      <input ref={imageRef} type="text" /> <br />
      <label>Category</label> <br />
      {/* <input ref={categoryRef} type="text" /> <br /> */}
      <select ref={categoryRef}>
        {categories.map(category => 
          <option key={category.id} value={category.id}>
            {category.name}
          </option>)}
      </select> <br />
      <label>Active</label> <br />
      <input ref={activeRef} type="checkbox" /> <br />
      <button onClick={add}>Add</button>

      <ToastContainer
          position="bottom-right"
          autoClose={4000}
          theme="dark"
        />
    </div>
  )
}

export default AddProduct