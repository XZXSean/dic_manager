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

/**
 * Created by xzx14 on 2017/6/16.
 */
public class SortDataServlet extends HttpServlet {

    ArrayList<Dic> list ;
    public static ArrayList<Dic> getSortDic(int sta){
        ArrayList<Dic> li = new ArrayList<Dic>();
        DicData dda = new DicData();
        li = dda.getSortData(sta);
        return li;
    }

    public SortDataServlet(){
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


        String name = req.getParameter("sta");
        System.out.println(name);
        int sta = Integer.valueOf(name);
        req.setCharacterEncoding("utf-8");
        //获取数据
        list = getSortDic(sta);
        String  str = JsonDic.getArrayListJson(list);
        // resp.setContentType("text/html;charset=utf-8");
        //String jsonStr = "{'info':'"+name+"'}";
        out.println(str);
    }
}
