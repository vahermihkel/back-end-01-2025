import { useState } from "react";
import { useTranslation } from "react-i18next";
import { Product } from "../models/Product";

function Cart() {
  const { t } = useTranslation();
  // return <h1>{t('Welcome to React')}</h1>
  const [cart, setCart] = useState<Product[]>(JSON.parse(localStorage.getItem("cart") || "[]"))

  function empty() {
    // KODUS
  }

  function deleteFromCart(index: number) { //.delete()   .remove()
    cart.splice(index, 1); // splice --> kustutamiseks.  splice(MITMES, MITU_TK)
    setCart(cart.slice()); // HTMLi uuendamiseks   -->   .slice() koopia tegemiseks
    //setCart([...cart]) // HTMLi uuendamiseks   -->   [...cart] koopia tegemiseks
    localStorage.setItem("cart", JSON.stringify(cart));
  } 

  return (
    <div>
      <div>{t("cart-empty")}</div>
      <div>{cart.map((product, index) => 
        <div key={product.id}>
          <div>{product.name}</div>
          <div>{product.price}</div>
          <button onClick={() => deleteFromCart(index)}>x</button>
        </div>)}</div>
    </div>
  )
}

export default Cart