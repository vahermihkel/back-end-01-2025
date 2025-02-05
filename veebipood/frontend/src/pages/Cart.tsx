import { useState } from "react";
import { useTranslation } from "react-i18next";
import { CartProduct } from "../models/CartProduct";
import styles from "../css/Cart.module.css";

function Cart() {
  const { t } = useTranslation();
  // return <h1>{t('Welcome to React')}</h1>
  const [cart, setCart] = useState<CartProduct[]>(JSON.parse(localStorage.getItem("cart") || "[]"))

  function empty() {
    // KODUS
  }

  function decreaseQuantity(index: number) {
    cart[index].quantity--;
    if (cart[index].quantity === 0) {
      cart.splice(index, 1);
    }
    setCart(cart.slice());
    localStorage.setItem("cart", JSON.stringify(cart));
  } 

  function increaseQuantity(index: number) {
    cart[index].quantity++;
    setCart(cart.slice());
    localStorage.setItem("cart", JSON.stringify(cart));
  } 

  function deleteFromCart(index: number) { //.delete()   .remove()
    cart.splice(index, 1); // splice --> kustutamiseks.  splice(MITMES, MITU_TK)
    setCart(cart.slice()); // HTMLi uuendamiseks   -->   .slice() koopia tegemiseks
    //setCart([...cart]) // HTMLi uuendamiseks   -->   [...cart] koopia tegemiseks
    localStorage.setItem("cart", JSON.stringify(cart));
  } 

  function pay() {
    fetch("http://localhost:8080/orders?personId=1", {
      method: "POST",
      body: JSON.stringify(cart),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(json => {
        if (json.timestamp && json.status && json.error) {
          // setMessage(json.error);
        } else {
          // navigate("/login");
        }
      });
  }

  return (
    <div>
      {cart.length === 0 && <div>{t("cart-empty")}</div>}
      <div>{cart.map((cp, index) => 
        <div className={styles.product} key={index}>
          <div className={styles.name}>{cp.product.name}</div>
          <div className={styles.price}>{cp.product.price.toFixed(2)}€</div>
          <div className={styles.quantity}>
            <img className="icon" src="/minus.png" onClick={() => decreaseQuantity(index)} alt="" />
            <div>{cp.quantity} tk</div>
            <img className="icon" src="/plus.png" onClick={() => increaseQuantity(index)} alt="" />
          </div>
          <div className={styles.total}><b>{(cp.product.price * cp.quantity).toFixed(2)} €</b></div>
          {/* <button >x</button> */}
          <img className="icon" src="/remove.png" onClick={() => deleteFromCart(index)} alt="" />
        </div>)}</div>

        {cart.length > 0 && <button onClick={pay}>Telli</button>}
    </div>
  )
}

export default Cart