import { createContext, ReactNode, useState } from "react";
import { CartProduct } from "../models/CartProduct";

// eslint-disable-next-line react-refresh/only-export-components
export const CartSumContext = createContext({
  cartSum: 0,
  increase: (value: number) => {console.log(value)},
  decrease: (value: number) => {console.log(value)},
  empty: () => {}
});

export const CartSumContextProvider = ({children}: {children: ReactNode}) => {
  const [cartSum, setCartSum] = useState(calculateCartSum());

  function increase(value: number) {
    setCartSum(cartSum + value);
  }

  function decrease(value: number) {
    setCartSum(cartSum - value);
  }

  function empty() {
    setCartSum(0);
  }

  function calculateCartSum() {
    const cart: CartProduct[] = JSON.parse(localStorage.getItem("cart") || "[]");
    let sum = 0;
    // sum = sum + 5 * 107;
    cart.forEach(cartProduct => sum += cartProduct.product.price * cartProduct.quantity);
    return sum;
  }

  return (
    <CartSumContext.Provider value={{cartSum, increase, decrease, empty}}>
      {children}
    </CartSumContext.Provider>
    )
}