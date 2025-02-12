import { Box, Button, Divider } from "@mui/material";
import React from "react";
import { Navigate, useNavigate } from "react-router-dom";
import OrderStepper from "./OrderStepper.tsx";
import { Payment } from "@mui/icons-material";

const OrderDetails = () => {
    const navigate = useNavigate()
    return (
        <Box className='space-y-5'>
            <section className="flex flex-col gap-5 justify-center items-center">
                <img className='w-[100px]' src="https://images.pexels.com/photos/2113994/pexels-photo-2113994.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                <div className='text-sm space-y-1 text-center'>
                    <h1 className="font-bold">Murat Clothing</h1>
                    <p>Cellecor RAY 1.43" AMOLED Display | 700 NITS | AOD | BT -Calling | AI Voice | Split Screen Smartwatch (Black Strap, Free Size)</p>
                    <p><strong>Size:</strong>M</p>

                </div>
                <div>
                    <Button onClick={() => navigate(`reviews/${5}/create`)}>Write Review</Button>
                </div>

            </section>
            <section className="border p-5">
                <OrderStepper orderStatus={"SHIPPED"} />
            </section>
            <div className='border p-5'>
                <h1 className='font-bold pb-3'>Delivery Address</h1>
                <div className='text-sm space-y-2'>
                    <div className='flex gap-5 font-medium'>
                        <p>{"Murat ev2"}</p>
                        <Divider flexItem orientation='vertical' />
                        <p>{5334453250}</p>


                    </div>
                    <p>Karanfil Mah Zincir Sokak No 12/2 Istanbul</p>
                </div>

            </div>
            <div className= 'border space-y-4'>
                <div className='flex justify-between text-sm pt-5 px-5'>
                    <div  className=' space-y-1'>
                        <p className='font-bold'>Total Item Price</p>
                        <p>You saved <span className= 'text-blue-500 font-medium text-xs'>140 on this item.</span></p>

                    </div>
                    <p className="font-medium">899 TL</p>

                </div>
                <div className="px-5">
                    <div className=' bg-blue-50 px-5 py-2 text-xs font-medium flex items-center gap-3'>
                        <Payment/>
                        <p>Pay On Delivery</p>

                    </div>
                </div>
                <Divider/>
                <div className='px-5 pb-5'>
                    <p className= 'text-xs'><strong>Sold by : </strong>MuratsLand</p>
                </div>
                <div className="p-10">
                    <Button disabled={true} color='error' sx={{ py: "0.7rem" }} className='' variant='outlined' fullWidth>
                        {true?"Order Canceled":"Cancel Order"}
                    </Button>

                </div>

            </div>

        </Box>

    )
}

export default OrderDetails