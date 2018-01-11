package BankSystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public 	class desview extends JFrame implements ActionListener
{	public JButton ok,cancel;
	public JTextField account,id;
	private JLabel jl1,jl2;
	int i;
	public desview(int i)
	{
		this.i=i;
		ok = new JButton("确定");
		cancel = new JButton("取消");
		ok.addActionListener(this);
		cancel.addActionListener(this);
		account = new JTextField(20);
		id = new JTextField(20);
		jl1 = new JLabel("账号");
		jl2 = new JLabel("身份证号");
		this.add(jl1);
		this.add(account);
		this.add(jl2);
		this.add(id);
		this.add(ok);
		this.add(cancel);
		this.setLayout(new GridLayout(3, 2));
		this.setSize(300,200);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			String ac = account.getText();
			String ID = id.getText();
			String sq = "select Card_State from CardInfo where U_ID = '"+ID+"' and C_ID = '"+ac+"' and Card_State = 0";
			String sqq = "select Card_State from CardInfo where U_ID = '"+ID+"' and C_ID = '"+ac+"' and Card_State != 0";
			String sql = "update CardInfo set Card_State = ~Card_State where U_ID = '"+ ID +"' and C_ID = '"+ ac +"'";
			db dbconn = new db();
			try {
				ResultSet rs = dbconn.executeQuery(sq);
//				int b= rs.getInt("Card_State");
				
				
				if(i==1)
				{
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null,"该账号已经被禁用！");
					}
					else
					{
						int a = dbconn.executeUpdate(sql);
						if(a!=0)
						{
							JOptionPane.showMessageDialog(null,"操作成功！");
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"账号信息有误！");
							return;
						}
					}
						
				}
				else
				{
					if(rs.next())
					{
						int a = dbconn.executeUpdate(sql);
						if(a!=0)
						{
							JOptionPane.showMessageDialog(null,"操作成功！");
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"账号信息有误！");
							return;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"该账号使用正常！");
					}
				}
			}catch (SQLException ev) {
				System.out.println(ev.toString());
			}
		}
	}
}