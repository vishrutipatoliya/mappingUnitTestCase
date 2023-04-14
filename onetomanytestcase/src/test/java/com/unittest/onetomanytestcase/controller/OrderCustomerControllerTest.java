package com.unittest.onetomanytestcase.controller;

import com.unittest.onetomanytestcase.dto.*;
import com.unittest.onetomanytestcase.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCustomerControllerTest extends AbstractOrderControllerTest {
    @Autowired
    @InjectMocks
    OrderCustomerController orderController;

    @Test
    void addOrder() {

        List<OrderItemReqDto> orderItem = new ArrayList<>();
        orderItem.add(new OrderItemReqDto("product"));
        OrderCustomerReqDto orderCustomerReqDto = new OrderCustomerReqDto("aaa", orderItem);
        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(1L, "product"));
        OrderCustomerResDto orderCustomerResDto = new OrderCustomerResDto(1L, "aaa", orderItemres);
        ResponseEntity<ApiResponse> custom = new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "add successfully", orderCustomerResDto), HttpStatus.OK);
        when(orderService.addOrder(Mockito.any(OrderCustomerReqDto.class))).thenReturn(orderCustomerResDto);
        ResponseEntity<ApiResponse> expect = orderController.addOrder(orderCustomerReqDto);
        verify(orderService, times(1)).addOrder(orderCustomerReqDto);
        assertEquals(expect, custom);
    }

    @Test
    void getAll() {
        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(1L, "product"));
        List<OrderCustomerOrderItemResponseDto> responseDto = new ArrayList<>();
        responseDto.add(new OrderCustomerOrderItemResponseDto(1L, "aaa", orderItemres));
        ResponseEntity<ApiResponse> custom = new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "get successfully", responseDto), HttpStatus.OK);
        when(orderService.getAll()).thenReturn(responseDto);
        ResponseEntity<ApiResponse> expect = orderController.getAll();
        verify(orderService, times(1)).getAll();
        assertEquals(expect, custom);

    }

    @Test
    void getOrderById() {
        Long id = 2L;
        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(id, "product"));
        OrderCustomerOrderItemResponseDto responseDto = new OrderCustomerOrderItemResponseDto(id, "aaa", orderItemres);
        ResponseEntity<ApiResponse> custome = new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "get using id", responseDto), HttpStatus.OK);
        when(orderService.getOrderById(id)).thenReturn(responseDto);
        ResponseEntity<ApiResponse> expect = orderController.getOrderById(id);
        verify(orderService, times(1)).getOrderById(id);
        assertEquals(expect, custome);

    }

    @Test
    void deleteOrder() {
        Long id = 2L;
        ResponseEntity<ApiResponse> custome = new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "delete successfully", Collections.emptyMap()), HttpStatus.OK);
        ResponseEntity<ApiResponse> expect = orderController.deleteOrder(id);
        verify(orderService, times(1)).delete(id);
    }

    @Test
    void updateorder() {
        Long id = 2L;
        List<OrderItemReqDto> orderItemreq = new ArrayList<>();
        orderItemreq.add(new OrderItemReqDto("product"));
        OrderCustomerOrderItemRequestDto RequestDto = new OrderCustomerOrderItemRequestDto("aaa", orderItemreq);

        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(id, "product"));
        OrderCustomerOrderItemResponseDto responseDto = new OrderCustomerOrderItemResponseDto(id, "aaa", orderItemres);
        ResponseEntity<ApiResponse> custom = new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "update sucessfully", responseDto), HttpStatus.OK);
        when(orderService.updateOrder(RequestDto, id)).thenReturn(responseDto);
        ResponseEntity<ApiResponse> expect = orderController.updateorder(RequestDto, id);
        verify(orderService, times(1)).updateOrder(RequestDto, id);
        assertEquals(expect, custom);
    }
}