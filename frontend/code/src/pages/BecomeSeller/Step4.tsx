import { TextField } from "@mui/material";
import React from "react";

interface BecomeSellerFormStep2Props {
    formik: any;
}
const Step4: React.FC<BecomeSellerFormStep2Props> = ({ formik }) => {
    return (
        <div className="space-y-5">
            <TextField fullWidth name="businessDetails.businessName" label='Business Name' value={formik.values.businessDetails?.businessName} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.businessDetails?.businessName && Boolean(formik.errors.businessDetails?.businessName)}
                helperText={formik.touched.businessDetails?.businessName && formik.errors.businessDetails?.businessName}
            />
            <TextField fullWidth name="businessDetails.SellerName" label='Seller Name' value={formik.values.businessDetails?.SellerName} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.businessDetails?.SellerName && Boolean(formik.errors.businessDetails?.SellerName)}
                helperText={formik.touched.businessDetails?.SellerName && formik.errors.businessDetails?.SellerName}
            />
            <TextField fullWidth name="businessDetails.email" label='Email' value={formik.values.bankDetails.businessDetails?.email} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.businessDetails?.email && Boolean(formik.errors.businessDetails?.email)}
                helperText={formik.touched.businessDetails?.email && formik.errors.businessDetails?.email}
            />
            <TextField fullWidth name="businessDetails.password" label='Password' value={formik.values.bankDetails.businessDetails?.password} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.businessDetails?.password && Boolean(formik.errors.businessDetails?.password)}
                helperText={formik.touched.businessDetails?.password && formik.errors.businessDetails?.password}
            />
        </div>
    )
}

export default Step4