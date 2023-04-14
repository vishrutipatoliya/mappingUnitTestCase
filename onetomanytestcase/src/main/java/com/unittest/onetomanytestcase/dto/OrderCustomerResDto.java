package com.unittest.onetomanytestcase.dto;

import com.unittest.onetomanytestcase.entity.OrderItem;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCustomerResDto {

    private Long id;
    private String customerName;
    private List<OrderItemResDto> orderItem;
}
