package com.CliPg.pojo;

import com.CliPg.exception.GoodsNameNotFoundException;
import com.CliPg.exception.OrderNumFalseException;
import com.CliPg.exception.OrderPriceFalseException;
import com.CliPg.mapper.GoodsMapper;
import com.CliPg.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Order {
    private int orderId;
    private String goodsName;
    private int orderNum;
    private Date orderTime;
    private int orderPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", goodName='" + goodsName + '\'' +
                ", orderNum=" + orderNum +
                ", orderTime=" + orderTime +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
