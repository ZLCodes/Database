package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class StoreSelect extends JFrame implements ActionListener{
	private JTable table;
	private MyTableModel tablemodel;
	private JTextField year,month,day;
	private JButton ok;
	private int b;
	private String sql;
	private String account,ID;
	private JButton search;
	private JComboBox jcb;
	ArrayList<storentity> v;
	public StoreSelect(int b,String account,String ID)
	{
		v=new ArrayList<storentity>();
		year = new JTextField(4);
		month = new JTextField(4);
		day = new JTextField(4);
		this.b=b;
		this.account=account;
		this.ID=ID;
		String s[]= {"之前","之后"};
		jcb = new JComboBox(s);
		ok = new JButton("查询");
		Container contain = getContentPane();
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 8));
		jp.add(new JLabel("    年："));
		jp.add(year);
		jp.add(new JLabel("    月："));
		jp.add(month);
		jp.add(new JLabel("    日："));
		jp.add(day);
		jp.add(jcb);
		jp.add(ok);
		ok.addActionListener(this);
		contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));
		contain.add(jp);
		//contain.setLayout(new );
		
		this.setSize(600,300);
		this.setTitle("账户信息");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel = getModel();
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(530,250));
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	private MyTableModel getModel() {
				
		MyTableModel tableModel = new MyTableModel();
		db dbcon;
		try {
			dbcon = new db();
			if(b==0)
			{
				sql = "select * from Store where C_ID='"+account+"'and U_ID='"+ID+"'and Much>0";
				String str[]= {"身份证号","卡号","日期","金额","期限","利率"};
				for(int i= 0;i<6;i++)
					tableModel.addColumn(str[i]);
			}
			else if(b==1)
			{
				sql = "select * from Store where C_ID='"+account+"'and U_ID='"+ID+"'and Much<0";
				String str[]= {"身份证号","卡号","日期","金额"};
				for(int i= 0;i<4;i++)
					tableModel.addColumn(str[i]);
			}
				
			else if(b==2)
			{
				sql = "select * from Store where C_ID='"+account+"'and U_ID='"+ID+"'";
				String str[]= {"身份证号","卡号","日期","金额"};
				for(int i= 0;i<4;i++)
					tableModel.addColumn(str[i]);
			}
			else
			{
				sql = "select * from view_zh where C_ID1='"+account+"'and U_ID='"+ID+"'";
				String str[]= {"转入卡号","日期","金额","经办人"};
				for(int i= 0;i<4;i++)
					tableModel.addColumn(str[i]);
			}
			ResultSet rs = dbcon.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int Colnum = rsmd.getColumnCount();
			int i;
			v=new ArrayList<storentity>();
			if(b!=3)
			{
				while(rs.next()) {
					storentity store=new storentity();
					store.setA_ID(rs.getString("A_ID"));
					store.setC_ID(rs.getString("C_ID"));
					store.setDateTime(rs.getDate("DateTime_S"));
					store.setKind(rs.getInt("Kind"));
					store.setRate(rs.getFloat("Rate"));
					store.setMuch(rs.getFloat("Much"));
					store.setU_ID(rs.getString("U_ID"));
					v.add(store);
				}
			}
			else
			{
				while(rs.next()) {
					storentity store=new storentity();
					store.setC_ID(rs.getString("C_ID2"));
					store.setDateTime(rs.getDate("ztime"));
					store.setMuch(rs.getFloat("Much"));
					store.setNAME(rs.getString("A_NAME"));
					v.add(store);
				}
			}

			rs.close();
			for(i=0;i<v.size();i++) {
				
				if(b==0)
				{
					tableModel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),
							v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getKind(),v.get(i).getRate()});
				}
				else if(b ==1 ||b==2)
					tableModel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch()});
				else
				{
					tableModel.addRow(new Object[] {v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getNAME()});
				}
			}
			dbcon.closeConn();
		}catch(SQLException sqle) {
			System.out.println(sqle.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return tableModel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			Date t;
			
			String y = year.getText();
			String m = month.getText();
			String d = day.getText();
			if(y.length()!=4||Integer.parseInt(m)>12||Integer.parseInt(m)<=0||Integer.parseInt(d)<=0||Integer.parseInt(d)>31)
			{
				
				JOptionPane.showMessageDialog(null,"时间格式不对！！");
				return;
			}
			else
			{
				//String s = y+"-"+m+"-"+d;
				t = new Date(Integer.parseInt(y)-1900,Integer.parseInt(m)-1,Integer.parseInt(d));
			}
			
			tablemodel = null;
			tablemodel = new MyTableModel();
			if(b==0)
			{
				String str[]= {"身份证号","卡号","日期","金额","期限","利率"};
				for(int i= 0;i<6;i++)
					tablemodel.addColumn(str[i]);
			}
			else if(b==1||b==2)
			{
				String str[]= {"身份证号","卡号","日期","金额"};
				for(int i= 0;i<4;i++)
					tablemodel.addColumn(str[i]);
			}
			else
			{
				String str[]= {"转入卡号","日期","金额","经办人"};
				for(int i= 0;i<4;i++)
					tablemodel.addColumn(str[i]);
			}
			
			if(jcb.getSelectedIndex()==0)
			{
				int i;
				for(i=0;i<v.size();i++) {
					
					if(t.getTime()>=v.get(i).getDateTime().getTime())
					{
						if(b==0)
						{
							tablemodel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),
									v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getKind(),v.get(i).getRate()});
						}
						else if(b ==1 ||b==2)
							tablemodel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch()});
						else
							tablemodel.addRow(new Object[] {v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getNAME()});
					}
				}
			}
			else
			{
				int i;
				for(i=0;i<v.size();i++) {
					
					if(t.getTime()<=v.get(i).getDateTime().getTime())
					{
						if(b==0)
						{
							tablemodel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),
									v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getKind(),v.get(i).getRate()});
						}
						else if(b ==1 ||b==2)
							tablemodel.addRow(new Object[] {v.get(i).getU_ID(),v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch()});
						else
							tablemodel.addRow(new Object[] {v.get(i).getC_ID(),v.get(i).getDateTime(),v.get(i).getMuch(),v.get(i).getNAME()});
					}
				}
			}
		}
	       table.setModel(tablemodel);
	       table.setEnabled(true);
	}
	
	public static void main(String[] arg)
	{
		new StoreSelect(3,"6212263602015645305","1231");
	}

}
