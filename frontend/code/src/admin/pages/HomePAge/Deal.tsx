import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
import HomeCategoryTable from "./HomeCategoryTable.tsx";
import { Button } from "@mui/material";
import DealTable from "./DealTable.tsx";
import DealCategoryTable from "./DealCategoryTable.tsx";
import CreateDealFrom from "./CreateDealFrom.tsx";

const tabs=[
    "Deals","Category","Create Deal"
]

const Deal = () => {
    const [activeTab, setActiveTab]=useState("Deals")
    return(
        <div>
           <div className="flex gap-4">
                {tabs.map((item) => <Button onClick={()=>setActiveTab(item)} variant={activeTab==item?"contained":"outlined"}>{item}</Button>)}
           </div>
           <div className="mt-5 flex flex-col justify-center items-center h-[70vh]">
                {activeTab=="Deals"?<DealTable/>:activeTab=="Category"?<DealCategoryTable/>:<div><CreateDealFrom/></div>}
           </div>
        </div>
    )
}

export default Deal