import { ElectricBolt } from "@mui/icons-material";
import { Avatar } from "@mui/material";
import { blue } from "@mui/material/colors";
import React from "react";

const OrderItem = () => {
    return (
        <div className= 'text-sm bg-white p-5 space-y-4 border rounded-md cursor-pointer'>
            <div className='flex items-center gap-5'>
                <div>
                    <Avatar sizes="small" sx={{bgcolor:blue[500]}}> <ElectricBolt/> </Avatar>
                    
                </div>
                <div>
                    <h1 className="font-bold text-primary-color">PENDING</h1>
                    <p>Arriving By Wed, Feb 12</p>
                </div>

            </div>
            <div className='p-5 bg-blue-50 flex gap-3'>
                <div>
                    <img className='w-[70px]' src="https://images.pexels.com/photos/2113994/pexels-photo-2113994.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                </div>
                <div className='w-full space-y-2'>
                    <h1 className="font-bold">Murat Clothing</h1>
                    <p>Cellecor RAY 1.43" AMOLED Display | 700 NITS | AOD | BT -Calling | AI Voice | Split Screen Smartwatch (Black Strap, Free Size) </p>
                    <p>
                        <strong>size:</strong>
                        FREE

                    </p>
                </div>
            </div>
        </div>
    )
}

export default OrderItem