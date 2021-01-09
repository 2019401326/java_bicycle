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
		
		JButton btnNewButton = new JButton("ɾ��");
		btnNewButton.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {// �Ƿ�ѡ����Ҫɾ������
					if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��������", "", JOptionPane.YES_NO_OPTION) == 0) {// ȷ�϶Ի���
						int count = table.getSelectedRow();// ��ȡ��ѡ�е��кţ���¼��
						String getlpn = table.getValueAt(count, 0).toString();// ��ȡ���ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
						String getname=	table.getValueAt(count, 4).toString();// ��ȡ���ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
//						System.out.println(getname + " ");
						model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
						try {
							Stuoperate.deleteTable(getlpn,getname);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "����ɾ���ɹ�");
						// �ӱ��������ɾ����,modelΪDefaultTableModel���ͣ��������ֱ��ʹ�ñ���getSelectedRow������ȡ��ѡ�е���
					}
				} else {
					JOptionPane.showMessageDialog(null,"��ѡ����Ҫɾ������!");
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
		
		String[] title = { "����",  "����","��ʼʱ��","����ʱ��","�û���","��ϵ��ʽ" };// ���������ʾ������
		model=new DefaultTableModel(title,0);
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
		
		
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);//���ñ���������
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();//��������ļ��ϣ�
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));//���õ�һ������ʽ����1������2��Ϊ�����ֶΣ�ָ��Ϊ3�ڸ��ֶ�cj����2������Ϊ����
		//sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//�����һ����������ֵͬ�����õڶ�������ʽ����1������0��Ϊ�����ֶΣ�ָ��Ϊ1�ڸ��ֶ�xh����2������Ϊ����
		sorter.setSortKeys(sortKeys);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=textField.getText().trim();//��ȡ����ؼ����ı����ֵ
				if(key.length()!=0) {
					sorter.setRowFilter(RowFilter.regexFilter(key));//�Ƿ����������ֵ
				}else {
					sorter.setRowFilter(null);//�����ˣ���ʾ��������
				}
			}
		});
		btnNewButton_1.setBounds(298, 9, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("�����룺");
		lblNewLabel.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
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
			model.addRow(row);//����������ӵ���¼������
			}
	}
}
