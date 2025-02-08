import React, { useEffect, useState } from "react";
import "./ProductCard.css";
import { Button } from "@mui/material";
import { Favorite, ModeComment } from "@mui/icons-material";
import { teal } from "@mui/material/colors";

const images = [
    "https://images.pexels.com/photos/4066290/pexels-photo-4066290.jpeg?auto=compress&cs=tinysrgb&w=1200",
    "https://images.pexels.com/photos/1304647/pexels-photo-1304647.jpeg?auto=compress&cs=tinysrgb&w=1200"
]
const ProductCard = () => {
    const [currentImage, setCurrentImage] = useState(0)
    const [isHovered, setIsHovered] = useState(false);
    useEffect(() => {
        let interval: any
        if (isHovered) {
            interval = setInterval(() => {
                setCurrentImage((prevImage) => (prevImage + 1) % images.length);
            }, 1000);
        }
        else if (interval) {
            clearInterval(interval);
            interval = null;
        }
        return () => clearInterval(interval);

    }, [isHovered])

    return (
        <div>
            <div className='group px-4 relative'>
                <div className="card" onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)}>
                    {images.map((item, index) => <img className="card-media object-top" src={item} alt=""
                        style={{ transform: `translateX(${(index - currentImage) * 100}%)` }}

                    />)}

                    {isHovered && <div className='indicator flex flex-col items-center space-y-2'>
                        <div className="flex gap-3">
                            <Button variant="contained" color="secondary">
                                < Favorite sx={{ color: "#009FFD" }} />
                            </Button>
                            <Button variant="contained" color="secondary">
                                <ModeComment sx={{ color: "#009FFD" }} />

                            </Button>
                        </div>

                    </div>}
                </div>
                <div className= 'details pt-3 space-y-1 group-hover-effect rounded-md'>
                    <div className="name">
                        <h1>H&M</h1>
                        <p>White Tshirt</p>
                    </div>
                    <div className='price flex items-center gap-3'>
                        <span className="font-sans •text-gray-800">
                            400
                        </span>   
                        <span className="thin-line-trhough text-gray-400">
                            <s>999</s>
                        </span> 
                        <span className="text-primary-color font-semibold">
                            %60
                        </span> 


                    </div>
                </div>

            </div>
        </div>
    )
}

export default ProductCard