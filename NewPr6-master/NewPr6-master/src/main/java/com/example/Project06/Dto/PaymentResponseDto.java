package com.example.Project06.Dto;

import lombok.Data;

import java.util.List;
@Data
public class PaymentResponseDto {

    private String message;
    private List<PaymentDto> list;
    private String exception;

    public PaymentResponseDto(String message) {
        this.message = message;
    }
}
