package com.CliPg.mapper;

import com.CliPg.pojo.Brand;
import com.CliPg.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    //1.查
    //1.1查看所有
    List<Goods> selectAll();
    //1.2查看详情
    Goods selectByName(String goodsName);
    Goods selectById(int goodsId);
    //1.3动态条件查询
    List<Goods> selectByDynamicCondition(Map map);
    //1.4价格升序
    List<Goods> selectAllPriceASC();
    //1.5价格降序
    List<Goods> selectAllPriceDESC();
    //1.6id降序
    List<Goods> selectAllIdDESC();

    //2.改
    //动态修改
    void updateDynamic(Goods goods);

    //3.增
    void add(Goods goods);

    //4.删
    //4.1删除指定
    void deleteById(int id);
    //4.2批量删除
    void deleteByIds(@Param("goodsIds") int [] ids);
}
