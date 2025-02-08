import React, { useState } from "react";
import FilterSection from "./FilterSection.tsx";
import { BrowserRouter } from "react-router-dom";
import ProductCard from "./ProductCard.tsx";
import { useTheme } from "@emotion/react";
import { Box, Divider, FormControl, IconButton, InputLabel, MenuItem, Select, useMediaQuery } from "@mui/material";
import { FilterAlt } from "@mui/icons-material";

const Product = () => {

    const theme = useTheme();
    const isLarge = useMediaQuery(theme.breakpoints.up("lg"))
    const [sort,setSort] = useState()
    const handleSortChange = (event:any) => {
        setSort(event.target.value)
    }

    return (
        <div className=' -z-10 mt-10'>
            <div>
                <h1 className="text-3x1 text-center font-bold text-gray-700 pb-5 px-9 uppercase space-x-2">MEN TSHIRT</h1>
            </div>
            <div className="lg:flex">
                <section className="filter_section hidden lg:block w-[20%]">
                    <FilterSection />
                </section >
                <div className='w-full lg:w-[80%] space-y-5'>
                    <div className='flex justify-between items-center px-9 h-[40px]'>
                        <div className='relative w-[50%]'>
                            {
                                !isLarge && (
                                    <IconButton>
                                        <FilterAlt />
                                    </IconButton>
                                )
                            }
                            {
                                !isLarge && (
                                    <Box>
                                        <FilterSection />
                                    </Box>
                                )
                            }

                        </div>
                        <FormControl size="small" sx={{width:"200px"}}>
                            <InputLabel id="demo-simple-select-label">Price Filter</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={sort}
                                label="Price Filter"
                                onChange={handleSortChange}
                            >
                                <MenuItem value={"proce_low"}>Price: Low - High</MenuItem>
                                <MenuItem value={"price_high"}>Price: High - Low</MenuItem>
                                
                            </Select>
                        </FormControl>





                    </div>
                    <Divider/>
                    <section className='products_section grid sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-y-5 px-5 justify-center'>
                        {[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1].map((item) => <ProductCard/>)}
                    </section>
                </div>

            </div>


        </div >
    )
}

export default Product