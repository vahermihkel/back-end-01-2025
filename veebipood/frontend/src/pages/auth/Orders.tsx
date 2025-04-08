import { useEffect, useState } from "react"
import { Order } from "../../models/Order";
import styles from "../../css/Pagination.module.css";

// Reacti Hookid:
// 1. use algavad
// 2. imporditud
// 3. funktsionaalsed
// 4. ei tohi olla tingimuslikult loodud
// 5. ei tohi olla loodud funktsiooni sees

function Orders() {
  const [orders, setOrders] = useState<Order[]>([]);
  const size = 1;  
  const [activePage, setActivePage] = useState(1);
  const [pages, setPages] = useState<number[]>([]);

  useEffect(() => {
   fetch(`http://localhost:8080/orders?size=${size}&page=${activePage-1}`, {
    headers: {"Authorization": "Bearer " + sessionStorage.getItem("token") || ""}
   }).then(res => res.json())
    .then(json => {
      setOrders(json.content);
      const pagesArray = [];
        for (let page = 1; page <= json.totalPages; page++) {
          pagesArray.push(page);
        }
        setPages(pagesArray);
    });
  }, [activePage]);

  function changePage(newPage: number) {
    setActivePage(newPage);
  }

  return (
    <div>
      {orders.map(order => <div>{order.id}</div>)}

      <button disabled={activePage === 1} onClick={() => changePage(activePage-1)}>{"<"}</button>
      {
        pages
        // .slice(activePage >= 3 ? activePage-3 : 0, 
        //     activePage <= pages.length-2 ? activePage+2 : pages.length)
            .map(page => 
          <button 
            hidden={page < activePage - 2 || page > activePage + 2}
            key={page} 
            className={page === activePage ? styles.active : undefined} 
            onClick={() => changePage(page)}>
              {page}
          </button> )
      }
      <button disabled={activePage === pages.length} onClick={() => changePage(activePage+1)}>{">"}</button>

    </div>
  )
}

export default Orders