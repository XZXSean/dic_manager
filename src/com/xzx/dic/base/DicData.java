package com.xzx.dic.base;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.xzx.dic.entity.Dic;

/**
 * Created by xzx14 on 2017/6/13.
 */
public class DicData extends BaseData {
      //test
    public static void main(String args[]){
        DicData ba = new DicData();
        Dic d = new Dic();
        d.setDef_value(false);
        d.setDic_name("哈密瓜");
        d.setDic_id(4);
        d.setOrder_id(4);
        d.setDic_type_id(2);
        //删除id 为13
        //ba.deleteDic(15);
        //修改数据
        //ba.updateDic(d);
        //添加数据
        //ba.addDic(d);
        //ArrayList<Dic> li = new ArrayList<Dic>();
        //li = ba.getAllData();
        //ba.showData(li);
        ba.updateDic(d);
    }
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //排序数据
    public ArrayList<Dic> getSortData(int n){
        String str = "";
        switch (n){
            case 0:
                str = " order by dic_id";
                break;
            case 1:
                str = " order by dic_id DESC";
                break;
            case 2:
                str = " order by order_id";
                break;
            case 3:
                str = " order by order_id DESC";
                break;
            case 4:
                str = " order by dic_type_id";
                break;
            case 5:
                str = " order by dic_type_id DESC";
                break;
        }
        ArrayList<Dic> list = new ArrayList<Dic>();
        String sql = "select * from dic " + str;
        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Dic dic = null;
            while (rs.next()){
                dic = new Dic();
                dic.setDic_id(rs.getInt("dic_id"));
                dic.setOrder_id(rs.getInt("order_id"));
                dic.setDic_type_id(rs.getInt("dic_type_id"));
                dic.setDic_name(rs.getString("dic_name"));
                dic.setDef_value(rs.getBoolean("def_value"));
                list.add(dic);
            }
            System.out.println("获取信息成功\t" + this.getClass());
            System.out.println(list.size());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("获取信息失败\t" + this.getClass());
        } finally {
            this.closeAll(con, pstmt, rs);
        }
        return list;
    }
    //查询所有数据
    public ArrayList<Dic> getAllData(){
        ArrayList<Dic> list = new ArrayList<Dic>();
        String sql = "select * from dic";
        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Dic dic = null;
            while (rs.next()){
                dic = new Dic();
                dic.setDic_id(rs.getInt("dic_id"));
                dic.setOrder_id(rs.getInt("order_id"));
                dic.setDic_type_id(rs.getInt("dic_type_id"));
                dic.setDic_name(rs.getString("dic_name"));
                dic.setDef_value(rs.getBoolean("def_value"));
                list.add(dic);
            }
            System.out.println("获取信息成功\t" + this.getClass());
            System.out.println(list.size());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("获取信息失败\t" + this.getClass());
        } finally {
            this.closeAll(con, pstmt, rs);
        }
        return list;
    }
    public void showData(ArrayList<Dic> al){
        System.out.println("打印数据： ");
        for (int i = 0; i < al.size(); i++){
            System.out.println(al.get(i).getDic_id() + " " + al.get(i).getOrder_id() + " " + al.get(i).getDic_type_id()
                    + " " + al.get(i).getDic_name() + " " + al.get(i).isDef_value());
        }
    }
    //增加一条数据
    public boolean addDic(Dic d){
        boolean addSuccess = false;
        String sql = "insert into dic(order_id, dic_type_id, dic_name, def_value) values (?,?,?,?)";
        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);
            //pstmt.setInt(1, d.getDic_id());
            pstmt.setInt(1,d.getOrder_id());
            pstmt.setInt(2,d.getDic_type_id());
            pstmt.setString(3,d.getDic_name());
            pstmt.setInt(4,d.isDef_value()==true? 1 : 0); //true:1  false:0
            int flag = pstmt.executeUpdate();
            if (flag !=0){
                addSuccess = true;
                System.out.println("添加数据成功\t" + this.getClass());
            } else {
                System.out.println("添加数据失败\t" + this.getClass());
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeAll(con, pstmt, rs);
        }
        return addSuccess;
    }

    /*
    * 根据 dic_id 修改数据
    * order_id : unique 不能修改
    * */
    public boolean updateDic(Dic d){
        boolean updateSuccess = false;
        String sql = "update dic set   dic_type_id=?, dic_name=?, def_value=? where dic_id=" + d.getDic_id();
        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);
            //pstmt.setInt(1, d.getDic_id());
            //pstmt.setInt(1,d.getOrder_id());
            pstmt.setInt(1,d.getDic_type_id());
            pstmt.setString(2,d.getDic_name());
            pstmt.setInt(3,d.isDef_value()==true? 1 : 0); //true:1  false:0
            int flag = pstmt.executeUpdate();
            if (flag !=0){
                updateSuccess = true;
                System.out.println("修改数据成功\t" + this.getClass());
            } else {
                System.out.println("修改数据失败\t" + this.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll(con, pstmt, rs);
        }
        return updateSuccess;
    }
    //删除数据
    public boolean deleteDic(int id){
        boolean deleteSuccess = false;
        String sql = "delete from dic where dic_id=?";
        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            int flag = pstmt.executeUpdate();
            if (flag !=0){
                deleteSuccess = true;
                System.out.println("成功删除了字典编号为 " + id + " 的数据");
            } else {
                System.out.println("删除数据失败\t" + this.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll(con, pstmt, rs);
        }
        return deleteSuccess;
    }
}
