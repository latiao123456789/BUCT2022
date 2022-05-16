package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController//返回json数据的
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper UserMapper;
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res=UserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(res!=null){
            return Result.error("-1","用户名已被注册");
        }
        UserMapper.insert(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res=UserMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
        if(res==null){
            return Result.error("-1","用户名or密码错误");
        }
        return Result.success(res);
    }

    @PostMapping
    public Result<?> save(@RequestBody User User){
        if(User.getPassword()==null){
            User.setPassword("123456");
        }
        UserMapper.insert(User);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User User){
        UserMapper.updateById(User);
        return Result.success();
    }


    @GetMapping
//    findPage参数默认必填，不然404
    public Result<?> findPage(@RequestParam(defaultValue ="1") Integer pageNum,
                              @RequestParam(defaultValue ="10") Integer pageSize,
                              @RequestParam(defaultValue ="") String search,
                              @RequestParam Integer num){
//        实现用户名模糊查询并返回查询结果
        LambdaQueryWrapper<User> wrapper=Wrappers.<User>lambdaQuery();
//        search非空，避免username=null查不出来
        if(num==1){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(User::getUsername,search);
            }
            Page<User> UserPage=UserMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(UserPage);
        }
        else if(num==2){
            if(StrUtil.isNotBlank(search)){
                wrapper.like(User::getSex,search);
            }
            Page<User> UserPage=UserMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
            return Result.success(UserPage);
        }
        return Result.success();
    }

    @DeleteMapping("/{tid}")
    public Result<?> update(@PathVariable Long tid) {
        UserMapper.deleteById(tid);
        return Result.success();
    }
}

