package com.p92group.zhlobo;

import com.p92group.zhlobo.models.Balance;
import com.p92group.zhlobo.services.BalanceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BalanceServiceSpringTest {


        @Autowired
        private BalanceService balanceService;

        @Test
        public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
            List<Balance> balance = balanceService.getAll();
            Assert.assertEquals(balance.size(), 0);
        }


}

