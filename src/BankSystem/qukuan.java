package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.*;

public class qukuan extends JFrame implements ActionListener {
	private JLabel all,keyong,money,account,pwd;
	private JButton ok,cancel;
	private JTextField Ac,left,keyongleft,qukuan;
	private JPasswordField pw;
	private String a_id;
	public qukuan(String str)
	{
		a_id = str;
		all = new JLabel("当前余额:");
		keyong = new JLabel("活期余额：");
		money = new JLabel("取款金额：");
		account = new JLabel("账号：");
		pwd = new JLabel("密码：");
		Ac = new JTextField(20);
		left = new JTextField(20);
		keyongleft = new JTextField(20);
		qukuan = new JTextField(20);
		pw = new JPasswordField(20);
		ok = new JButton("确认");
		cancel = new JButton("取消");
		this.add(account);
		this.add(Ac);
		this.add(pwd);
		this.add(pw);
		this.add(all);
		this.add(left);
		//this.add(keyong);
		//this.add(keyongleft);
		this.add(money);
		this.add(qukuan);
		this.add(ok);
		this.add(cancel);
		left.setEnabled(false);
		//keyongleft.setEnabled(false);
		this.setLayout(new GridLayout(5, 2));
		this.setVisible(true);
		this.setSize(380, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		ok.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	public qukuan(String str,String ac)
	{
		a_id = str;
		all = new JLabel("当前余额:");
		keyong = new JLabel("活期余额：");
		money = new JLabel("取款金额：");
		account = new JLabel("账号：");
		pwd = new JLabel("密码：");
		Ac = new JTextField(20);
		Ac.setEditable(false);
		Ac.setText(ac);
		left = new JTextField(20);
		keyongleft = new JTextField(20);
		qukuan = new JTextField(20);
		pw = new JPasswordField(20);
		ok = new JButton("确认");
		cancel = new JButton("取消");
		this.add(account);
		this.add(Ac);
		this.add(pwd);
		this.add(pw);
		this.add(all);
		this.add(left);
		//this.add(keyong);
		//this.add(keyongleft);
		this.add(money);
		this.add(qukuan);
		this.add(ok);
		this.add(cancel);
		left.setEnabled(false);
		//keyongleft.setEnabled(false);
		this.setLayout(new GridLayout(5, 2));
		this.setVisible(true);
		this.setSize(380, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		ok.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==ok)
		{
			db dbconn = new db();
			try {
				String ID;
				String account = Ac.getText();
				String pwd = pw.getText();
				String sql = "select * from CardInfo where C_ID='"+account+"'and Card_State!=0 and Passwd = '"+ pwd+"'";
				System.out.println(sql);
				float a;
				ResultSet rs = dbconn.executeQuery(sql);
				if(rs.next())
				{
					ID = rs.getString("U_ID");
					a=rs.getFloat("Balance");
					left.setText(""+a);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"信息填写错误！");
					return;
				}
			
				float money = Float.parseFloat(qukuan.getText());
				if(a<money)
				{
					JOptionPane.showMessageDialog(null,"余额不足！");
					return;
				}
				
				String StoreProc = "{call Pro_Store(?,?,?,?,?,?)}";
				CallableStatement Store_pro = dbconn.Store(StoreProc);
				Store_pro.setString(1, ID);
				Store_pro.setString(2,a_id);
				Store_pro.setString(3,account);
				Store_pro.setFloat(4,-money);
				Store_pro.setInt(5,0);
				Store_pro.registerOutParameter(6, Types.VARCHAR);
				Store_pro.execute();
				String msg = Store_pro.getString(6);
				JOptionPane.showMessageDialog(null,"取款成功！");
				//dispose();
			}catch (SQLException e) {
			System.out.println(e.toString());
			}
		}
		else
			dispose();
	}
	
	public static void main(String[] arg)
	{
		new qukuan("202150401");
	}
	

}
