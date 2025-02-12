import React from "react";
import ProfileFieldCard from "../../customer/component/ProfileFieldCard.tsx";
import { Divider } from "@mui/material";

const UserDetails = () => {
    return (
        <div className='flex justify-center py-10'>
            <div className="w-full lg:w-[70%]">
                <div className='flex items-center pb-3 justify-between'>
                    <h1 className='text-2xl font-bold •text-gray-600'>Persional Details </h1>


                </div>
                <div className="space-y-5">
                    <ProfileFieldCard keys='Name' value="Murat Ertik" />
                    <Divider/>
                    <ProfileFieldCard keys='Email' value="dmuratertik1@gmail.com" />
                    <Divider/>
                    <ProfileFieldCard keys='Mobile' value="533-789-3486" />
                </div>

            </div>

        </div>
    )
}

export default UserDetails