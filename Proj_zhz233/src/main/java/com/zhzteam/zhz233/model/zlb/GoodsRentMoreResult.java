package com.zhzteam.zhz233.model.zlb;

import java.io.Serializable;

public class GoodsRentMoreResult implements Serializable {
    private String goods_no;
    private String goods_lol_role;
    private String goods_lol_area;
    private String goods_lol_server;
    private Integer goods_lol_grade;
    private Integer goods_lol_rank;
    private Integer goods_lol_case;

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getGoods_lol_role() {
        return goods_lol_role;
    }

    public void setGoods_lol_role(String goods_lol_role) {
        this.goods_lol_role = goods_lol_role;
    }

    public String getGoods_lol_area() {
        return goods_lol_area;
    }

    public void setGoods_lol_area(String goods_lol_area) {
        this.goods_lol_area = goods_lol_area;
    }

    public String getGoods_lol_server() {
        return goods_lol_server;
    }

    public void setGoods_lol_server(String goods_lol_server) {
        this.goods_lol_server = goods_lol_server;
    }

    public Integer getGoods_lol_grade() {
        return goods_lol_grade;
    }

    public void setGoods_lol_grade(Integer goods_lol_grade) {
        this.goods_lol_grade = goods_lol_grade;
    }

    public Integer getGoods_lol_rank() {
        return goods_lol_rank;
    }

    public void setGoods_lol_rank(Integer goods_lol_rank) {
        this.goods_lol_rank = goods_lol_rank;
    }

    public Integer getGoods_lol_case() {
        return goods_lol_case;
    }

    public void setGoods_lol_case(Integer goods_lol_case) {
        this.goods_lol_case = goods_lol_case;
    }
}
