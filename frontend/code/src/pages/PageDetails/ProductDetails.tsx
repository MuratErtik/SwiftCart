import React from "react";
import StarIcon from '@mui/icons-material/Star';
import { Button, Divider } from "@mui/material";
import ShieldIcon from '@mui/icons-material/Shield';
import { blue } from "@mui/material/colors";
import WorkspacePremiumIcon from '@mui/icons-material/WorkspacePremium';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import WalletIcon from '@mui/icons-material/Wallet';
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import SimilarProduct from "./SimilarProduct.tsx";
import ReviewCard from "../Review/ReviewCard.tsx";

const ProductDetails = () => {
    const [quantity, setQuantity] = React.useState(1);
    return (
        <div className="bx-5 lg:px-20 pt-10">
            <div className='grid grid-cols-1 lg:grid-cols-2 gap-10'>
                <section className="flex flex-col lg:flex-row gap-5">
                    <div className='w-full lg:w-[15%] flex flex-wrap lg:flex-col gap-3'>
                        {[1, 1, 1, 1].map((item) => <img className='lg:w-full w-[50px] cursor-pointer rounded-md'
                            src="https://images.pexels.com/photos/1304647/pexels-photo-1304647.jpeg?auto=compress&cs=tinysrgb&w=1200" />)}

                    </div>
                    <div className='w-full lg:w-[85%]'>
                        <img className='w-full rounded-md' src="https://media.istockphoto.com/id/1354020635/tr/fotoğraf/white-t-shirt-mockup-front-used-as-design-template-tee-shirt-blank-isolated-on-white.jpg?s=2048x2048&w=is&k=20&c=ndfN9it7EGoS2TUu1mw9xLtSbBsVPBmHRl-DHSO1xaM=" alt="" />
                    </div>

                </section>
                <section>
                    <h1 className="font-bold text-lg text-primary-color">Men Clothing</h1>
                    <p className=' text-gray-500 font-semibold'>Men White Tshirt</p>
                    <div className='flex justify-between items-center py-2 border w-[180px] px-3 mt-5'>
                        <div className='flex gap-1 items-center'>
                            <span>4</span>
                            <StarIcon sx={{ color: "primary", fontSize: "17px" }} />
                        </div>
                        <Divider orientation='vertical' flexItem />
                        <span>12 Ratings</span>
                    </div>
                    <div>
                        <div className='price flex items-center gap-3 mt-5 text-2xl'>
                            <span className="font-sans text-gray-800">250</span>
                            <span className="line-through text-gray-400">449</span>
                            <span className="text-primary-color font-semibold">%25</span>

                        </div>
                        <p className="text-sm">Inclusive of all taxes. Free Shipping above 1500.</p>
                    </div>
                    <div className='mt-7 space-y-3'>
                        <div className='flex items-center gap-4'>
                            <ShieldIcon sx={{ color: "#009FFD" }} />
                            <p>Authentic & Quality Assured</p>
                        </div>
                        <div className='flex items-center gap-4'>
                            <WorkspacePremiumIcon sx={{ color: "#009FFD" }} />
                            <p>100% money back guarantee</p>
                        </div>
                        <div className='flex items-center gap-4'>
                            <LocalShippingIcon sx={{ color: "#009FFD" }} />
                            <p>Free Shipping & Returns</p>
                        </div>
                        <div className='flex items-center gap-4'>
                            <WalletIcon sx={{ color: "#009FFD" }} />
                            <p>Pay on delivery might be available</p>
                        </div>

                    </div>
                    <div className='mt-7 space-y-2'>
                        <h1>Quantity</h1>
                        <div className='flex items-center gap-2 w-[140px] justify-between'>
                            <Button disabled={quantity == 1} onClick={() => setQuantity(quantity - 1)}><RemoveIcon /></Button>
                            <span>{quantity}</span>
                            <Button onClick={() => setQuantity(quantity + 1)}><AddIcon /></Button>
                        </div>

                    </div>
                    <div className='mt-12 flex items-center gap-5'>
                        <Button fullWidth variant="contained" startIcon={<AddShoppingCartIcon />} sx={{ py: "1rem" }}>
                            Add To Cart
                        </Button>
                        <Button fullWidth variant="contained" startIcon={<FavoriteBorderIcon />} sx={{ py: "1rem" }}>
                            Add To Wishlist
                        </Button>
                    </div>
                    <div className='mt-5'>
                        <p>The saree comes with an unstitched blouse piece The blouse worn by the model might be for modelling purpose only.
                            Check the image of the blouse piece to understand how the actual blouse piece looks like.</p>
                    </div>
                    <div className='mt-12 space-y-5'>
                        <ReviewCard/>
                        <Divider/>

                    </div>
                </section>

            </div>

            <div className='mt-20'>
                <h1 className="text-lg font-bold">Similar Product</h1>
                <div className='pt-5'>
                    <SimilarProduct />
                    <Divider/>


                </div>
            </div>
        </div>
    )
}

export default ProductDetails