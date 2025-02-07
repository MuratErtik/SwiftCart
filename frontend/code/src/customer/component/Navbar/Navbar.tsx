import { Avatar, Box, Button, Icon, IconButton, useMediaQuery, useTheme } from "@mui/material";
import React, { useState } from "react";
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import StorefrontIcon from '@mui/icons-material/Storefront';
import CategorySheet from "./CategorySheet.tsx";
import {mainCategory} from "../../../data/category/mainCategoruy.ts";

const Navbar = () => {

    const theme = useTheme();
    const isLarge = useMediaQuery(theme.breakpoints.up("lg"));
    const [selectedcategory,setselectedCategory] = useState("men");
    const [showCategorySheet,setShowCategorySheet] = useState(false);

    return (


        <div>
            <Box className="sticky top-0 left-0 right-0 bg-white " sx={{zIndex:2}}>
                <div className="flex item-center justify-between px-5 lg:px-20 h-[70px] border-b">
                    <div className="flex items-center gap-9">
                        <div className="flex items-center gap-2">
                            {!isLarge && <IconButton>
                                <MenuIcon/>

                                
                            </IconButton>}
                            <h1 className="logo cursor-pointer text-lg md:text-2xl text-[#009FFD]">
                                SwiftCart
                            </h1>
                        </div>
                        <ul className="flex items-center font-medium text-gray-800">
                            {mainCategory.map((item) => <li onMouseLeave={()=>{setShowCategorySheet(false);}} 
                            onMouseEnter={()=>{setShowCategorySheet(true);setselectedCategory(item.categoryId);}}
                            className="mainCategory hover:text-primary-color 
                            hover:border-b-2 h-[70px] px-4 border-primary-color flex items-center">
                                {item.name}
                            </li>)}
                        </ul>
                    </div>
                    <div className="flex gap-1 lg:gap-6 items-center">
                        <IconButton>
                            <SearchIcon sx={{ fontSize: 25 }}></SearchIcon>
                        </IconButton>
                        {
                            false ? <Button className='flex items-center gap-2'>
                                <Avatar
                                sx={{width:25,height:25}}
                                    src="https://cdn1.iconfinder.com/data/icons/user-pictures/100/male3-1024.png"/>
                                    <h1 className="font-semibold hidden lg:block">Murat</h1>



                            </Button> : <Button variant="contained">
                                Login
                            </Button>
                        }
                        <IconButton>
                            <FavoriteBorderIcon sx={{ fontSize: 25 }} />
                        </IconButton>
                        <IconButton>
                            <AddShoppingCartIcon sx={{ fontSize: 25 }} className="text-gray-700"></AddShoppingCartIcon>
                        </IconButton>
                        {isLarge && <Button startIcon={<StorefrontIcon />} variant="outlined">
                            Become Seller
                        </Button>}
                    </div>
                </div>
                {showCategorySheet &&  <div onMouseLeave={ ()=>setShowCategorySheet(false)} onMouseEnter={()=>setShowCategorySheet(true)} 
                className='categorySheet absolute top-[4.41rem] left-20 right-20 border bg-slate-500'>
                    <CategorySheet selectedCategory={selectedcategory}/>
                </div>}
            </Box>
        </div>
    )
}

export default Navbar