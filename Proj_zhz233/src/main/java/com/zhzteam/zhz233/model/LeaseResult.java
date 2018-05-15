package com.zhzteam.zhz233.model;

import java.io.Serializable;
import java.util.Date;

public class LeaseResult implements Serializable {
    private String order_no;//订单编号 03
    private String goods_no;//商品编号
    private String account;//商家编号
    private String buyer;//购买者编号
    private Double order_amount;//总价格
    private Date order_start_time;//订单开始时间
    private Date order_end_time;//订单结束时间
    private Integer order_state;//订单状态（0/等待付款 1/订单失败 2/完成订单 3/订单失效 4/订单进行中）
    private Date create_time;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Double order_amount) {
        this.order_amount = order_amount;
    }

    public Date getOrder_start_time() {
        return order_start_time;
    }

    public void setOrder_start_time(Date order_start_time) {
        this.order_start_time = order_start_time;
    }

    public Date getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(Date order_end_time) {
        this.order_end_time = order_end_time;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
