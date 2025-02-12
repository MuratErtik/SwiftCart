import { Box, Button, Grid, Grid2, TextField } from "@mui/material";
import React from "react";
import {useFormik} from "formik"
import * as Yup from "yup"

const AddressForm = () => {
    const addressFormSchema = Yup.object().shape({ 
        name:Yup.string().required("Name is required."),
        mobile:Yup.string().required("Mobile number is required.").matches(/^[0-9]\d{9}$/, "Invalid mobile number"),
        address:Yup.string().required("Address is required."),
        city:Yup.string().required("City is required."),
        locality:Yup.string().required("Locality is required."),


    })
    const formik = useFormik({
        initialValues:{
            name:"",
            mobile:"+90 ",
            address:"",
            city:"",
            locality:""

        },
        validationSchema: addressFormSchema,
        onSubmit:(values)=>{
            console.log(values)
        },
    })
    return (
       <Box sx={{ max: "auto"}}>
            <p className='text-xl font-bold text-center pb-5'>Contact Details</p>
            <form onSubmit={formik.handleSubmit}>
                <Grid2 container spacing={8}>
                    <Grid2 size={{xs:12}}>
                        <TextField fullWidth name="name" label='Name' value={formik.values.name} onChange={formik.handleChange} 
                        error={formik.touched.name && Boolean(formik.errors.name)}  
                        helperText= {formik.touched.name && formik.errors.name}
                        />
                    </Grid2>
                    <Grid2 size={{xs:12}}>
                        <TextField fullWidth name="mobile" label='Mobile' value={formik.values.mobile} onChange={formik.handleChange} 
                        error={formik.touched.mobile && Boolean(formik.errors.mobile)}  
                        helperText= {formik.touched.mobile && formik.errors.mobile}
                        />
                    </Grid2>
                    <Grid2 size={{xs:12}}>
                        <TextField fullWidth name="address" label='Address' value={formik.values.address} onChange={formik.handleChange} 
                        error={formik.touched.address && Boolean(formik.errors.address)}  
                        helperText= {formik.touched.address && formik.errors.address}
                        />
                    </Grid2>
                    <Grid2 size={{xs:6}}>
                        <TextField fullWidth name="city" label='City' value={formik.values.city} onChange={formik.handleChange} 
                        error={formik.touched.city && Boolean(formik.errors.city)}  
                        helperText= {formik.touched.city && formik.errors.city}
                        />
                    </Grid2>
                    <Grid2 size={{xs:6}}>
                        <TextField fullWidth name="locality" label='Locality' value={formik.values.locality} onChange={formik.handleChange} 
                        error={formik.touched.locality && Boolean(formik.errors.locality)}  
                        helperText= {formik.touched.locality && formik.errors.locality}
                        />
                    </Grid2>
                    <Grid2 size={{xs:12}}>
                        <Button fullWidth type="submit" variant="contained" sx={{py:"14px"}}>
                            Add Address
                        </Button>
                    </Grid2>

                </Grid2>
            </form>
       </Box>
    )
}


export default AddressForm