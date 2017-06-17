package com.xzx.dic.servlet;

import com.xzx.dic.base.DicData;
import com.xzx.dic.base.JsonDic;
import com.xzx.dic.entity.Dic;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xzx14 on 2017/6/14.
 */
public class DataServlet extends HttpServlet {

    ArrayList<Dic> list ;
    public static ArrayList<Dic> getDic(){
        ArrayList<Dic> li = new ArrayList<Dic>();
        DicData dda = new DicData();
        //System.out.print("******获取数据前***");
        li = dda.getAllData();
        //System.out.print("******获取数据后***");
        return li;
    }

    public DataServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("get");
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        //System.out.println("执行到了");
        //获取数据
        list = getDic();
        String  str = JsonDic.getArrayListJson(list);
        String name = req.getParameter("name");
        req.setCharacterEncoding("utf-8");
       // resp.setContentType("text/html;charset=utf-8");
        //String jsonStr = "{'info':'"+name+"'}";
        out.println(str);
    }

}
