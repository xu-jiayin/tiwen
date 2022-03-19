package com.example.tiwen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tiwen.entity.AppUser;
import com.example.tiwen.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiayin
 * @since 2022-03-17
 */
public interface IAppUserService extends IService<AppUser> {

    ResponseResult addUser(AppUser user, HttpServletRequest request);

    ResponseResult getUserInfo();
}
