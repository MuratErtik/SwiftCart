import { Box, TextField } from "@mui/material";
import React from "react";

const Step1 = ({ formik }: any) => {
    return (
        <Box>
            <p className='text-xl font-bold text-center pb-9'>Contact Details</p>
            <div className='space-y-9'>
                <TextField fullWidth name="mobile" label='Mobile' value={formik.values.mobile} onChange={formik.handleChange}
                    error={formik.touched.mobile && Boolean(formik.errors.mobile)}
                    helperText={formik.touched.mobile && formik.errors.mobile}
                />
            </div>
        </Box>
    )
}

export default Step1