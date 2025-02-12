import { colors, Divider } from "@mui/material";
import { blue } from "@mui/material/colors";
import React from "react";

const PricingCart = () => {
    return (
        <div>
            <div className='space-y-3 p-5'>
                <div className='flex justify-between items-center'>
                    <span>Subtotal</span>
                    <span>899</span>

                </div>
                <div className='flex justify-between items-center'>
                <span>Discount</ span>
                <span>699</ span>

                </div>
                <div className='flex justify-between items-center'>
                <span>Shipping</ span>
                <span>69</ span>

                </div>
                <div className='flex justify-between items-center'>
                <span>Platform fee</ span>
                <span>free</ span>

                </div>
                <Divider/>
                <div className='flex justify-between items-center color-primary'>
                <span color="blue">Total</ span>
                <span>768</ span>

                </div>
            </div>
        </div>
    )
}

export default PricingCart