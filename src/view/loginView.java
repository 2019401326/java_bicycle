package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import util.JDBCUtil;
import view.UserMainView;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class loginView extends JFrame {
	JLabel lblNewLabel_3;

	private JPanel contentPane;
	private static JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_4;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private static String stra=null;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginView frame = new loginView();
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
	public loginView() {
		setTitle("\u81EA\u884C\u8F66\u79DF\u51ED\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cheakInputID() && cheakInputMM() && cheakInputpassword()) {
					
						if (((JRadioButton) rdbtnNewRadioButton).isSelected()) {
							if (cheakInputYZ()) {
								UserMainView dofrm = new UserMainView();
								dofrm.setVisible(true);
							} else {
								lblNewLabel_4.setText("密码错误");
							}
						} else if (((JRadioButton) rdbtnNewRadioButton_1).isSelected()) {
							if (adminlogin(textField.getText(),passwordField.getText())) {
								adminuserMainView dofrm_2 = new adminuserMainView();
								dofrm_2.setVisible(true);
								
							}
							else {
								lblNewLabel_4.setText("密码错误");
							}
						}

					
					}

				}
			});

			
		
		btnNewButton.setBounds(61, 189, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userregisterView dofrm_3=new userregisterView();
				dofrm_3.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnNewButton_1.setBounds(223, 189, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(72, 89, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(72, 119, 68, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(168, 89, 127, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 119, 127, 21);
		contentPane.add(passwordField);
		
		rdbtnNewRadioButton = new JRadioButton("用户登录");
		rdbtnNewRadioButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(61, 146, 79, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("管理员登录");
		rdbtnNewRadioButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		rdbtnNewRadioButton_1.setBounds(223, 146, 85, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup bg = new ButtonGroup();// 实现单选操作
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.red);
		lblNewLabel_3.setBounds(304, 89, 73, 15);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(Color.red);
		lblNewLabel_4.setBounds(305, 119, 72, 15);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setForeground(Color.red);
		lblNewLabel_6.setBounds(335, 154, 58, 15);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(loginView.class.getResource("/view/u=1250185044,4161971475&fm=26&gp=0.jpg")));
		lblNewLabel_2.setBounds(10, 10, 416, 253);
		contentPane.add(lblNewLabel_2);
		
		//账号规范
		
	}
	public boolean cheakInputID() {
		if (textField.getText().length() == 0) {
			lblNewLabel_3.setText("不能为空！");
			textField.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean cheakInputMM() {
		if (passwordField.getText().length() == 0) {
			lblNewLabel_4.setText("不能为空！");
			passwordField.requestFocus();
			return false;
		}
		return true;
	}
	
	
	public boolean cheakInputpassword() {
		if (!((JRadioButton) rdbtnNewRadioButton).isSelected()) {
			if (!((JRadioButton) rdbtnNewRadioButton_1).isSelected()) {
				lblNewLabel_6.setText("请选择！");
				return false;
			}
		}
		return true;
	}
	
	public boolean cheakInputYZ() {// 职员验证登录验证登录
		JDBCUtil dbconn = new JDBCUtil();// 数据库连接
		String sql = "SELECT* from user_info";
		try (java.sql.Connection conn = dbconn.getConnection();
				java.sql.Statement stme =  conn.createStatement();
				ResultSet rs = stme.executeQuery(sql);) {
			String ID = null;
			String password = null;
			while (rs.next()) {
				ID = rs.getString("username");
				password = rs.getString("password");
				if ((textField.getText()).equals(ID) && (passwordField.getText()).equals(password)) {
					stra=ID;
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public static String getStr2() {
		return stra;
	}
	
	public static String getusername() {
		String name=textField.getText();
		return name;
	}
	
	
	
	//管理员验证
	public boolean adminlogin(String userName, String password) {
		try {
			BufferedReader br=new BufferedReader(new FileReader("E:\\管理员注册.txt"));
			String line;
			while((line=br.readLine())!=null) {
				String[] s=line.split(" ");
				if(s[0].equals(userName)&&s[1].equals(password)) {
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return false;
}
}

