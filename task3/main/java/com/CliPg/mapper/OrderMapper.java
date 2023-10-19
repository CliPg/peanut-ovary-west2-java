package com.CliPg.mapper;

import com.CliPg.pojo.Goods;
import com.CliPg.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    //1.查
    //1.1查看所有
    List<Order> selectAll();
    //1.2查看详情
    Order selectByid(int id);
    //1.3动态条件查询
    List<Order> selectByDynamicCondition(Map map);
    //1.4时间降序
    List<Order> selectAllDateDESC();
    //1.5时间升序
    List<Order> selectAllDateASC();
    //1.6id降序
    List<Order> selectAllIdDESC();

    //2.改
    //动态修改
    void updateDynamic(Order order);

    //3.增
    void add(Order orders);

    //4.删
    //4.1删除指定
    void deleteById(int id);
    //4.2批量删除
    void deleteByIds(@Param("orderIds") int [] ids);
}
