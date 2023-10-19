package com.CliPg;

import com.CliPg.Service.GoodsService;
import com.CliPg.Service.OrderService;
import com.CliPg.pojo.Goods;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class OrderManagementTest {
    public static void main(String[] args) throws IOException, ParseException {
        //GoodsCURD
        GoodsService goodsService = new GoodsService();
        //正常范例 增
        goodsService.goodsAdd("ZK6",4000,50,9);
        //Id已存在
        //goodsService.goodsAdd("ZK6",4000,50,8);
        //num非正
        //goodsService.goodsAdd("ZK6",4000,-1,10);
        //price非正
        //goodsService.goodsAdd("ZK6",-4000,1,10);
        //name为空
        //goodsService.goodsAdd("",4000,5,11);

        //正常范例 删
        //goodsService.goodsDeleteById(10);
        //批量删除
        //int [] goodsIds = {1,2};
        //goodsService.goodsDeleteInBatches(goodsIds);
        //id不存在
        //goodsService.goodsDeleteById(11);
        //goodsService.goodsDeleteById(0);
        //goodsService.goodsDeleteById(-1);
        //删除的商品已存在在订单中，会取消相应订单
        //goodsService.goodsDeleteById(2);

        //正常范例 改
        goodsService.goodsUpdateById("ZK6",5000,5,9);
        //id不存在
        //goodsService.goodsUpdateById("ZK6",5000,5,10);
        //price非正
        //goodsService.goodsUpdateById("ZK6",5000,5,10);
        //name为空
        //goodsService.goodsUpdateById("",5000,5,9);

        //正常范例 查
        goodsService.goodsSelectById(1);
        goodsService.goodsSelectByName("PG1");
        //id不存在
        //goodsService.goodsSelectById(-1);


        //OrderCURD
        OrderService orderService = new OrderService();
        //增
        orderService.orderAdd(8,"ZK4",2);
        //删
        //orderService.orderDeleteById(5);
        //批量删除
        //int [] orderIds = {1,2}
        //orderService.orderDeleteInBatches(orderIds);
        //改
        orderService.orderUpdate(4,"PG3",5,"2023-10-16 13:50:56");
        //查
        orderService.orderSelectById(4);


    }
}
