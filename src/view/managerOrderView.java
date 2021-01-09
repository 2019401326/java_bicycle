package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import util.JDBCUtil;
import util.Stuoperate;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class managerOrderView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerOrderView frame = new managerOrderView();
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
	public managerOrderView() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("删除");
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {// 是否选择了要删除的行
					if (JOptionPane.showConfirmDialog(null, "确定要删除数据吗？", "", JOptionPane.YES_NO_OPTION) == 0) {// 确认对话框
						int count = table.getSelectedRow();// 获取你选中的行号（记录）
						String getlpn = table.getValueAt(count, 0).toString();// 读取你获取行号的某一列的值（也就是字段）
						String getname=	table.getValueAt(count, 4).toString();// 读取你获取行号的某一列的值（也就是字段）
//						System.out.println(getname + " ");
						model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
						try {
							Stuoperate.deleteTable(getlpn,getname);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "数据删除成功");
						// 从表格数据中删除行,model为DefaultTableModel类型，排序后不能直接使用表格的getSelectedRow方法获取被选中的行
					}
				} else {
					JOptionPane.showMessageDialog(null,"请选择你要删除的行!");
				}
			}
			
		});
		btnNewButton.setBounds(172, 9, 97, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(80, 10, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 45, 404, 196);
		contentPane.add(scrollPane);
		
		String[] title = { "车号",  "数量","开始时间","结束时间","用户名","联系方式" };// 定义数组表示表格标题
		model=new DefaultTableModel(title,0);
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
		
		
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);//设置表格的排序器
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();//设置排序的集合，
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));//设置第一种排序方式：第1个参数2，为排序字段，指明为3第个字段cj，第2个参数为升序
		//sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//如果第一种排序有相同值，设置第二种排序方式：第1个参数0，为排序字段，指明为1第个字段xh，第2个参数为升序
		sorter.setSortKeys(sortKeys);
		
		JButton btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=textField.getText().trim();//获取输入关键字文本框的值
				if(key.length()!=0) {
					sorter.setRowFilter(RowFilter.regexFilter(key));//是否包含输入框的值
				}else {
					sorter.setRowFilter(null);//不过滤，显示所有数据
				}
			}
		});
		btnNewButton_1.setBounds(298, 9, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("请输入：");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 13, 71, 15);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	
	public void getRental(DefaultTableModel model) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="SELECT*FROM rental_info";
		Statement stmt = jdbc.conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector> rows=new Vector<Vector>();
		while(rs.next()) {
			Vector row=new Vector();
			row.add(rs.getInt("lpn"));
			row.add(rs.getInt("num"));
			row.add(rs.getString("startdate"));
			row.add(rs.getString("enddate"));
			row.add(rs.getString("name"));
			row.add(rs.getInt("telenumber"));
			model.addRow(row);//将行数据添加到记录集合中
			}
	}
}
