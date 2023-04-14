package com.unittest.onetomanytestcase.service;


import com.unittest.onetomanytestcase.OnetomanytestcaseApplication;
import com.unittest.onetomanytestcase.repository.OrderCustomerRepository;
import com.unittest.onetomanytestcase.repository.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles(value = "test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = OnetomanytestcaseApplication.class)
public class AbstractServiceTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;
    @MockBean
    protected OrderItemRepository orderItemRepository;
    @MockBean
    protected OrderCustomerRepository orderCustomerRepository;

    @BeforeEach
    public void data() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

}
