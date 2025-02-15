import React from "react";
import { Route, Routes } from "react-router-dom";
import SellerTable from "./pages/SellerTable.tsx";
import Coupon from "./pages/Coupon/Coupon.tsx";
import AddNewCouponForm from "./pages/Coupon/AddNewCouponForm.tsx";
import GridTable from "./pages/HomePAge/GridTable.tsx";
import ElectronicTable from "./pages/HomePAge/ElectronicTable.tsx";
import ShopByCategoryId from "./pages/HomePAge/ShopByCategoryId.tsx";
import Deal from "./pages/HomePAge/Deal.tsx";

const AdminRoutes = () =>{
    return(
        <div>
            <Routes>
                < Route path='/' element={<SellerTable/>}/>
                < Route path='/coupon' element={<Coupon/>}/>
                < Route path='/add-coupon' element={<AddNewCouponForm/>}/>
                < Route path= '/home-grid' element={<GridTable/>}/>
                <Route path='/electronics-category' element={<ElectronicTable/>}/>
                <Route path='/shop-by-category' element={<ShopByCategoryId/>}/>
                <Route path='/deals' element={<Deal/>}/>
            </Routes>
        </div>
    )
}

export default AdminRoutes