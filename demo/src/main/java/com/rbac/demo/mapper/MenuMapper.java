package com.rbac.demo.mapper;

import com.rbac.demo.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MenuMapper {

    // 根据用户 ID 获取菜单列表
    List<Menu> getMenusByUserId(@Param("userId") Integer userId);

    // 其他方法
}
