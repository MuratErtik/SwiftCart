import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import Navbar from './customer/component/Navbar/Navbar.tsx';
import customeTheme from './Theme/customTheme.ts';
import Home from './pages/Home/Home.tsx';
import Deal from './pages/Home/Deal/Deal.tsx';
import Product from './pages/Product/Product.tsx';
import { BrowserRouter } from "react-router-dom";
import React from "react";
import ReactDOM from "react-dom";
import ProductDetails from "./pages/PageDetails/ProductDetails.tsx"
import Review from './pages/Review/Review.tsx';

export default function App() {
  return (
    <div>

      <ThemeProvider theme={customeTheme}>

        <div>
          <Navbar />
          {/* <Home/>
          <Product/> */}
          {/* <ProductDetails/> */}
          <Review/>
        </div>

      </ThemeProvider>



    </div>


  )
}
