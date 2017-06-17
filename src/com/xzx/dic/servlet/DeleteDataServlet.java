package com.xzx.dic.servlet;

import com.xzx.dic.base.DicData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xzx14 on 2017/6/16.
 */
public class DeleteDataServlet extends HttpServlet {

//    public static void main(String args[]){
//        DicData dicData = new DicData();
//        String str = "12";
//        int id = Integer.valueOf(str);
//        boolean bool = dicData.deleteDic(id);
//        System.out.print(bool);
//    }

    public DeleteDataServlet(){
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
        req.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        System.out.println("执行"+getClass());

        String id = req.getParameter("id");
        System.out.print("传入id="+id);
        int dic_id = Integer.valueOf(id);
        DicData dicData = new DicData();
        boolean flag = dicData.deleteDic(dic_id);
        out.println(flag);
    }
}
