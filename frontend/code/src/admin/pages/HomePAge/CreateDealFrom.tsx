import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
import HomeCategoryTable from "./HomeCategoryTable.tsx";
import { Box, Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import { useFormik } from "formik";



const CreateDealFrom = () => {
    const formik = useFormik({
        initialValues: {
            discount: 0,
            category: ""
        },
        onSubmit: (values) => {

        }
    })
    return (
        <Box component={"form"} onSubmit={formik.handleSubmit} className=' space-y-6'>
            <Typography variant='h4' className='text-center'>Create Deal</Typography>
            <TextField fullWidth name="discount" label='discount' value={formik.values.discount} onChange={formik.handleChange}
                error={formik.touched.discount && Boolean(formik.errors.discount)}
                helperText={formik.touched.discount && formik.errors.discount}
            />
            <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">Category</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={formik.values.category}
                    label="Category"
                    onChange={formik.handleChange}
                >
                    <MenuItem value={10}>Ten</MenuItem>
                    <MenuItem value={20}>Twenty</MenuItem>
                    <MenuItem value={30}>Thirty</MenuItem>
                </Select>
            </FormControl>
            <Button fullWidth sx={{py:" .9rem"}} type="submit" variant="contained">Create a Deal</Button>
        </Box>
    )
}

export default CreateDealFrom