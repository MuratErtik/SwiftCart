package com.project.ecommerce.entities;

import lombok.Data;

@Data
public class BankDetails {

    private String accountNumber;

    private String accountHolderName;
    
    //private String bankName;
    
    private String ibanCode;
}
