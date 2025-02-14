import { Button, Step, StepLabel, Stepper } from "@mui/material";
import React, { useState } from "react";
import Step1 from "./Step1.tsx";
import { useFormik } from "formik";
import Step2 from "./Step2.tsx";
import Step3 from "./Step3.tsx";
import Step4 from "./Step4.tsx";

const steps = [
    "Tax Details & Mobile",
    "Pickup Address",
    "Bank Details",
    "Supplier Details"
];
const menu = [
    { name: "step1", path: "/become-seller/step1" }
   
]

const SellerAccountForm = () => {
    const [activestep, setActivestep] = useState(1);
    const handleStep = (value: number) => () => {

        (activestep < steps.length - 1 || activestep > 0 && value == -1) && setActivestep(activestep + value);
        activestep == (steps.length - 1) && handleCreateAccount();

    }
    const handleCreateAccount = () => {
    }
    const formik = useFormik(
        {
            initialValues: {
                mobile: "",
                otp: "",
                pickupAddresses: {
                    name: "",
                    mobile: "",
                    address: "",
                    locality: "",
                    city: ""

                },
                bankDetails: {
                    accountNumber: "",
                    iban: "",
                    accountholderName: ""

                },
                sellerName: "",
                email: "",
                businnesDetails: {
                    businessName: "",
                    businessEmail: "",
                    businessMobile: "",
                    businessAddress: "",
                    logo: "",
                    banner: ""

                },
                password: ""



            },
            onSubmit: (values) => {

            },
        }
    );
    return (
        <div>
            <section>
                <Stepper activeStep={activestep} alternativeLabel>
                    {steps.map((label, index) => (
                        <Step key={label}>
                            <StepLabel>{label}</StepLabel>
                        </Step>
                    ))}
                </Stepper>
            </section>
            <section className="mt-20 space-y-10">
                {activestep == 0 ? <Step1 formik={formik} /> : ""}
                {activestep == 1 ? <Step2 formik={formik} /> : ""}
                {activestep == 2 ? <Step3 formik={formik} /> : ""}
                {activestep == 3 ? <Step4 formik={formik} /> : ""}



                <div className='flex items-center justify-between'>
                    <Button onClick={handleStep(-1)} variant='contained' disabled={activestep == 0}>Back</Button>
                    <Button onClick={handleStep(1)} variant='contained' disabled={activestep == (steps.length)} >{activestep == (steps.length - 1) ? "Create Account" : "Next"}</Button>


                </div>
            </section>

        </div>

    )
}

export default SellerAccountForm