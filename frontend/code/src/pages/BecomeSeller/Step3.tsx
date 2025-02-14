import { TextField } from "@mui/material";
import React from "react";

interface BecomeSellerFormStep2Props {
    formik: any;
}
const Step3: React.FC<BecomeSellerFormStep2Props> = ({ formik }) => {
    return (
        <div className="space-y-5">
            <TextField fullWidth name="bankDetails.accountNumber" label='Account Number' value={formik.values.bankDetails.accountNumber} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.bankDetails?.accountNumber && Boolean(formik.errors.bankDetails?.accountNumber)}
                helperText={formik.touched.bankDetails?.accountNumber && formik.errors.bankDetails?.accountNumber}
            />
            <TextField fullWidth name="bankDetails.iban" label='IBAN' value={formik.values.bankDetails.iban} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.bankDetails?.iban && Boolean(formik.errors.bankDetails?.iban)}
                helperText={formik.touched.bankDetails?.iban && formik.errors.bankDetails?.iban}
            />
            <TextField fullWidth name="bankDetails.accountHolderName" label='Account Holder Name' value={formik.values.bankDetails.accountHolderName} onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                error={formik.touched.bankDetails?.accountHolderName && Boolean(formik.errors.bankDetails?.accountHolderName)}
                helperText={formik.touched.bankDetails?.accountHolderName && formik.errors.bankDetails?.accountHolderName}
            />
        </div>
    )
}

export default Step3