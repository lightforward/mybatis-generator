package com.example.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Transactional
    int addUser(Map<Object, Object> user);

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    List<Map> loginUser(Map<Object, Object> param);
}
