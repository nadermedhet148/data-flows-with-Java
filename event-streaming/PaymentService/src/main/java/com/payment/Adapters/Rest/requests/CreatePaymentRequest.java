package com.payment.Adapters.Rest.requests;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePaymentRequest {

    private String userId;

    private Float amount;

    private String type;

}