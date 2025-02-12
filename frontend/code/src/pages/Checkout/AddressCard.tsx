import { Radio } from "@mui/material";
import React from "react";

const AddressCard = () => {
    const handleChange = (event:any) => {
        console.log(event.target.checked)
    }
    return (
        <div className='p-5 border rounded-md flex'>
            <div>
                <Radio checked={true} onChange={handleChange} value={""} name="radio-button"  />
            </div>
            <div className=' space-y-3 '>
                <h1>Murat</h1>
                <p className= 'w-[320px]'>ataturk caddesi serdar mahallesi no:12/2 Istanbul</p>
                <p> Mobile:<strong>53358538442</strong></p>
            </div>
        </div>
    )
}


export default AddressCard