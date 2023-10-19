package com.CliPg.Service;

import com.CliPg.mapper.GoodsMapper;
import com.CliPg.mapper.OrderMapper;
import com.CliPg.pojo.Goods;
import com.CliPg.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsService {
    //1.获取sqlSessionFactory
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    //2.获取sqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    //3.获取Mapper接口的代理对象
    GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    public GoodsService() throws IOException {
    }


    //增加商品
    public void goodsAdd(String goodsName,int goodsPrice,int goodsNum,int goodsId){
        boolean flag = true;
        for (int i = 1; i <= goodsIdMax(); i++) {
            Goods goods = goodsMapper.selectById(i);
            if( goods.getGoodsId() == goodsId){
                flag = false;
                System.out.println(i + "号商品已存在");
            }
        }
        if(goodsNum <=0 ){
            flag = false;
            System.out.println("商品数量需为正数");
        }
        if (goodsPrice <= 0 ){
            flag = false;
            System.out.println("商品价格需为正数");
        }
        if (goodsName.equals("") || goodsName == null){
            flag = false;
            System.out.println("商品名不能为空");
        }
        if (flag){
            Goods goods = new Goods();
            goods.setGoodsName(goodsName);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsNum(goodsNum);
            goods.setGoodsId(goodsId);
            goodsMapper.add(goods);
            sqlSession.commit();
            //sqlSession.close();
        }
    }

    //删除商品（按照id）
    public void goodsDeleteById(int goodsId){
        if (goodsId > goodsIdMax() || goodsId <=0){
            System.out.println("商品不存在");
        }
        Goods goods1 = goodsMapper.selectById(goodsId);
        for (int j = 1; j <= orderIdMax(); j++){
            Order order = orderMapper.selectByid(j);
            if (goods1.getGoodsName().equals(order.getGoodsName())){
                orderMapper.deleteById(j);
                System.out.println(j + "号订单取消");
            }
        }
        goodsMapper.deleteById(goodsId);
        sqlSession.commit();
        //sqlSession.close();
    }

    //批量删除
    //判断订单内是否存在该商品，若存在，则取消该订单
    public void goodsDeleteInBatches(int [] goodsIds){
        for (int i = 0; i < goodsIds.length; i++) {
            Goods goods2 = goodsMapper.selectById(goodsIds[i]);
            for (int j = 1; j <= orderIdMax(); j++){
                Order order = orderMapper.selectByid(j);
                if (goods2.getGoodsName().equals(order.getGoodsName())){
                    orderMapper.deleteById(j);
                    System.out.println(j + "号订单取消");
                }
            }
        }
        goodsMapper.deleteByIds(goodsIds);
        sqlSession.commit();
        //sqlSession.close();
    }

    //修改商品（通过id）
    public void goodsUpdateById(String goodsName,int goodsPrice,int goodsNum,int goodsId){
        boolean flag = true;
        if (goodsId > goodsIdMax() || goodsId <=0){
            flag = false;
            System.out.println("商品不存在");
        }
        if (goodsName.equals("") || goodsName == null){
            flag = false;
            System.out.println("商品名不能为空");
        }
        if (flag){
            Goods goods = new Goods();
            goods.setGoodsName(goodsName);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsNum(goodsNum);
            goods.setGoodsId(goodsId);
            goodsMapper.updateDynamic(goods);
            sqlSession.commit();
            //sqlSession.close();
        }
    }

    //查询商品（通过id）
    public void goodsSelectById(int goodsId){
        boolean flag = true;
        if (goodsId > goodsIdMax() || goodsId <=0){
            flag = false;
            System.out.println("商品不存在");
        }
        if (flag){
            Goods goods = goodsMapper.selectById(goodsId);
            System.out.println(goods);
            //sqlSession.close();
        }
    }

    //查询商品（通过名字）
    public void goodsSelectByName(String goodsName){
        Goods goods = goodsMapper.selectByName(goodsName);
        System.out.println(goods);
        //sqlSession.close();
    }

    //按照价格升序查询
    public void goodsSelectAllPriceASC(){
        List<Goods> goods = goodsMapper.selectAllPriceASC();
        System.out.println(goods);
        //sqlSession.close();
    }

    public void goodsSelectAllPriceDESC(){
        List<Goods> goods = goodsMapper.selectAllPriceASC();
        System.out.println(goods);
        //sqlSession.close();
    }

    public int goodsIdMax(){
        List<Goods> goods = goodsMapper.selectAllIdDESC();
        return goods.get(0).getGoodsId();
    }

    public int orderIdMax(){
        List<Order> orders = orderMapper.selectAllIdDESC();
        return orders.get(0).getOrderId();
    }


}
