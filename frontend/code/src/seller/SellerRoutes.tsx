import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SellerDashboard from "./pages/sellerdashboard/SellerDashboard.tsx";
import Dashboard from "./pages/sellerdashboard/Dashboard.tsx";
import Products from "./page/Products/Products.tsx";
import AddProducts from "./page/Products/AddProducts.tsx";
import Order from "./page/Orders/Orders.tsx";
import Profile from "./page/Account/Profile.tsx";
import Payment from "./page/Payment/Payments.tsx";
import Transaction from "./page/Payment/Transaction.tsx";

const SellerRoutes = () => {
    return (
        <div>

            <Routes>
                <Route path="/" element={<Dashboard />} />
                <Route path='/products' element={<Products />} />
                <Route path='/add-product' element={<AddProducts />} />
                <Route path='/orders' element={<Order />} />
                <Route path='/account' element={<Profile />} />
                < Route path='/payment' element={<Payment />} />
                <Route path='/transaction' element={<Transaction/>} />
            </Routes>

        </div>
    )
}

export default SellerRoutes