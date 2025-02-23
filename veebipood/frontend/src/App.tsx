import './App.css'
import { Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import AdminHome from './pages/admin/AdminHome';
import Cart from './pages/Cart';
import ManageCategories from './pages/admin/ManageCategories';
import ManageProducts from './pages/admin/ManageProducts';
import NavigationBar from './components/NavigationBar';
import NotFound from './pages/NotFound';
import AddProduct from './pages/admin/AddProduct';
import SingleProduct from './pages/SingleProduct';
import EditProduct from './pages/admin/EditProduct';
import Login from './pages/auth/Login';
import Signup from './pages/auth/Signup';

function App() {

  return (
    <>
{/* path sisse kirjutan, mis järgneb localhost:5173-le */}
        <NavigationBar />
        <br /><br />

        <Routes>
          <Route path='/' element={<HomePage />} />
          <Route path='/cart' element={<Cart />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />

          <Route path='/product/:id' element={<SingleProduct />} />
          <Route path='/edit-product/:id' element={<EditProduct />} />

          <Route path='/admin' element={<AdminHome />} />
          <Route path='/admin/categories' element={<ManageCategories />} />
          <Route path='/admin/products' element={<ManageProducts />} />
          <Route path='/admin/add-product' element={<AddProduct />} />
          <Route path='*' element={<NotFound />} />
        </Routes>   
    </>
  )
}

export default App
