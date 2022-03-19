package com.example.tiwen.mapper;

import com.example.tiwen.entity.AppUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xujiayin
 * @since 2022-03-17
 */
@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {
    AppUser queryUserByID(String userID);

    List<AppUser> getUser();
}
