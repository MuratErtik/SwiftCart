import React from "react";
import TransactionTable from "./TransactionTable.tsx";

const Transaction = () =>{
    return(
        <div>
            <h1 className='font-bold mb-5 text-xl'>All Transaction</h1>
            <TransactionTable/>
        </div>
    )
}

export default Transaction