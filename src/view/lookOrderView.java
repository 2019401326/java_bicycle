package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import util.JDBCUtil;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class lookOrderView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private static JTable table_3;
	private java.sql.Statement stmt=null;
	private ResultSet rs=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lookOrderView frame = new lookOrderView();
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
	public lookOrderView() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�ҵĶ���");
		lblNewLabel.setFont(new Font("΢ܛ�����w", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(148, 10, 120, 25);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 45, 404, 196);
		contentPane.add(scrollPane);
		
		String[] title = { "����",  "����","��ʼʱ��","����ʱ��","�û���","��ϵ��ʽ" };// ���������ʾ������
		DefaultTableModel model=new DefaultTableModel(title,0);
		getRental(model);
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {// ���õ�1�в��ɱ༭
					return false;
				}
				return true;
			}
		};// ʵ�������װ�ر��ģ��ʵ��
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("����������");
		btnNewButton.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton.setBounds(278, 11, 118, 23);
		contentPane.add(btnNewButton);
		
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
		
		
		
		
	}
	public void getRental(DefaultTableModel model) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="SELECT*FROM rental_info";
		stmt=jdbc.conn.createStatement();
		rs=stmt.executeQuery(sql);
		Vector<Vector> rows=new Vector<Vector>();
		String AD = loginView.getusername();
		while(rs.next()) {
			String SD = rs.getString("name");
			if (AD.equals(SD)) {
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
	 
	
}
