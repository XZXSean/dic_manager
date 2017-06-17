package com.xzx.dic.base;

import com.xzx.dic.entity.Dic;

import java.util.ArrayList;

/**
 * Created by xzx14 on 2017/6/14.
 */
public class test {

    public static void main(String args[]){
        DicData da = new DicData();
        ArrayList<Dic> d = new ArrayList<Dic>();
        d = da.getAllData();
        da.showData(d);
    }
}
