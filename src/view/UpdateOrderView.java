package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import util.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class UpdateOrderView extends JFrame {
	private PreparedStatement pstm=null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateOrderView frame = new UpdateOrderView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateOrderView() {
		setTitle("\u81EA\u884C\u8F66\u79DF\u51ED\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u586B\u5199\u4FE1\u606F");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(28, 10, 161, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("品牌");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(28, 46, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(105, 46, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("车型");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 93, 58, 15);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 93, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("车号");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3.setBounds(189, 46, 58, 15);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(266, 43, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("数量");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_4.setBounds(189, 93, 58, 15);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(266, 93, 66, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("开始时间");
		lblNewLabel_5.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_5.setBounds(28, 140, 66, 15);
		contentPane.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(105, 140, 66, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("结束时间");
		lblNewLabel_6.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_6.setBounds(28, 193, 66, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(105, 193, 66, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("用户名");
		lblNewLabel_7.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_7.setBounds(181, 140, 66, 15);
		contentPane.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(266, 140, 66, 21);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("联系方式");
		lblNewLabel_8.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_8.setBounds(181, 193, 66, 15);
		contentPane.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(266, 193, 66, 21);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton = new JButton("确认下单");
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertUserOrder(textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_5.getText(),
							textField_6.getText(),textField_7.getText());//调用方法将订单数据存入数据库
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "下单成功！");
			}
		});
		btnNewButton.setBounds(142, 243, 105, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\lenovo\\Desktop\\2345_image_file_copy_2.jpg"));
		lblNewLabel_9.setBounds(0, 0, 436, 266);
		contentPane.add(lblNewLabel_9);
	}
	
	//定义方法将数据存入数据库
	public void insertUserOrder(Object... params) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="insert into rental_info"
				+ "(lpn,num,startdate,enddate,name,telenumber)"
				+ " values(?,?,?,?,?,?)";
		pstm = jdbc.conn.prepareStatement(sql);
		for(int i=0;i<params.length;i++){
			pstm.setObject(i+1, params[i]);
		}
		pstm.executeUpdate();
		pstm.close();
	}

}
