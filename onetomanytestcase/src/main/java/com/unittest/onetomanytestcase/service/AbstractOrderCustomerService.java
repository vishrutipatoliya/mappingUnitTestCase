package com.unittest.onetomanytestcase.service;

import com.unittest.onetomanytestcase.dto.OrderCustomerOrderItemRequestDto;
import com.unittest.onetomanytestcase.dto.OrderCustomerOrderItemResponseDto;
import com.unittest.onetomanytestcase.dto.OrderCustomerReqDto;
import com.unittest.onetomanytestcase.dto.OrderCustomerResDto;

import java.util.List;

public interface AbstractOrderCustomerService {
    OrderCustomerResDto addOrder(OrderCustomerReqDto orderCustomerReqDto);

    List<OrderCustomerOrderItemResponseDto> getAll();

    OrderCustomerOrderItemResponseDto getOrderById(Long id);

    void  delete(Long id);
    OrderCustomerOrderItemResponseDto updateOrder(OrderCustomerOrderItemRequestDto orderCustomerOrderItemRequestDto, Long id);

}
