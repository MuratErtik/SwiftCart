import { Button, Divider, IconButton } from "@mui/material";
import React from "react";
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import { Close } from "@mui/icons-material";

const CartItem = () => {
    const handleUpdateQuantity = () => {

    }
    return (
        <div className='border rounded-md relative'>
            <div className='p-5 flex gap-3'>
                <div>
                    <img className='w-[90px] rounded-md' src="https://images.pexels.com/photos/9558786/pexels-photo-9558786.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                </div>
                <div className='space-y-2'>
                    <h1 className="font-semibold text-lg">Murat Clothing</h1>
                    <p className="text-gray-600 font-medium text-sm">Turquoise Blue Stonework Satin Designer Saree</p>
                    <p className="text-gray-400 text-xs"> <strong>Sold by: </ strong>Natural Lifestyle Products </p>
                    <p className='text-sm' >7 days replacement available</p>
                    <p className='text-sm text-gray-500'><strong>quantity : </strong> 5 </p>


                </div>
                <Divider />
                <div className= 'flex justify-between items-center'>
                    <div className='px-5 py-2 flex justify-between items-center'>
                        <div className="flex items-center gap-2 w-[140px] justify-between">
                            <Button onClick={handleUpdateQuantity} disabled={true} ><RemoveIcon /></Button>
                            <span>{5}</span>
                            <Button onClick={handleUpdateQuantity}><AddIcon /></Button>
                        </div>
                    </div>
                    <div className="pr-5">
                        <p className=' text-gray-700 font-medium'>799TL</p>
                    </div>

                </div>
                <div className='absolute top-1 right-1'>
                    <IconButton color="primary">
                        <Close/>
                    </IconButton>
                </div>

            </div>
        </div>
    )
}

export default CartItem