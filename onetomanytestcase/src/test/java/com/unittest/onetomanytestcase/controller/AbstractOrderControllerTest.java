package com.unittest.onetomanytestcase.controller;


import com.unittest.onetomanytestcase.OnetomanytestcaseApplication;
import com.unittest.onetomanytestcase.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles(value = "test")
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = OnetomanytestcaseApplication.class)
public class AbstractOrderControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @MockBean
    protected OrderServiceImpl orderService;

    @BeforeEach
    public void data() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

}


