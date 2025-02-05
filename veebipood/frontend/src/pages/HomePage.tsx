import { useEffect, useState } from "react";
import { Product } from "../models/Product";
import { Button } from "@mui/material";
import { Category } from "../models/Category";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { CartProduct } from "../models/CartProduct";

function HomePage() {
  const [products, setProducts] = useState<Product[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
    // const [message, setMessage] = useState("");
  const size = 2;  
  // const page = 2;
  const [activePage, setActivePage] = useState(1);
  const [pages, setPages] = useState<number[]>([]); // [1,2,3,4]
  const [activeCategoryId, setActiveCategoryId] = useState(0);
  const {t} = useTranslation();
  
  useEffect(() => {
    fetch(`http://localhost:8080/public-products?categoryId=${activeCategoryId}&size=${size}&page=${activePage-1}`)
      .then(res => res.json())
      .then(json => {
        setProducts(json.content);
        const pagesArray = [];
        for (let page = 1; page <= json.totalPages; page++) {
          pagesArray.push(page);
        }
        setPages(pagesArray);
      });
  }, [activePage, activeCategoryId]);

  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then(res => res.json())
      .then(json => setCategories(json));
  }, []);

  function filterByCategory(categoryId: number) {
    setActiveCategoryId(categoryId);
    setActivePage(1);
    // fetch("http://localhost:8080/products-by-category?categoryId=" + categoryId)
    //   .then(res => res.json())
    //   .then(json => {
    //     setProducts(json.content);
    //     const pagesArray = [];
    //     for (let page = 1; page <= json.totalPages; page++) {
    //       pagesArray.push(page);
    //     }
    //     setPages(pagesArray);
    //   });
  }

  function addToCart(product: Product) {
    const cartLS: CartProduct[] = JSON.parse(localStorage.getItem("cart") || "[]");
    const index = cartLS.findIndex(cartProduct => cartProduct.product.id === product.id);
    if (index >= 0) {  // if (index !== -1)
      cartLS[index].quantity++;
    } else {
      cartLS.push({"product": product, "quantity": 1});
    } 
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  // 1.võtame localStorage-st   --> localStorage.getItem()
  // 2.võtame jutumärgid maha  --> JSON.parse()
  // 3.lisame localStorage-st võetule ühe juurde  --> .push()
  // 4.paneme jutumärgid tagasi --> JSON.stringify()
  // 5.paneme localStorage-sse tagasi  --> localStorage.setItem()

  function changePage(newPage: number) {
    setActivePage(newPage);
  }

  return (
    <div>

      <div>
        <button onClick={() => filterByCategory(0)}>All categories</button>
        {categories.map(category => 
          <button key={category.id} onClick={() => filterByCategory(category.id)}>
            {t(category.name)}
          </button>)}
      </div>
      
      <div>
        {products.map(product => 
          <div className="product" key={product.id}>
            <Link to={"/product/" + product.id}>
              <img className="picture" src={product.image} alt="Product image" />
              <div>{product.name}</div>
              <div>{product.price} €</div>
            </Link>
            {/* <div>{product.image}</div> */}
            <Button variant="outlined" onClick={() => addToCart(product)}>Add to cart</Button>
          </div>
        )}
      </div>

      {
        pages.map(page => <button key={page} onClick={() => changePage(page)}>{page}</button> )
      }

    </div>
  )
}

export default HomePage