package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import util.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class adminlookOrderView extends JFrame {
	private java.sql.Statement stmt=null;
	private ResultSet rs=null;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlookOrderView frame = new adminlookOrderView();
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
	public adminlookOrderView() throws SQLException {
		setForeground(Color.ORANGE);
		setTitle("\u81EA\u884C\u8F66\u79DF\u51ED\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�û�����");
		lblNewLabel.setFont(new Font("΢ܛ�����w", Font.BOLD, 20));
		lblNewLabel.setBounds(159, 6, 97, 19);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 35, 404, 206);
		contentPane.add(scrollPane);

		
		
		String[] title = { "����",  "����","��ʼʱ��","����ʱ��","�û���","��ϵ��ʽ" };// ���������ʾ������
		DefaultTableModel model=new DefaultTableModel(title,0);
		admingetRental(model);
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {// ���õ�1�в��ɱ༭
					return false;
				}
				return true;
			}
		};
		table.setBounds(24, 58, 390, 184);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("����������");
		btnNewButton.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

					// table.setAutoCreateRowSorter(true);//���û������б���ʱ�����Զ�����

					// =======����(����ָ����ĳ��������ʹ�����´���)==============
					TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);// ����������
					table.setRowSorter(sorter);// ���ñ���������
					ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
					// ���������ֶΡ����е�һ���ֶ�����
					sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
				
			}
		});
		btnNewButton.setBounds(281, 6, 119, 23);
		contentPane.add(btnNewButton);
	}
	
	
	public void admingetRental(DefaultTableModel model) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="SELECT lpn,num,startdate,enddate,name,telenumber FROM rental_info";
		stmt=jdbc.conn.createStatement();
		rs=stmt.executeQuery(sql);
		Vector<Vector> rows=new Vector<Vector>();
		while(rs.next()) {
			Vector row=new Vector();
			row.add(rs.getInt("lpn"));
			row.add(rs.getInt("num"));
			row.add(rs.getString("startdate"));
			row.add(rs.getString("enddate"));
			row.add(rs.getString("name"));
			row.add(rs.getInt("telenumber"));
			model.addRow(row);//����������ӵ���¼������
		}
	}
}
