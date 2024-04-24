package com.example.Project06.Controller;

import com.example.Project06.Dto.PaymentDto;
import com.example.Project06.Dto.PaymentResponseDto;
import com.example.Project06.Entity.Payment;
import com.example.Project06.Entity.Plan;
import com.example.Project06.Entity.User;
import com.example.Project06.Repository.PaymentRepo;
import com.example.Project06.Repository.PlanRepository;
import com.example.Project06.Repository.UserRepository;
import com.example.Project06.Service.PaymentService;
import com.example.Project06.exception.PageNotFoundException;
import com.example.Project06.exception.PaymentNotFoundException;
import com.example.Project06.exception.UserNotFoundExceptions;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final UserRepository userRepository;

    private final PlanRepository planRepository;

    private final PaymentRepo paymentRepo;

    private final PaymentService paymentService;

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException, ParseException {
        System.out.println(data);
        Integer rupeeAmount = Integer.parseInt(data.get("amount").toString());

        int amt = Integer.parseInt(data.get("amount").toString());

        var client = new RazorpayClient("rzp_test_80O2m3SmaMOrVp", "F9T5oh0tfg95guvDn1lFINDZ");

        JSONObject ob = new JSONObject();
        ob.put("amount", amt * 100);
        ob.put("currency", "INR");
        String transactionId = "txn_" + UUID.randomUUID().toString();
        ob.put("receipt", transactionId);

        Order order = client.Orders.create(ob);
        System.out.println(order);

        Integer userId = Integer.parseInt(data.get("userId").toString());

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundExceptions("User not found", HttpStatus.NOT_FOUND));

        Integer planId = Integer.parseInt(data.get("planId").toString());

        Plan plan = planRepository.findById(planId).orElse(null);

        String planName = plan.getPlan();

        Payment myPayment = new Payment();
        myPayment.setOrderId(order.get("id"));
        myPayment.setReceipt(order.get("receipt"));
        myPayment.setPaymentId(null);
        myPayment.setStatus("created");
        myPayment.setUser(user);
        myPayment.setPlan(plan);
        myPayment.setPlanName(planName);
        myPayment.setDate(order.get("date"));
        String isoDate = data.get("date").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date parsedDate = dateFormat.parse(isoDate);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        myPayment.setDate(timestamp);
        myPayment.setAmount(order.get("amount"));

        myPayment.setAmount(rupeeAmount);

        this.paymentRepo.save(myPayment);

        return order.toString();
    }

    @GetMapping("getAllPayments")
    public ResponseEntity<?> getAllPayments(
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = "10") int pageSize) {

        try {
            List<PaymentDto> allPayments = paymentService.getAllPayments(pageNo, pageSize);
            PaymentResponseDto paymentResponceDto = new PaymentResponseDto("Success");
            paymentResponceDto.setList(allPayments);
            return    ResponseEntity.status(HttpStatus.OK).body(paymentResponceDto);
        } catch (PageNotFoundException e) {
            PaymentResponseDto responseDto = new PaymentResponseDto("Unsuccess");
            responseDto.setException(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }


    @GetMapping("/user/{user_id}")
    public ResponseEntity<PaymentResponseDto> getPaymentsByUserId(@PathVariable Integer user_id

    ) {
        try {
            List<PaymentDto> paymentsByUserId = paymentService.getPaymentsByUserId(user_id);
            PaymentResponseDto paymentResponceDto = new PaymentResponseDto("Success");
            paymentResponceDto.setList(paymentsByUserId);
            return ResponseEntity.status(HttpStatus.OK).body(paymentResponceDto);
        }catch (PaymentNotFoundException e){
            PaymentResponseDto responceDto = new PaymentResponseDto("Unsuccess");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responceDto);
        }
    }
}
