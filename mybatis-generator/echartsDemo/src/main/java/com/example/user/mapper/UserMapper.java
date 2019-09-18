package com.example.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int addUser(Map<Object, Object> user);

    List<Map> loginUser(Map<Object, Object> param);
}
