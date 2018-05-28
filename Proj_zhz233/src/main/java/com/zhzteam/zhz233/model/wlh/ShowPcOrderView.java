package com.zhzteam.zhz233.model.wlh;

import java.time.LocalDateTime;

/**
 * @描述
 * @参数 $params
 * @返回值 $return
 * @创建人 wenliheng
 * @创建时间 2018/5/27
 */
public class ShowPcOrderView {

    private String goods_theme;//商品主题
    private String order_no;
    private LocalDateTime order_end_time;//订单结束时间
    private String game_name;
    private String goods_area;//大区
    private String goods_server;//服务器

    public String getGoods_theme() {
        return goods_theme;
    }

    public void setGoods_theme(String goods_theme) {
        this.goods_theme = goods_theme;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public LocalDateTime getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(LocalDateTime order_end_time) {
        this.order_end_time = order_end_time;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGoods_area() {
        return goods_area;
    }

    public void setGoods_area(String goods_area) {
        this.goods_area = goods_area;
    }

    public String getGoods_server() {
        return goods_server;
    }

    public void setGoods_server(String goods_server) {
        this.goods_server = goods_server;
    }
}
