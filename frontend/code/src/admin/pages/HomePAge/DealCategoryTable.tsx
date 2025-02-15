import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
import HomeCategoryTable from "./HomeCategoryTable.tsx";
import { Button } from "@mui/material";
import { useFormik } from "formik";



const DealCategoryTable = () => {
    
    return(
        <div>
           <HomeCategoryTable/>
        </div>
    )
}

export default DealCategoryTable