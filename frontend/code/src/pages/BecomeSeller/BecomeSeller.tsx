import React, { useState } from "react";
import SellerAccountForm from "./SellerAccountForm.tsx";
import SellerLoginForm from "./SellerLoginForm.tsx";
import { Button } from "@mui/material"

const BecomeSeller = () => {
    const [isLogin, setIsLogin] = useState(false);
    const handleShowPage = () => {
        setIsLogin(!isLogin);
    };
    return (
        <div className='grid md:gap-10 grid-cols-3 min-h-screen'>
            <section className='lg:col-span-1 md:col-span-2 col-span-3 p-10 shadow-lg rounded-b-md'>
                {!isLogin ? <SellerAccountForm /> : <SellerLoginForm />}
                <div className='mt-10 space-y-2'>
                    <h1 className='text-center text-sm font-medium'>Have an Account</h1>
                    <Button onClick={handleShowPage} fullWidth sx={{ py: "11px" }} variant='outlined' >
                        {isLogin ? "Register" : "Login"}

                    </Button>
                </div>
            </section>
            <section className='hidden md:col-span-1 lg:col-span-2 md:flex justify-center items-center'>
                <div className="lg:w-[70%] px-5 space-y-10">
                    <div className="grid justify-items-center text-center space-y-2 font-bold">
                        <p className="text-2xl">Join the Best Seller Team</p>
                        <p className="text-lg text-primary-color">Boost your sales everyday</p>
                        <img width={470} src="https://images.pexels.com/photos/15282072/pexels-photo-15282072/free-photo-of-insanlar-kitaplar-rahatlatici-hayvan.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                    </div>
                </div>

            </section>

        </div>
    )
}

export default BecomeSeller