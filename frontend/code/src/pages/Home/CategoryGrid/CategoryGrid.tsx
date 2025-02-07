import React from "react";

const CategoryGrid = () => {
    return (
        <div className='grid gap-4 grid-rows-12 grid-cols-12 lg:h-[600px] px-5 lg:px-20'>

            <div className=' col-span-3 row-span-12 text-white'>
                <img  className='w-full h-full object-cover object-top rounded-md'
                src="https://images.pexels.com/photos/1261422/pexels-photo-1261422.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>
            <div className=' col-span-2 row-span-6 text-white'>
                <img className='w-full h-full object-cover object-top rounded-md'
                src="https://images.pexels.com/photos/3026284/pexels-photo-3026284.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>
            <div className=' col-span-4 row-span-6 text-white'>
                <img className='w-full h-full object-cover object-top rounded-md'
                src="https://images.pexels.com/photos/2522683/pexels-photo-2522683.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>
            <div className=' col-span-3 row-span-12 text-white'>
                <img className='w-full h-full object-cover object-top rounded-md'
                src="https://images.pexels.com/photos/2043590/pexels-photo-2043590.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>
            <div className=' col-span-4 row-span-6 text-white'>
                <img className='w-full h-full object-cover object-top rounded-md'
                src="https://images.pexels.com/photos/6347888/pexels-photo-6347888.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>

            <div className=' col-span-2 row-span-6 text-white'>
                <img className='w-full h-full object-cover object-top rounded-md'
                 src="https://images.pexels.com/photos/5039659/pexels-photo-5039659.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />

            </div>


        </div>
    )
}

export default CategoryGrid