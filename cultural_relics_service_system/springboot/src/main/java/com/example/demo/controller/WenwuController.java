package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Wenwu;
import com.example.demo.mapper.WenwuMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wenwu")
public class WenwuController {

    @Resource
    WenwuMapper WenwuMapper;

    //    从后台传入前端
    @GetMapping
//    findPage参数默认必填，不然404
    public Result<?> findPage(@RequestParam(defaultValue ="1") Integer pageNum,
                              @RequestParam(defaultValue ="10") Integer pageSize,
                              @RequestParam(defaultValue ="") String search,
                              @RequestParam Integer num){
//        实现用户名模糊查询并返回查询结果
        LambdaQueryWrapper<Wenwu> wrapper=Wrappers.<Wenwu>lambdaQuery();
//        search非空，避免username=null查不出来
        if(num==1){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(Wenwu::getName,search);
            }
            Page<Wenwu> WenwuPage=WenwuMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(WenwuPage);
        }
        else if(num==2){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(Wenwu::getMuseum,search);
            }
            Page<Wenwu> WenwuPage=WenwuMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(WenwuPage);
        }
        else if(num==3){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(Wenwu::getDate,search);
            }
            Page<Wenwu> WenwuPage=WenwuMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(WenwuPage);
        }
        else if(num==4){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(Wenwu::getWebsite,search);
            }
            Page<Wenwu> WenwuPage=WenwuMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(WenwuPage);
        }
        return Result.success();
    }

}