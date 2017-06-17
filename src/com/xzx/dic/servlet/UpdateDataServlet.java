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
public class UpdateDataServlet extends HttpServlet {

    public UpdateDataServlet(){
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
        String str_def_value = req.getParameter("update_def_value");
        String str_dic_id = req.getParameter("update_dic_id");
        int dic_id = Integer.valueOf(str_dic_id);
        boolean def_value = Boolean.valueOf(str_def_value);
        String dic_name = req.getParameter("update_dic_name");
        String str_dic_type_id = req.getParameter("update_dic_type");
        int dic_type_id = Integer.valueOf(str_dic_type_id);
        String str_order_id = req.getParameter("update_order_id");
        int order_id = Integer.valueOf(str_order_id);
        System.out.print(dic_id+"\t"+dic_name+"\t"+dic_type_id+"\t"+def_value +"\t"+order_id);

        //新建字典对象，加入参数
        Dic dic = new Dic();
        dic.setDic_id(dic_id);
        dic.setOrder_id(order_id);
        dic.setDic_type_id(dic_type_id);
        dic.setDic_name(dic_name);
        dic.setDef_value(def_value);

        DicData dicData = new DicData();
        boolean updateSuccess = dicData.updateDic(dic);

        out.println(updateSuccess);
    }
}
