package com.example.user.mapper;

import com.example.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int addUser(Map<Object, Object> user);

    User loginUser(Map<Object, Object> param);
}
