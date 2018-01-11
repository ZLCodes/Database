package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.*;
public class zhuanzhang extends JFrame implements ActionListener {
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	private JTextField jt1,jt3,jt4,jt5,jt6;
	private JPasswordField jt2;
	private JButton jb1,jb2;
	private JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	private String a_id;
	public zhuanzhang(String str)
	{
		a_id=str;
		jl1 = new JLabel("ת������");
		jl2 = new JLabel("����");
		jl3 = new JLabel("���");
		//jl4 = new JLabel("���ڽ��");
		jl5 = new JLabel("ת�뿨��");
		jl6 = new JLabel("���׽��");
		
		jt1 = new JTextField(20);
		jt2 = new JPasswordField(20);
		jt3 = new JTextField(20);
		jt3.setEditable(false);
		jt4 = new JTextField(20);
		jt4.setEditable(false);
		jt5 = new JTextField(20);
		jt6 = new JTextField(20);
		jb1 = new JButton("ת��");
		jb2 = new JButton("ȡ��");
		
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(1,2));
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1,2));
		jp3 = new JPanel();
		jp3.setLayout(new GridLayout(1,2));
		jp4 = new JPanel();
		jp4.setLayout(new GridLayout(1,2));
		jp5 = new JPanel();
		jp5.setLayout(new GridLayout(1,2));
		jp6 = new JPanel();
		jp6.setLayout(new GridLayout(1,2));
		jp7 = new JPanel();
		jp7.setLayout(new GridLayout(1,2));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jl3);
		jp3.add(jt3);
		//jp4.add(jl4);
		//jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(jb1);
		jp7.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.setSize(400,270);
		this.setLayout(new GridLayout(6,1));
		this.setResizable(false);
		this.setLocation(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==jb1)
		{
			db dbconn = new db();
			try {
				String ID;
				String pw = jt2.getText();
				String account = jt1.getText();
				String sql = "select * from CardInfo where C_ID='"+account+"'and Passwd = '"+pw+"'";
				String account2 = jt5.getText();
				float yue;
				float money = Float.parseFloat(jt6.getText());
				System.out.println(sql);
				ResultSet rs = dbconn.executeQuery(sql);
				if(rs.next())
				{
					ID = rs.getString("U_ID");
					yue=rs.getFloat("Balance");
					jt3.setText(""+yue);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"�˺Ż����벻��ȷ��");
					return;
				}
				if(yue<money)
				{
					JOptionPane.showMessageDialog(null,"���㣡");
					return;
				}
				
				rs = dbconn.executeQuery("select* from Store where C_ID = '"+account2+"'");
				if(rs.next())
				{
					if(rs.getInt("Kind")!=0)
					{
						JOptionPane.showMessageDialog(null,"�Է��˻�Ϊ�����˻����޷�ת�룡");
						return;
					}
				}
				else
				{
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
					System.out.println(msg);
					Store_pro.setString(3,account2);
					Store_pro.setFloat(4,money);
					Store_pro.execute();
				}	
				String ZhuanProc = "{call Pro_zh(?,?,?,?,?,?)}";
				CallableStatement Zhuan_pro = dbconn.Prozh(ZhuanProc);
				Zhuan_pro.setString(1, a_id);
				Zhuan_pro.setString(2,ID);
				Zhuan_pro.setString(3,account);
				Zhuan_pro.setString(4,account2);
				Zhuan_pro.setFloat(5,money);
				System.out.println(a_id+" "+ID+" "+account+" "+account2+" ");
				Zhuan_pro.registerOutParameter(6, Types.VARCHAR);
				Zhuan_pro.execute();
				String msg = Zhuan_pro.getString(6);
				JOptionPane.showMessageDialog(null,msg);
				dispose();
			}catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		else
			dispose();
	}
	public zhuanzhang(String str,String account)
	{
		a_id=str;
		jl1 = new JLabel("ת������");
		jl2 = new JLabel("����");
		jl3 = new JLabel("���");
		//jl4 = new JLabel("���ڽ��");
		jl5 = new JLabel("ת�뿨��");
		jl6 = new JLabel("���׽��");
		
		jt1 = new JTextField(20);
		jt1.setEditable(false);
		jt1.setText(account);
		jt2 = new JPasswordField(20);
		jt3 = new JTextField(20);
		jt3.setEditable(false);
		jt4 = new JTextField(20);
		jt4.setEditable(false);
		jt5 = new JTextField(20);
		jt6 = new JTextField(20);
		jb1 = new JButton("ת��");
		jb2 = new JButton("ȡ��");
		
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(1,2));
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1,2));
		jp3 = new JPanel();
		jp3.setLayout(new GridLayout(1,2));
		jp4 = new JPanel();
		jp4.setLayout(new GridLayout(1,2));
		jp5 = new JPanel();
		jp5.setLayout(new GridLayout(1,2));
		jp6 = new JPanel();
		jp6.setLayout(new GridLayout(1,2));
		jp7 = new JPanel();
		jp7.setLayout(new GridLayout(1,2));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jl3);
		jp3.add(jt3);
		//jp4.add(jl4);
		//jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(jb1);
		jp7.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.setSize(400,270);
		this.setLayout(new GridLayout(6,1));
		this.setResizable(false);
		this.setLocation(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		zhuanzhang a = new zhuanzhang("202150401");
	}

}
