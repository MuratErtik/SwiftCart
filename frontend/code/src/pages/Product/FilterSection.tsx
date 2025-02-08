import { Button, Divider, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup } from "@mui/material";
import { teal } from "@mui/material/colors";
import React, { useState }  from "react";
import { colors } from "../../data/color.ts";
import {prices} from "../../data/price.ts"
import {discounts} from "../../data/discount.ts"

import { useSearchParams } from "react-router-dom";


const FilterSection = () => {

    const [expendColor, setExpendColor] = useState(false);
    const [searchParams, setSearchParams] = useSearchParams();
    const handleColorToggle = () => {
        setExpendColor(!expendColor)

    };
    const updateFilterParams = (e:any)=>{
        const {value,name}= e.target;

        if (value) {
            searchParams.set(name,value);
        }
        else{
            searchParams.delete(name);
        }
        setSearchParams(searchParams);
    };

    const clearAllFilters = () => {
        setSearchParams({}); // Tüm filtreleri temizler 
    
    };
    

    return (
        <div className=' -z-50 space-y-5 bg-secondary'>
            <div className='flex items-center justify-between h-[40px] px-9, lg:border-r'>
                <p className="text-lg font-semibold">Filters</p>
                <Button onClick={clearAllFilters} size='small' className='text-teal-600 cursor-pointer font-semibold bg-secondary'>
                    clear all
                </Button>
            </div>
            <Divider />
            <div className='px-9 space-y-6'>
                <section>
                    <FormControl>
                        <FormLabel sx={{
                            fontSize: "16px", fontWeight: "bold", color: "#009FFD", pb: "14px",
                        }}
                            className='text-2xl font-semibold text-primary' id="color">Color</FormLabel>
                        <RadioGroup
                            aria-labelledby="color"
                            defaultValue=""
                            name="color">

                            {colors.slice(0, expendColor ? colors.length : 5).map((item) => (
                                <FormControlLabel
                                    key={item.name}
                                    value={item.name}
                                    control={<Radio />}
                                    label={
                                        <div className='flex items-center gap-3'>
                                            <p>{item.name}</p>
                                            <span
                                                style={{ backgroundColor: item.hex }}
                                                className={`h-5 w-5 rounded-full ${item.name === "White" ? "border" : ""}`}
                                            ></span>
                                        </div>
                                    }
                                />
                            ))}

                        </RadioGroup>
                    </FormControl>
                    <div>
                        <Button onClick={handleColorToggle}
                            className='text-primary-color cursor-pointer hover:text-blue-900 flex items-center'>
                            {expendColor ? "hide" : `+${colors.length - 5} more option`}
                        </Button>
                    </div>
                </section>

                <section>
                    <FormControl>
                        <FormLabel sx={{ fontSize: "16px", fontWeight: "bold", pb: "14px", color: "#009FFD" }}
                            className="text-2xl font-semibold" id="price">Price</FormLabel>
                        <RadioGroup name="price" onChange={updateFilterParams} aria-labelledby="price" value={searchParams.get("price") || ""}>
                            {prices.map((item, index) => (
                                <FormControlLabel key={item.name} value={item.value} control={<Radio size="small" />} label={item.name} />
                            ))}

                        </RadioGroup>
                    </FormControl>


                </section>
                <section>
                    <FormControl>
                        <FormLabel sx={{ fontSize: "16px", fontWeight: "bold", pb: "14px", color: "#009FFD" }}
                            className="text-2xl font-semibold" id="discount">Discount</FormLabel>
                        <RadioGroup name="discount" onChange={updateFilterParams} aria-labelledby="discount" value={searchParams.get("discount") || ""}>
                            {discounts.map((item, index) => (
                                <FormControlLabel key={item.name} value={item.value} control={<Radio size="small" />} label={item.name} />
                            ))}

                        </RadioGroup>
                    </FormControl>


                </section>

            </div>
        </div>
    )
}

export default FilterSection