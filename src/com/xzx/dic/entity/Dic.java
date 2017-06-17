package com.xzx.dic.entity;

/**
 * Created by xzx14 on 2017/6/13.
 */
public class Dic {
    private int dic_id;
    private int order_id;
    private int dic_type_id;
    private String dic_name;
    private boolean def_value;


    public int getDic_id() {
        return dic_id;
    }

    public void setDic_id(int dic_id) {
        this.dic_id = dic_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDic_type_id() {
        return dic_type_id;
    }

    public void setDic_type_id(int dic_type_id) {
        this.dic_type_id = dic_type_id;
    }

    public String getDic_name() {
        return dic_name;
    }

    public void setDic_name(String dic_name) {
        this.dic_name = dic_name;
    }

    public boolean isDef_value() {
        return def_value;
    }

    public void setDef_value(boolean def_value) {
        this.def_value = def_value;
    }


}
