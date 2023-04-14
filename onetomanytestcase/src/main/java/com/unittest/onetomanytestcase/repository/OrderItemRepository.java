package com.unittest.onetomanytestcase.repository;

import com.unittest.onetomanytestcase.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem,Long> {

}
