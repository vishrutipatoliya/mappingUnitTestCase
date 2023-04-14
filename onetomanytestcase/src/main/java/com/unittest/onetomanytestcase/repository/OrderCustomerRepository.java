package com.unittest.onetomanytestcase.repository;

import com.unittest.onetomanytestcase.entity.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomer,Long> {


}
