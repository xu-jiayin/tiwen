package com.example.tiwen.controller;


import com.example.tiwen.entity.AppUser;
import com.example.tiwen.response.ResponseResult;
import com.example.tiwen.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xujiayin
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/tiwen")
@CrossOrigin
public class AppUserController {
    @Autowired
    private IAppUserService userService;


    @PostMapping("/addUser")
    @ResponseBody
    public ResponseResult addUser(@RequestBody AppUser user, HttpServletRequest request) {
        return userService.addUser(user,request);
    }

    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }
}
