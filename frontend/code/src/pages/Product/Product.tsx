import React from "react";
import FilterSection from "./FilterSection.tsx";
import ProductCard from "./ProductCard.tsx";
import { useTheme } from "@emotion/react";
import { Box, IconButton, useMediaQuery } from "@mui/material";
import { FilterAlt } from "@mui/icons-material";

const Product = () => {

    const theme = useTheme();
    const isLarge = useMediaQuery(theme.breakpoints.up("lg"))

    return (
        <div className=' -z-10 mt-10'>
            <div>
                <h1 className="text-3x1 text-center font-bold text-gray-700 pb-5 px-9 uppercase space-x-2">SAMPLE</h1>
            </div>
            <div className="lg:flex">
                <section className="filter_section hidden lg:block w-[20%]">
                    <FilterSection />
                </section >
                <div className='w-full lg:w-[80%] space-y-5'>
                    <div className="">
                        <div className= 'relative w-[50%]'>
                            {
                                !isLarge && (
                                    <IconButton>
                                        <FilterAlt/>
                                    </IconButton>
                                )
                            }
                            {
                                !isLarge && (
                                    <Box>
                                        <FilterSection/>
                                    </Box>
                                )
                            }
                            
                        </div>





                    </div>
                    <section className='products_section'>
                        <ProductCard />
                    </section>
                </div>

            </div>


        </div >
    )
}

export default Product