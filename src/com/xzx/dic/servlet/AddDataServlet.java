package com.xzx.dic.servlet;

import com.xzx.dic.base.DicData;
import com.xzx.dic.entity.Dic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xzx14 on 2017/6/16.
 */
public class AddDataServlet extends HttpServlet {

    public AddDataServlet(){
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
        //获取进来的参数
        String str_def_value = req.getParameter("add_def_value");
        String str_order_id = req.getParameter("add_order_id");
        int order_id = Integer.valueOf(str_order_id);
        boolean def_value = Boolean.valueOf(str_def_value);
        String dic_name = req.getParameter("add_dic_name");
        String str_dic_type_id = req.getParameter("add_dic_type");
        int dic_type_id = Integer.valueOf(str_dic_type_id);
        System.out.print(order_id+"\t"+dic_name+"\t"+dic_type_id+"\t"+def_value);

        //新建字典对象，加入参数
        Dic dic = new Dic();
        dic.setOrder_id(order_id);
        dic.setDic_type_id(dic_type_id);
        dic.setDic_name(dic_name);
        dic.setDef_value(def_value);

        DicData dicData = new DicData();
        boolean addSuccess = dicData.addDic(dic);


        out.println(addSuccess);
    }
}
