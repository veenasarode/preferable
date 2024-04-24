package com.example.Project06.Service;

import com.example.Project06.Dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> getAllPayments(int pageNo, int pageSize);

    List<PaymentDto> getPaymentsByUserId(Integer user_id);
}
