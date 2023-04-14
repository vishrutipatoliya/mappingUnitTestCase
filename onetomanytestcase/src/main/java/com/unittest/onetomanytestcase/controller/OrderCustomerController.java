package com.unittest.onetomanytestcase.controller;

import com.unittest.onetomanytestcase.dto.*;
import com.unittest.onetomanytestcase.response.ApiResponse;
import com.unittest.onetomanytestcase.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class OrderCustomerController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderCustomerReqDto orderCustomerReqDto) {
        OrderCustomerResDto orderCustomerResDto = orderService.addOrder(orderCustomerReqDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "add successfully", orderCustomerResDto), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        var result = orderService.getAll();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "get successfully", result), HttpStatus.OK);
    }

    @GetMapping("order/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable("id") Long id) {
        OrderCustomerOrderItemResponseDto result = orderService.getOrderById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "get using id", result), HttpStatus.OK);
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "delete successfully", Collections.emptyMap()), HttpStatus.OK);
    }

    @PutMapping("order/{id}")
    public ResponseEntity<ApiResponse> updateorder(@RequestBody OrderCustomerOrderItemRequestDto orderCustomerOrderItemRequestDto, @PathVariable("id") Long id) {
        OrderCustomerOrderItemResponseDto result = orderService.updateOrder(orderCustomerOrderItemRequestDto, id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "update sucessfully", result), HttpStatus.OK);

    }
}
