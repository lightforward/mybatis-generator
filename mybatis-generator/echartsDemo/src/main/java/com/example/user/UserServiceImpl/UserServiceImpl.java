package com.example.user.UserServiceImpl;

import com.example.user.mapper.UserMapper;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(Map<Object, Object> user) {
        return userMapper.addUser(user);
    }

    @Override
    public User loginUser(Map<Object, Object> param) {
        return userMapper.loginUser(param);
    }


}
