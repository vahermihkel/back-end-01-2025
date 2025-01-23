import { useEffect, useState } from "react";
import { Category } from "../../models/Category";
import { ToastContainer, toast } from 'react-toastify';

function ManageCategories() {
  const [categories, setCategories] = useState<Category[]>([]);
    //let categories = []; // kui muudan muutujat, siis kuidas muutub HTML?
    // document.getElementById("dasdsad").innerHTML = <div></div>
  // const [message, setMessage] = useState("");

   useEffect(() => {
      fetch("http://localhost:8080/categories")
        .then(res => res.json())
        .then(json => setCategories(json));
    }, []);

    function kustuta(categoryId: number) {
      fetch("http://localhost:8080/categories/" + categoryId, {
        method: "DELETE"
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

  return (
    <div>
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