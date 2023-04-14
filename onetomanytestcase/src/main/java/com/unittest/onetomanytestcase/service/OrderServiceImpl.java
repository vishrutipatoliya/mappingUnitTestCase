package com.unittest.onetomanytestcase.service;

import com.unittest.onetomanytestcase.dto.*;
import com.unittest.onetomanytestcase.entity.OrderCustomer;
import com.unittest.onetomanytestcase.entity.OrderItem;
import com.unittest.onetomanytestcase.repository.OrderCustomerRepository;
import com.unittest.onetomanytestcase.repository.OrderItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements AbstractOrderCustomerService {
    @Autowired
    OrderCustomerRepository orderCustomerRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //add order
    @Override
    public OrderCustomerResDto addOrder(OrderCustomerReqDto orderCustomerReqDto) {

        List<OrderItem> orderItemList = new ArrayList<>();
        List<OrderItemResDto> orderItemListOrderItemResDto = new ArrayList<>();
        for (OrderItemReqDto orderItem1 : orderCustomerReqDto.getOrderItem()) {
            OrderItem orderItem2 = new OrderItem(null, orderItem1.getProductName());
            orderItemRepository.save(orderItem2);
//            orderItemList.add(orderItem2);
            orderItemList.add(orderItem2);
            orderItemListOrderItemResDto.add(modelMapper.map(orderItem2, OrderItemResDto.class));
        }

        OrderCustomer orderCustomer = new OrderCustomer(null, orderCustomerReqDto.getCustomerName(), orderItemList);
        orderCustomerRepository.save(orderCustomer);
//        List<OrderItemResDto> resListitem = new ArrayList<>();

        OrderCustomerResDto response = new OrderCustomerResDto();
        response.setOrderItem(orderItemListOrderItemResDto);
        response.setCustomerName(orderCustomerReqDto.getCustomerName());
        return response;
    }

    // get order all

    @Override
    public List<OrderCustomerOrderItemResponseDto> getAll() {

        List<OrderCustomer> list = orderCustomerRepository.findAll();
        List<OrderCustomerOrderItemResponseDto> response = new ArrayList<>();

        for (OrderCustomer orderCustomer : list) {
            List<OrderItemResDto> itemList = new ArrayList<>();
            for (OrderItem item : orderCustomer.getOrderItem()) {
                itemList.add(modelMapper.map(item, OrderItemResDto.class));
            }

            response.add(new OrderCustomerOrderItemResponseDto(orderCustomer.getId(), orderCustomer.getCustomerName(), itemList));

        }
        return response;
    }

    // get by id
    @Override
    public OrderCustomerOrderItemResponseDto getOrderById(Long id) {
        OrderCustomer orderCustomer = orderCustomerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        List<OrderItemResDto> orderItemResDtos = new ArrayList<>();
        for (OrderItem order : orderCustomer.getOrderItem()) {

            orderItemResDtos.add(modelMapper.map(order, OrderItemResDto.class));
        }
//        List<OrderItemResDto> orderItemResDtos = Collections.singletonList(modelMapper.map(orderItem, OrderItemResDto.class));
        OrderCustomerOrderItemResponseDto response = new OrderCustomerOrderItemResponseDto(orderCustomer.getId(), orderCustomer.getCustomerName(), orderItemResDtos);
        return response;
    }

    //delete order
    @Override
    public void delete(Long id) {
        orderCustomerRepository.deleteById(id);

    }


    @Override
    public OrderCustomerOrderItemResponseDto updateOrder(OrderCustomerOrderItemRequestDto dto, Long id) {
        OrderCustomer orderCustomer = orderCustomerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        orderCustomer.setCustomerName(dto.getCustomerName());

        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderItemReqDto item : dto.getProduct()) {
            orderItemList.add(orderItemRepository.save(modelMapper.map(item, OrderItem.class)));
        }
        orderCustomer.setOrderItem(orderItemList);
        orderCustomerRepository.save(orderCustomer);

        return modelMapper.map(orderCustomer, OrderCustomerOrderItemResponseDto.class);

    }


}
