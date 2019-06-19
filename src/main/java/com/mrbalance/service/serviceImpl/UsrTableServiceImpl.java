package com.mrbalance.service.serviceImpl;


import com.mrbalance.dao.UsrTableDao;
import com.mrbalance.model.UsrTable;
import com.mrbalance.model.UsrTableExample;
import com.mrbalance.service.UsrTableService;
import com.mrbalance.util.MapBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 杜云章
 * @description: 用户业务处理
 * @Date: 2019-06-11 11:10
 */
@Service("usrTableService")
public class UsrTableServiceImpl implements UsrTableService {

    private static Logger log= LoggerFactory.getLogger(UsrTableServiceImpl.class);

    @Autowired
    private UsrTableDao usrTableDao;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @Author: 杜云章
     * @description: 校验登录结果
     * @parm: map[account,password]
     * @return: boolean true 表示校验成功 false表示校验失败
     * @Date: 2019/6/11 - 9:16
     */
    private boolean checkLogin(Map<String, Object> map){
        String account = (String) map.get("account");
        if(account == null || "".equals(account)){
            map.put("errorMessage","账号为空");
            log.error("账号为空");
            return false;
        }
        String password = (String) map.get("password");
        if(password == null || "".equals(password)){
            map.put("errorMessage","密码为空");
            log.error("密码为空");
            return false;
        }
        UsrTableExample ux = new UsrTableExample();
        ux.createCriteria().andUsrAccountEqualTo(account);
        List<UsrTable> usrTables = usrTableDao.selectByExample(ux);
        if(usrTables.isEmpty() || null == usrTables){
            map.put("errorMessage","账号不存在");
            log.error("账号不存在");
            return false;
        }
        if(!(password.equals(usrTables.get(0).getUsrPassword()))){
            map.put("errorMessage","密码错误");
            log.error("密码错误");
            return false;
        }
        map.clear();
        //深拷贝，用 = 会使得此处成为浅拷贝对map的操作不会在函数回调的时候不生效
        map.putAll(MapBeanUtil.objToMap(usrTables.get(0)));
        map.put("errorMessage","0");
        return true;
    }

    @Override
    public String islogin(Map<String, Object> map) {
        if(checkLogin(map)){
            redisTemplate.opsForHash().putAll("loginInfo", map);
            log.info("成功登录");
        }
        return (String) map.get("errorMessage");
    }

    @Override
    public UsrTable getCurrentUsr() {
        return null;
    }
}
