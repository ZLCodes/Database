package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.*;
public class Store extends JFrame{
	private JLabel jl1,jl2,jl3,jl4;
	private JTextField jt1,jt2,jt3;
	private JPasswordField jpt;
	private JButton jb1,jb2;
	private String a_id;
	public Store(String str)
	{
		a_id=str;
		jl1 = new JLabel("卡号");
		//jl2 = new JLabel("密码");
		jl3 = new JLabel("交易金额");
		//jl4 = new JLabel("当前余额");
		
		jt1 = new JTextField(15);
		jt2 = new JTextField(15);
		//jt3 = new JTextField(15);
		//jt3.setEditable(false);
		//jpt = new JPasswordField(15);
		jb1 = new JButton("存入");
		jb2 = new JButton("取消");
		this.add(jl1);
		this.add(jt1);
		//this.add(jl2);
		//this.add(jpt);
		this.add(jl3);
		this.add(jt2);
		this.add(jb1);
		this.add(jb2);
		this.setLayout(new GridLayout(3,2));
		this.setSize(300,130);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				db dbconn = new db();
				try {
					String ID;
					String account = jt1.getText();
					String sql = "select U_ID from CardInfo where C_ID='"+account+"'";
					System.out.println(sql);
					ResultSet rs = dbconn.executeQuery(sql);
					if(rs.next())
					{
						ID = rs.getString("U_ID");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"账号不存在！");
						return;
					}
					
					float money = Float.parseFloat(jt2.getText());
					String StoreProc = "{call Pro_Store(?,?,?,?,?,?)}";
					CallableStatement Store_pro = dbconn.Store(StoreProc);
					Store_pro.setString(1, ID);
					Store_pro.setString(2,a_id);
					Store_pro.setString(3,account);
					Store_pro.setFloat(4,money);
					Store_pro.setInt(5,0);
					Store_pro.registerOutParameter(6, Types.VARCHAR);
					Store_pro.execute();
					String msg = Store_pro.getString(6);
					JOptionPane.showMessageDialog(null,msg);
					dispose();
				}catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jt2.setText("");
				jt1.setText("");
				
			}
		});
	}
	
	
	public Store(String str,String account)
	{
		String ac = account;
		
		a_id=str;
		jl1 = new JLabel("卡号");
		//jl2 = new JLabel("密码");
		jl3 = new JLabel("交易金额");
		//jl4 = new JLabel("当前余额");
		
		jt1 = new JTextField(15);
		jt1.setEditable(false);
		jt1.setText(ac);
		jt2 = new JTextField(15);
		//jt3 = new JTextField(15);
		//jt3.setEditable(false);
		//jpt = new JPasswordField(15);
		jb1 = new JButton("存入");
		jb2 = new JButton("取消");
		this.add(jl1);
		this.add(jt1);
		//this.add(jl2);
		//this.add(jpt);
		this.add(jl3);
		this.add(jt2);
		this.add(jb1);
		this.add(jb2);
		this.setLayout(new GridLayout(3,2));
		this.setSize(300,150);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				db dbconn = new db();
				try {
					String ID;
					String account = jt1.getText();
					String sql = "select U_ID from CardInfo where C_ID='"+account+"'";
					System.out.println(sql);
					ResultSet rs = dbconn.executeQuery(sql);
					if(rs.next())
					{
						ID = rs.getString("U_ID");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"账号不存在！");
						return;
					}
					
					float money = Float.parseFloat(jt2.getText());
					String StoreProc = "{call Pro_Store(?,?,?,?,?,?)}";
					CallableStatement Store_pro = dbconn.Store(StoreProc);
					Store_pro.setString(1, ID);
					Store_pro.setString(2,a_id);
					Store_pro.setString(3,account);
					Store_pro.setFloat(4,money);
					Store_pro.setInt(5,0);
					Store_pro.registerOutParameter(6, Types.VARCHAR);
					Store_pro.execute();
					String msg = Store_pro.getString(6);
					JOptionPane.showMessageDialog(null,msg);
					dispose();
				}catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jt2.setText("");
				jt1.setText("");
				
			}
		});
	}
	
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		Store a = new Store("202150401");
	}
}
