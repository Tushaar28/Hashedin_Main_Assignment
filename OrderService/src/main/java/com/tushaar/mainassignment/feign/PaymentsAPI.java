package com.tushaar.mainassignment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tushaar.mainassignment.dtos.CreatePaymentDTO;
import com.tushaar.mainassignment.dtos.PaymentResponse;

@FeignClient("PAYMENT-SERVICE")
public interface PaymentsAPI {

	@PostMapping("/payments")
	public PaymentResponse createPayment(@RequestBody CreatePaymentDTO createPayment);
	
}
