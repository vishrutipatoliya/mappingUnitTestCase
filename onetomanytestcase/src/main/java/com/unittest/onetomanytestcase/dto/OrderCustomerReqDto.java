package com.unittest.onetomanytestcase.dto;

import com.unittest.onetomanytestcase.entity.OrderItem;
import lombok.*;

import java.util.List;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCustomerReqDto {
    private String customerName;
    private List<OrderItemReqDto> orderItem;
}
