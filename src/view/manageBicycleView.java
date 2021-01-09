package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.Font;

public class manageBicycleView extends JFrame {

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
					manageBicycleView frame = new manageBicycleView();
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
	public manageBicycleView() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(22, 10, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBicycleView a=new addBicycleView();
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(125, 9, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ɾ��");
		btnNewButton_1.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {// �Ƿ�ѡ����Ҫɾ������
					if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��������", "", JOptionPane.YES_NO_OPTION) == 0) {// ȷ�϶Ի���
						int count = table.getSelectedRow();// ��ȡ��ѡ�е��кţ���¼��
						String getlpn = table.getValueAt(count, 0).toString();// ��ȡ���ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
						model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
						try {
							Stuoperate.deleteTable2(getlpn);
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
		btnNewButton_1.setBounds(232, 9, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 45, 404, 196);
		contentPane.add(scrollPane);
		
		String[] title = { "����",  "Ʒ��","����","�۸�","����" };// ���������ʾ������
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
		
		
		
		JButton btnNewButton_2 = new JButton("����");
		btnNewButton_2.setFont(new Font("΢ܛ�����w", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=textField.getText().trim();//��ȡ����ؼ����ı����ֵ
				if(key.length()!=0) {
					sorter.setRowFilter(RowFilter.regexFilter(key));//�Ƿ����������ֵ
				}else {
					sorter.setRowFilter(null);//�����ˣ���ʾ��������
				}
			}
		});
		btnNewButton_2.setBounds(339, 9, 97, 23);
		contentPane.add(btnNewButton_2);

		
		
		JButton btnNewButton_3 = new JButton("�޸�");
		btnNewButton_3.setBounds(135, 53, 97, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("����");
		btnNewButton_4.setBounds(242, 57, 97, 23);
		contentPane.add(btnNewButton_4);
	}
	
	
	//װ������
	public void getRental(DefaultTableModel model) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="SELECT*FROM bicycle_info";
		Statement stmt = jdbc.conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Vector<Vector> rows=new Vector<Vector>();
		while(rs.next()) {
			Vector row=new Vector();
			row.add(rs.getInt("lpn"));
			row.add(rs.getString("brand"));
			row.add(rs.getString("type"));
			row.add(rs.getString("price"));
			row.add(rs.getString("address"));
			model.addRow(row);//����������ӵ���¼������
			}
	}
	
	

}
