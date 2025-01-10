import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  // useState --> HTML muutmiseks
  const [categories, setCategories] = useState<{name: string}[]>([]);
  //let categories = []; // kui muudan muutujat, siis kuidas muutub HTML?
  // document.getElementById("dasdsad").innerHTML = <div></div>
  const [products, setProducts] = useState<any[]>([]);

  // uef
  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then(res => res.json())
      .then(json => setCategories(json));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(res => res.json())
      .then(json => setProducts(json));
  }, []);

  return (
    <>
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <div>Kokku kategooriaid: {categories.length} tk</div>
        <div>{categories.map(category => <div>{category.name}</div>)}</div>
        <br /><br />
        <div>
          {products.map(product => 
            <div>
              <span>{product.name}: </span>
              <span>{product.price} €</span>
            </div>
          )}
        </div>
    </>
  )
}

export default App
