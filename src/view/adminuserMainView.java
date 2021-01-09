package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class adminuserMainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminuserMainView frame = new adminuserMainView();
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
	public adminuserMainView() {
		setTitle("\u81EA\u884C\u8F66\u79DF\u51ED\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("查看订单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					adminlookOrderView adminl=new adminlookOrderView();
					adminl.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton.setBounds(124, 22, 157, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("管理订单");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					managerOrderView m=new managerOrderView();
					m.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_1.setBounds(124, 83, 157, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("车辆管理");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manageBicycleView m=new manageBicycleView();
					m.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_2.setBounds(124, 141, 157, 38);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查看留言");
		btnNewButton_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lookMessageView l;
				try {
					l = new lookMessageView();
					l.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_3.setBounds(124, 194, 157, 38);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(adminuserMainView.class.getResource("/view/2345_image_file_copy_2.jpg")));
		lblNewLabel.setBounds(0, 0, 436, 263);
		contentPane.add(lblNewLabel);
	}
}
