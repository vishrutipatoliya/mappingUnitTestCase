package com.unittest.onetomanytestcase.service;

import com.unittest.onetomanytestcase.dto.*;
import com.unittest.onetomanytestcase.entity.OrderCustomer;
import com.unittest.onetomanytestcase.entity.OrderItem;
import com.unittest.onetomanytestcase.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest extends AbstractServiceTest {
    @Autowired
    @InjectMocks
    OrderServiceImpl orderService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void addOrder() {
        List<OrderItemReqDto> orderItem = new ArrayList<>();
        orderItem.add(new OrderItemReqDto("product"));
        OrderCustomerReqDto orderCustomerReqDto = new OrderCustomerReqDto("aaa", orderItem);

        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(null, "product"));
        OrderCustomerResDto orderCustomerResDto = new OrderCustomerResDto(null, "aaa", orderItemres);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(null, "product"));

        OrderItem item = new OrderItem(null, "product");
        OrderCustomer orderCustomer = new OrderCustomer(null, "aaa", orderItemList);
        when(orderCustomerRepository.save(Mockito.any(OrderCustomer.class))).thenReturn(orderCustomer);
        when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenReturn(item);
        orderService.addOrder(orderCustomerReqDto);
        verify(orderCustomerRepository, times(1)).save(Mockito.any(OrderCustomer.class));
        verify(orderItemRepository, times(1)).save(Mockito.any(OrderItem.class));
    }

    @Test
    void getAll() {
        List<OrderItemResDto> orderItemres = new ArrayList<>();
        orderItemres.add(new OrderItemResDto(1L, "product"));
        List<OrderCustomerOrderItemResponseDto> responseDto = new ArrayList<>();
        responseDto.add(new OrderCustomerOrderItemResponseDto(1L, "aaa", orderItemres));
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(null, "product"));

        OrderItem item = new OrderItem(null, "product");
        List<OrderCustomer> orderCustomerlist = new ArrayList<>();
        orderCustomerlist.add(new OrderCustomer(null, "aaa", orderItemList));
        when(orderCustomerRepository.findAll()).thenReturn(orderCustomerlist);
        orderService.getAll();
        verify(orderCustomerRepository, times(1)).findAll();
    }

    @Test
    void getOrderById() {
        Long id = 1L;
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(id, "product"));
        OrderCustomer orderCustomer = new OrderCustomer(id, "aaa", orderItemList);
        when(orderCustomerRepository.findById(id)).thenReturn(Optional.of(orderCustomer));
        orderService.getOrderById(id);
        verify(orderCustomerRepository, times(1)).findById(id);

    }

    @Test
    void delete() {
        Long id = 1L;
        orderService.delete(id);

    }

    @Test
    void updateOrder() {
        Long id = 1L;
        OrderCustomerOrderItemRequestDto requestDto = new OrderCustomerOrderItemRequestDto();
        requestDto.setCustomerName("abc");

        OrderItemReqDto item1 = new OrderItemReqDto();
        item1.setProductName("Product 1");


        OrderItemReqDto item2 = new OrderItemReqDto();
        item2.setProductName("Product 2");


        List<OrderItemReqDto> orderItemList = new ArrayList<>();
        orderItemList.add(item1);
        orderItemList.add(item2);

        requestDto.setProduct(orderItemList);

        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setId(id);
        orderCustomer.setCustomerName("aaa");

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setProductName("Product 1");



        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(2L);
        orderItem2.setProductName("Product 2");

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        orderCustomer.setOrderItem(orderItems);

        when(orderCustomerRepository.findById(id)).thenReturn(Optional.of(orderCustomer));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem1, orderItem2);

        // Act
        OrderCustomerOrderItemResponseDto responseDto = orderService.updateOrder(requestDto, id);

        // Assert
        assertEquals(requestDto.getCustomerName(), responseDto.getCustomerName());
        assertEquals(requestDto.getProduct().size(), responseDto.getOrderItem().size());
    }
}