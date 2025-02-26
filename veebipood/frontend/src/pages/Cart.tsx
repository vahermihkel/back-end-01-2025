import { useContext, useState } from "react";
import { useTranslation } from "react-i18next";
import { CartProduct } from "../models/CartProduct";
import styles from "../css/Cart.module.css";
import { CartSumContext } from "../store/CartSumContext";
import { Link } from "react-router-dom";

function Cart() {
  const { t } = useTranslation();
  // return <h1>{t('Welcome to React')}</h1>
  const [cart, setCart] = useState<CartProduct[]>(JSON.parse(localStorage.getItem("cart") || "[]"))
  const {empty, decrease, increase} = useContext(CartSumContext);

  function emptyCart() {
    setCart([]); 
    localStorage.setItem("cart", JSON.stringify([]));
    empty();
  }

  function decreaseQuantity(index: number) {
    cart[index].quantity--;
    if (cart[index].quantity === 0) {
      cart.splice(index, 1);
    }
    setCart(cart.slice()); // HTMLi uuenduseks
    localStorage.setItem("cart", JSON.stringify(cart)); // LSi uuenduseks
    decrease(cart[index].product.price); // Contexti muutuja uuenduseks
  } 

  function increaseQuantity(index: number) {
    cart[index].quantity++;
    setCart(cart.slice());
    localStorage.setItem("cart", JSON.stringify(cart));
    increase(cart[index].product.price);
  } 

  function deleteFromCart(index: number) { //.delete()   .remove()
    decrease(cart[index].product.price * cart[index].quantity);
    cart.splice(index, 1); // splice --> kustutamiseks.  splice(MITMES, MITU_TK)
    setCart(cart.slice()); // HTMLi uuendamiseks   -->   .slice() koopia tegemiseks
    //setCart([...cart]) // HTMLi uuendamiseks   -->   [...cart] koopia tegemiseks
    localStorage.setItem("cart", JSON.stringify(cart));
  } 

  function pay() {
    fetch("http://localhost:8080/orders", {
      method: "POST",
      body: JSON.stringify(cart),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + sessionStorage.getItem("token") || ""
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

  function calculateCartSum() {
    let sum = 0;
    // sum = sum + 5 * 107;
    cart.forEach(cartProduct => sum += cartProduct.product.price * cartProduct.quantity);
    return sum;
  }

  return (
    <div>
      {cart.length === 0 && <div>{t("cart-empty")}</div>}
      {cart.length > 0 && <button onClick={emptyCart}>Tühjenda</button>}
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

        {cart.length > 0 && 
          <>
            {sessionStorage.getItem("token") !== null ?
              <button onClick={pay}>Telli</button> :
              <Link to="/login">
                <button>Logi sisse, et tellida</button>
              </Link>
              }
            <div>Kogusumma: {calculateCartSum()}€</div>
          </>
        }

    </div>
  )
}

export default Cart

// Saadetakse argument:
// onClick={() => decreaseQuantity(index)}

// Ei saadeta argumenti:
// onClick={pay}
// Saab ka nii:
// onClick={() => pay()}

// Kindlasti ei tee onClick:
// onClick={pay()}
// onClick={decreaseQuantity(index)}
// Sellisel juhul paneb selle funktsiooni koheselt käima