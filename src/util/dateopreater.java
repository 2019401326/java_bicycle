package util;
/*
 * ���ݿ����10000���������
 */


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import static java.lang.Math.random;

public class dateopreater {
	
    
    
    public static String getDepart() {
        String[] departArray = { "ɽ�س�", "������", "��·��", "�������г�", "���г�", "�綯�������г�", "�۵����г�", "����"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    public static String getBrand() {
        String[] departArray = { "Giant", "Merida", "UCC", "XDS", "KHS", "solomo", "�ɸ�", "Specialized"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    public static String getAddress() {
        String[] departArray = { "�й�", "�¹�", "����", "�Ĵ�����", "�ձ�", "Ӣ��", "���ɱ�", "���ô�"};
        int departPos = (int) (7 * random());
        return departArray[departPos];
    }
    
    
    
    
    
    public static int getNum(int start,int end) {//������ط���ָ����Χ�������
        //Math.random()�������0.0��1.0֮�����
        return (int)(Math.random()*(end-start+1)+start);
    }
    
    //�����ȡ�û���
    public static StringBuilder getEid() {//��ʹ��String����Ϊ��Ҫ����ƴ���ַ���
        StringBuilder eid=new StringBuilder("2021");//����ǰ4λ��ͬ
        StringBuilder eid1=new StringBuilder(String.valueOf(getNum(1,99999)));//���ȡ��5λ
        if(eid1.length()==1) {
            eid1=eid1.insert(0, "0000");//�����1λ����ǰ������4��0
            eid=eid.append(eid1);//ǰ6λ���3λƴ�ӳ�ѧ��
        }else if(eid1.length()==2) {
            eid1=eid1.insert(0, "000");//�����2λ����ǰ������3��0
            eid=eid.append(eid1);
        }else if(eid1.length()==3){
            eid1=eid1.insert(0, "00");//�����3λ����ǰ������2��0
        }else if(eid1.length()==4){
            eid1=eid1.insert(0, "0");//�����4λ����ǰ������1��0
        } else {
            eid=eid.append(eid1);
        }
        return eid;
    }
  

    //�漴����һ���绰����
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
    
    public static void addDataAll() {//��������
        JDBCUtil dbcs=new JDBCUtil();//ʹ��1�ж�����������ݿ����
        String sql="insert into bicycle_info(lpn,brand,type,price,address) values(?,?,?,?,?)";//ʹ��ռλ������������
        try(java.sql.Connection conn=dbcs.getConnection();//��ȡ���ݿ��
            java.sql.PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
            ArrayList<String> alist=new ArrayList<String>();//���弯��
            for(int i=1;i<=10000;) {
                String lpn=getEid().toString();//�����ȡ����
                if(!alist.contains(lpn)) {//�жϹ����Ƿ�Ψһ
                    alist.add(lpn);//��ѧ�ż��뼯��
                    String brand=getBrand();//�����ȡ�û���
                    String type=getDepart();//�����ȡ�绰����
                    int price=getNum(100, 4000);//��ȡʱ��
                    String address=getAddress();//
                    int num=getNum(1, 10);//��ȡ����
                    pstmt.setString(1, lpn);//�����1��ռλ��������
                    pstmt.setString(2, brand);//�����2��ռλ��������
                    pstmt.setString(3, type);//�����3��ռλ��������
                    pstmt.setInt(4,price);
                    pstmt.setString(5,address);
                    pstmt.addBatch();//����������ȴ�ִ��
                    i++;//ѧ��Ψһ��ѭ����������ִ��
                }
            }
            pstmt.executeBatch();//����ִ�в������
            JOptionPane.showMessageDialog(null, "sucess");
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

