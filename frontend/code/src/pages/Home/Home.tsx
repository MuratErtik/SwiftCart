import React from "react";
import ElectricCategory from "./ElectricCategory/ElectricCategory.tsx";
import CategoryGrid from "./CategoryGrid/CategoryGrid.tsx";
import ShopByCategory from "./ShopByCategory/ShopByCategory.tsx";
import Deal from "./Deal/Deal.tsx";
import { Button } from "@mui/material";
import { Storefront } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigation = useNavigate();
    return (
        <div>
            <div className= 'space-y-5 1g:space-y-10 relative'>
                <ElectricCategory/>
                <CategoryGrid/>
                
                <div className="pt-20">
                    <h1 className="text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-20 text-center">TODAY'S DEAL</h1>
                    <Deal/>
                </div>
                <div className="py-20">
                    <h1 className="text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-20 text-center">SHOP BY CATEGORIES</h1>
                    <ShopByCategory/>
                </div>
                <section className=' lg:px-10 relative h-[100px] lg:h-[650px] object-cover'> <img className='w-full h-full' 
                src="https://images.pexels.com/photos/238118/pexels-photo-238118.jpeg?auto=compress&cs=tinysrgb&w=1200" alt=""/>
                <div className='absolute top-1/2 left-4 lg:left-[25rem] transform-translate-y-1/2 font-semibold lg:text-4xl space-y-3'>
                    <h1>Sell Your Product</h1>
                    <p className="text-lg md:text-2xl"> With <span className="logo text-50px">SwiftCart</span></p>
                    <div className='pt-6 flex justify-center'>
                        <Button onClick={()=> navigation("/become-seller")} startIcon={<Storefront/>} variant="contained" size="large">
                            Become Seller
                        </Button>
                    </div>
                    
                </div>
                </section>
                    
                
            </div>
        </div>
    )
}


export default Home