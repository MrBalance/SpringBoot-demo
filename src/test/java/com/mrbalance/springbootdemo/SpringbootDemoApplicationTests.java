package com.mrbalance.springbootdemo;

import com.mrbalance.dao.UsrTableDao;
import com.mrbalance.model.UsrTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {
    @Autowired
    UsrTableDao usrTableDao;

    @Test
    public void mybatisTest() {
        UsrTable usrTable = usrTableDao.selectByPrimaryKey(1);
        System.out.println(usrTable);
    }

}
