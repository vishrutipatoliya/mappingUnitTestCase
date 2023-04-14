package com.unittest.onetomanytestcase.dto;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCustomerOrderItemResponseDto {
    private Long id;
    private String customerName;

    private List<OrderItemResDto> orderItem;


}
