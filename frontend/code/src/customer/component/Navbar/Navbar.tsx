import { Avatar, Box, Button, Icon, IconButton, useMediaQuery, useTheme } from "@mui/material";
import React from "react";
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import StorefrontIcon from '@mui/icons-material/Storefront';

const Navbar = () => {

    const theme = useTheme();
    const isLarge = useMediaQuery(theme.breakpoints.up("lg"));

    return (


        <div>
            <Box>
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
                            {["Men","Women","Home & Furniture","Electronic"].map((item) => <li className="mainCategory hover:text-primary-color 
                            hover:border-b-2 h-[70px] px-4 border-primary-color flex items-center">
                                {item}
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
            </Box>
        </div>
    )
}

export default Navbar