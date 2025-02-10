import React from "react";
import StarIcon from '@mui/icons-material/Star';
import { Divider } from "@mui/material";
import ReviewCard from "./ReviewCard.tsx";


const Review = () => {
    return (
        <div className='p-5 lg:px-20 flex flex-col lg:flex-row gap-20'>
            <section className='w-full md:w-1/2 1g:w-[30%] space-y-2'>
                <img src="https://images.pexels.com/photos/9558786/pexels-photo-9558786.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                <div>
                    <div>
                        <p className='font-bold text-xl'>Clothing</p>
                        <p className='text-lg text-gray-600'>Men's White Shirt</p>
                    </div>
                    <div>
                        <div className='price flex items-center gap-3 mt-5 text-2xl'>
                            <span className="font-sans text-gray-800">250</span>
                            <span className="line-through text-gray-400">449</span>
                            <span className="text-primary-color font-semibold">%25</span>

                        </div>
                        
                    </div>

                </div>
            </section>
            <section className= 'space-y-5 w-full'>
                {[1,1,1,1,1,1].map((item) => <div className='spyЗ'><Divider/> <ReviewCard/></div>)}
            </section>
        </div>
    )
}

export default Review


