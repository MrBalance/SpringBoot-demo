package com.mrbalance.service;

import com.mrbalance.model.UsrTable;

import java.util.Map;

/**
 * @author: 杜云章
 * @description: 用户业务处理接口
 * @Date: 2019-06-11 9:07
 */
public interface UsrTableService {

     

     /**
      * @Author: 杜云章
      * @description: 处理前台信息，成功即缓存用户信息
      * @parm: map[account,password]
      * @return: String 返回异常文字描述，0表示正常
      * @Date: 2019/6/11 - 9:23
      */
    public String islogin(Map<String, Object> map);
    
     /**
      * @Author: 杜云章
      * @description:
      * @parm: 
      * @return: UsrTable
      * @Date: 2019/6/13 - 16:40
      */
    public UsrTable getCurrentUsr();
}
