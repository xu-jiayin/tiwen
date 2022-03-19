package com.example.tiwen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tiwen.entity.AppUser;
import com.example.tiwen.mapper.AppUserMapper;
import com.example.tiwen.response.ResponseResult;
import com.example.tiwen.service.IAppUserService;
import com.example.tiwen.utils.SnowflakeIdWorker;
import com.example.tiwen.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujiayin
 * @since 2022-03-17
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

    @Autowired
    private AppUserMapper userMapper;

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    public ResponseResult addUser(AppUser user, HttpServletRequest request) {
        // 参数非空校验
        if (TextUtils.isEmpty(user.getUserId())) {
            return ResponseResult.FATLED("学号不能为空");
        }
        if (TextUtils.isEmpty(user.getPassWord())) {
            return ResponseResult.FATLED("密码不能为空");
        }
        if (TextUtils.isEmpty(user.getToken())) {
            return ResponseResult.FATLED("Token不能为空");
        }

        HashMap map = new HashMap();
        map.put("userID", user.getUserId());
        List<AppUser> appUser = userMapper.selectByMap(map);
        if (appUser.size() != 0) {
            return ResponseResult.FATLED("请勿重复提交");
        }

        // 密码加密
//        String passWord = Md5Util.string2MD5(user.getPassWord());
        // 初始化数据
        user.setId(idWorker.nextId());
        //写入数据
        int insert = userMapper.insert(user);
        return insert > 0 ? ResponseResult.SUCCESS("账号初始化成功") : ResponseResult.FATLED("系统错误!");
    }

    @Override
    public ResponseResult getUserInfo() {
        List<AppUser> user = userMapper.selectList(null);
        if (user.size() == 0) {
            return ResponseResult.FATLED("没有内容");
        }
        return ResponseResult.SUCCESS("").setData(user);
    }
}
