package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class kehuView extends JFrame implements ActionListener{
	private JButton qukuan,zhuanzhang,cunkuan,yue;
	private JLabel jl;
	private JTextField jt;
	private String account;
	public kehuView(String s1)
	{
		account = s1;
		qukuan = new JButton("取款");
		zhuanzhang = new JButton("转账");
		cunkuan = new JButton("存款");
		yue = new JButton("余额");
		zhuanzhang.addActionListener(this);
		qukuan.addActionListener(this);
		cunkuan.addActionListener(this);
		yue.addActionListener(this);
		jl = new JLabel("账户余额");
		jt= new JTextField(30);
		JPanel jp = new JPanel();
		jp.add(yue);
		jp.add(qukuan);
		jp.add(zhuanzhang);
		jp.add(cunkuan);
		JPanel jp1 = new JPanel();
		jp1.add(jl);
		jp1.add(jt);
		jt.setEditable(false);
		jp1.setLayout(new GridLayout(1,2));
		this.add(jp1);
		this.add(jp);
		this.setSize(300,160);
		this.setLayout(new GridLayout(2, 1));
		this.setVisible(true);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==yue)
		{
			try {
				db dbconn = new db();
				String sql = "select * from CardInfo where C_ID='"+account+"'";
				System.out.println(sql);
				ResultSet rs = dbconn.executeQuery(sql);
				//System.out.println(""+rs.getFloat("Balance"));
				if(rs.next())
					jt.setText(""+rs.getFloat("Balance"));
				
			} catch (SQLException sqle) {
				System.out.println(sqle.toString());
			} 

		}
		else if(ev.getSource()==cunkuan)
		{
			new Store("000000",account);
		}
		else if(ev.getSource()==zhuanzhang)
		{
			new zhuanzhang("000000",account);
		}
		else
			new qukuan("000000",account);
	}
	public static void main(String[] arg)
	{
		new kehuView("6212263602015645305");
	}
}
