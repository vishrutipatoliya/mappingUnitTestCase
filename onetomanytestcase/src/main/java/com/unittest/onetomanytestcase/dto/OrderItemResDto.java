package com.unittest.onetomanytestcase.dto;

import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemResDto {
    private  Long id;
    private  String productName;
}
