package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import util.JDBCUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;

public class lookMessageView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private java.sql.Statement stmt=null;
	private ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lookMessageView frame = new lookMessageView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public lookMessageView() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u7559\u8A00");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(163, 0, 207, 47);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 45, 404, 196);
		contentPane.add(scrollPane);
		
		String[] title = { "留言用户",  "留言内容" };// 定义数组表示表格标题
		DefaultTableModel model=new DefaultTableModel(title,0);
		getRental(model);
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {// 设置第1列不可编辑
					return false;
				}
				return true;
			}
		};// 实例化表格装载表格模型实例
		scrollPane.setViewportView(table);
	}
	
	public void getRental(DefaultTableModel model) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="SELECT*FROM Message_info";
		stmt=jdbc.conn.createStatement();
		rs=stmt.executeQuery(sql);
		Vector<Vector> rows=new Vector<Vector>();
		while(rs.next()) {

			Vector row=new Vector();
			row.add(rs.getInt("username"));
			row.add(rs.getString("message"));
			
			model.addRow(row);//将行数据添加到记录集合中
			}
		}
	
}
