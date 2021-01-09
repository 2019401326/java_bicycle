package util;
/*
 * 数据库产生10000条随机数据
 */


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import static java.lang.Math.random;

public class dateopreater {
	
    
    
    public static String getDepart() {
        String[] departArray = { "山地车", "代步车", "公路车", "城市休闲车", "旅行车", "电动助力自行车", "折叠自行车", "死飞"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    public static String getBrand() {
        String[] departArray = { "Giant", "Merida", "UCC", "XDS", "KHS", "solomo", "飞鸽", "Specialized"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    public static String getAddress() {
        String[] departArray = { "中国", "德国", "美国", "澳大利亚", "日本", "英国", "菲律宾", "加拿大"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    
    
    
    
    public static int getNum(int start,int end) {//随机返回返回指定范围间的整数
        //Math.random()随机返回0.0至1.0之间的数
        return (int)(Math.random()*(end-start+1)+start);
    }
    
    //随机获取用户名
    public static StringBuilder getEid() {//不使用String，因为需要大量拼接字符串
        StringBuilder eid=new StringBuilder("2021");//工号前4位相同
        StringBuilder eid1=new StringBuilder(String.valueOf(getNum(1,99999)));//随机取后5位
        if(eid1.length()==1) {
            eid1=eid1.insert(0, "0000");//如果是1位数，前面增加4个0
            eid=eid.append(eid1);//前6位与后3位拼接成学号
        }else if(eid1.length()==2) {
            eid1=eid1.insert(0, "000");//如果是2位数，前面增加3个0
            eid=eid.append(eid1);
        }else if(eid1.length()==3){
            eid1=eid1.insert(0, "00");//如果是3位数，前面增加2个0
        }else if(eid1.length()==4){
            eid1=eid1.insert(0, "0");//如果是4位数，前面增加1个0
        } else {
            eid=eid.append(eid1);
        }
        return eid;
    }
  

    //随即返回一个电话号码
    private static String getTele() {
        String[] telFirst=("134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156," +
                "133,153").split(",");
        int index=(int)(Math.random()*telFirst.length);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }
    
   
    public static void main(String[] args) {
        addDataAll();
    }
    
    public static void addDataAll() {//增加数据
        JDBCUtil dbcs=new JDBCUtil();//使用1中定义的连接数据库的类
        String sql="insert into bicycle_info(lpn,brand,type,price,address) values(?,?,?,?,?)";//使用占位符定义插入语句
        try(java.sql.Connection conn=dbcs.getConnection();//获取数据库接
            java.sql.PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
            ArrayList<String> alist=new ArrayList<String>();//定义集合
            for(int i=1;i<=10000;) {
                String lpn=getEid().toString();//随机获取工号
                if(!alist.contains(lpn)) {//判断工号是否唯一
                    alist.add(lpn);//将学号加入集合
                    String brand=getBrand();//随机获取用户名
                    String type=getDepart();//随机获取电话号码
                    int price=getNum(100, 4000);//获取时间
                    String address=getAddress();//
                    int num=getNum(1, 10);//获取数量
                    pstmt.setString(1, lpn);//定义第1个占位符的内容
                    pstmt.setString(2, brand);//定义第2个占位符的内容
                    pstmt.setString(3, type);//定义第3个占位符的内容
                    pstmt.setInt(4,price);
                    pstmt.setString(5,address);
                    pstmt.addBatch();//加入批处理等待执行
                    i++;//学号唯一，循环继续往下执行
                }
            }
            pstmt.executeBatch();//批量执行插入操作
            JOptionPane.showMessageDialog(null, "sucess");
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

