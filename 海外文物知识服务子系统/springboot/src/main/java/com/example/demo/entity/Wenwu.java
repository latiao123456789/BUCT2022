package com.example.demo.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@TableName("wenwu")
@Data

public class Wenwu {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String name;
    private String museum;
    private String description;
    private Date date;
}