import { KeyboardEvent, useEffect, useState } from "react";
import { Category } from "../../models/Category";
import { ToastContainer, toast } from 'react-toastify';

function ManageCategories() {
  const [categories, setCategories] = useState<Category[]>([]);
  const [category, setCategory] = useState<Category>({name: ""});

   useEffect(() => {
      fetch("http://localhost:8080/categories")
        .then(res => res.json())
        .then(json => setCategories(json));
    }, []);

    function add() {
      fetch("http://localhost:8080/categories", {
        method: "POST",
        body: JSON.stringify(category),
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
        }
      })
        .then(res => res.json())
        .then(json => {
          if (json.timestamp && json.status && json.error) {
            //setMessage(json.error);
            toast.error(json.error);
          } else {
            setCategories(json);
            //setCategory({name: ""});
            // categoryRef.current.value = "";
          }
        });
    }

    function kustuta(categoryId: number | undefined) {
      fetch("http://localhost:8080/categories/" + categoryId, {
        method: "DELETE",
        headers: {
          "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
        }
      })
        .then(res => res.json())
        .then(json => {
          if (json.timestamp && json.status && json.error) {
            //setMessage(json.error);
            toast.error(json.error);
          } else {
            setCategories(json);
          }
        });
    }

    function updateCategory(e: KeyboardEvent<HTMLInputElement>) {
      if (e.code === "Enter") {
        add();
      } else {
        setCategory({...category, name: e.currentTarget.value});
      }
    }

  return (
    <div>
      <label>Kategooria</label> <br />
      <input onKeyUp={updateCategory} type="text" /> <br />
      <button onClick={add}>Lisa</button> <br />
      {/* <div>{message}</div> */}
        <div>Kokku kategooriaid: {categories.length} tk</div>
        <div>{categories.map(category => 
            <div key={category.id}>
              <span>{category.name}</span>
              <button onClick={() => kustuta(category.id)}>x</button>
            </div> )}
        </div>

        <ToastContainer
          position="bottom-right"
          autoClose={4000}
          theme="dark"
        />
    </div>
  )
}

export default ManageCategories