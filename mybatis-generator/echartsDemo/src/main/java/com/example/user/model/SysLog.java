package com.example.user.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王能顺
 * @Date: 2019年10月23日11:10:57
 *
 * 用户日志实体类
 */
@Data
public class SysLog implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作
     */
    private String operation;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作时间
     */
    private Date createDate;
}
