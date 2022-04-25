package ru.loudbar.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.loudbar.dao.StringRepo;

@RunWith(SpringRunner.class)
public class StringServiceTest {

    @TestConfiguration
    static class StringServiceTestContextConfiguration {

        @MockBean private StringRepo stringRepo;

        @Bean
        public StringService stringService() {
            return new StringService(stringRepo);
        }
    }

    @Autowired
    private StringService stringService;

    @Test
    public void encode() {
        Assert.assertEquals("", stringService.encode("qwerty"));
    }

    @Test
    public void decode() {

        Assert.assertNull( stringService.decode("b"));
    }
}