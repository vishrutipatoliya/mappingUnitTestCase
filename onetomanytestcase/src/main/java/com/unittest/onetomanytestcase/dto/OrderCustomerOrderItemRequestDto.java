package com.unittest.onetomanytestcase.dto;

import lombok.*;

import java.util.List;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCustomerOrderItemRequestDto {
    String customerName;

   private List<OrderItemReqDto> product;
}
