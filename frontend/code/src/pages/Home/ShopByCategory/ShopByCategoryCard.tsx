import React from "react";
import "./ShopByCategory.css"

const ShopByCategoryCard = () => {
    return (
        <div className='flex gap-3 flex-col justify-center items-center group cursor-pointer'>
            <div className= 'custom-border w-[150px] h-[150px] lg:w-[249px] lg:h-[249px] rounded-full bg-primary-color'>
                <img className='rounded-full group-hover:scale-95 transition-transform transform-duration-700 object-cover object-top h-full w-full'
                src="https://media.istockphoto.com/id/1456467041/tr/fotoğraf/beautiful-kitchen-in-new-farmhouse-style-luxury-home-with-island-pendant-lights-and.jpg?b=1&s=612x612&w=0&k=20&c=rlFDxoNn_9keWlTF075mYMosmu7AZVQ9Uz1H1gUSTQ0=" alt="" />
            </div>
            <h1>Kitchen & Table</h1>

        </div>
    )
}

export default ShopByCategoryCard