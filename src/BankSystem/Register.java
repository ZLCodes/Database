package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

import javax.swing.*;
public class Register extends JFrame implements ActionListener {
	private String A_ID;
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jladd;
	private JTextField jt1,jt2,jt3,jt4,jt5,jtadd;
	private JTextField jmoney;
	private JPasswordField jpt1,jpt2;
	private JComboBox jcb1,jcb2;
	private JButton jb1,jb2;
	private JPanel jp[] = new JPanel[11];
	private JRadioButton jrb1,jrb2;
	int panduan = 0;
	public Register(String str)
	{
		String[] choice = {"0","1","3","5"};
		jcb2 = new JComboBox(choice);
		A_ID=str;
		for(int i = 0;i < 11;i++)
		{
			jp[i] = new JPanel();
			jp[i].setLayout(new GridLayout());
		}
		jl1 = new JLabel("用户姓名*");
		jl2 = new JLabel("账号*");
		jl3 = new JLabel("密码（六位）*");
		jl4 = new JLabel("重复密码*");
		jl5 = new JLabel("性别");
		jl6 = new JLabel("出生日期*");
		jl7 = new JLabel("-");
		jl8 = new JLabel("-");
		jl9 = new JLabel("联系电话*");
		jl10 = new JLabel("身份证号*");
		jladd = new JLabel("家庭住址*");
		jt1 = new JTextField(15);
		jt2 = new JTextField(15);		
		jt3 = new JTextField(4);
		jt4 = new JTextField(15);
		jt5 = new JTextField(15);
		jmoney = new JTextField(15);
		jtadd = new JTextField(15);
		jpt1 = new JPasswordField(15);
		jpt2 = new JPasswordField(15);
		jrb1 = new JRadioButton("男",true);
		jrb2 = new JRadioButton("女");
		
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		
		jcb1 = new JComboBox();
		db dbconn;
		try {
			dbconn = new db();
			ResultSet rs = dbconn.executeQuery("select * from Account where Ac_state!=0");
			while(rs.next())
			{
				jcb1.addItem(rs.getString("account"));
			}
			
		}
		 catch (SQLException sqle) {
				System.out.println(sqle.toString());
			} catch (Exception a) {
				System.out.println(a.getMessage());
			}
		jb1 = new JButton("注册");
		jb2 = new JButton("取消");
		jp[0].add(jl1);
		jp[0].add(jt1);
		jp[1].add(jl2);
		jp[1].add(jcb1);
		jp[2].add(jl3);
		jp[2].add(jpt1);
		jp[3].add(jl4);
		jp[3].add(jpt2);
		jp[4].add(new JLabel("开户金额*"));
		jp[4].add(jmoney);
		jp[5].add(jl5);
		JPanel jpp1 = new JPanel();
		jpp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpp1.add(jrb1);
		jpp1.add(jrb2);
		jp[5].add(jpp1);
		jp[6].add(new JLabel("存款年限*"));
		jp[6].add(jcb2);
		jp[7].add(jl9);
		jp[7].add(jt4);
		jp[8].add(jl10);
		jp[8].add(jt5);
		jp[9].add(jladd);
		jp[9].add(jtadd);
		jp[10].add(jb1);
		jp[10].add(jb2);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		this.setLayout(new GridLayout(11,1));
		this.setSize(500,450);
		this.setTitle("注册");
		for(int i = 0;i < 11; i++)
			this.add(jp[i]);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		int i = 0;
		jt1.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jt1.getText()!=null&&!jt1.getText().equals(""))
		        jt1.setBackground(Color.WHITE);
		       else
		       {
		    	   jt1.setBackground(Color.PINK);
		       }
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		        jt1.setBackground(Color.WHITE);
		 
		    }
		});
		
		jpt1.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jpt1.getText()!=null&&!jpt1.getText().equals(""))
		        jpt1.setBackground(Color.WHITE);
		       else
		    	   jpt1.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		        jpt1.setBackground(Color.WHITE);
		 
		    }
		});
		jpt2.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jpt2.getText()!=null&&!jpt2.getText().equals(""))
		        jpt2.setBackground(Color.WHITE);
		       else
		    	   jpt2.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		        jpt2.setBackground(Color.WHITE);
		 
		    }
		});
		jt4.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jt4.getText()!=null&&!jt4.getText().equals(""))
		        jt4.setBackground(Color.WHITE);
		       else
		    	   jt4.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		        jt4.setBackground(Color.WHITE);
		 
		    }
		});
		jt5.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jt5.getText()!=null&&!jt5.getText().equals(""))
		        jt5.setBackground(Color.WHITE);
		       else
		    	   jt5.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		        jt5.setBackground(Color.WHITE);
		 
		    }
		});
		
		jmoney.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jmoney.getText()!=null&&!jmoney.getText().equals(""))
		    	   jmoney.setBackground(Color.WHITE);
		       else
		    	   jmoney.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		    	jmoney.setBackground(Color.WHITE);
		 
		    }
		});
		jtadd.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		       if(jtadd.getText()!=null&&!jtadd.getText().equals(""))
		    	   jtadd.setBackground(Color.WHITE);
		       else
		    	   jtadd.setBackground(Color.PINK);
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		    	jtadd.setBackground(Color.WHITE);
		 
		    }
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Register a = new Register("202150401");
	}
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==jb1)
		{
			db dbconn = new db();
			try {
				String name = jt1.getText();
				String account = (String)jcb1.getSelectedItem();
				String add = jtadd.getText();
				String pwd1 = jpt1.getText();
				String pwd2 = jpt2.getText();
				String tel = jt4.getText();
				String ID = jt5.getText();
				String sex;
				int kind = Integer.parseInt((String)jcb2.getSelectedItem());
				if(jrb1.isSelected())
					sex="男";
				else
					sex="女";
				float money = Float.parseFloat(jmoney.getText());
				if(pwd1.length()!=6)
				{
					JOptionPane.showMessageDialog(null,"请输入六位数密码！");
					return;
				}
				if(!pwd1.equals(pwd2))
				{
					JOptionPane.showMessageDialog(null,"两次密码不一致！");
					return;
				}
				if(money<1)
				{
					JOptionPane.showMessageDialog(null,"开户金额要大于一块！");
					return;
				}
				String UserProc = "{call Pro_UserInfo(?,?,?,?,?)}";
				String CardProc ="{call Pro_CardInfo(?,?,?,?,?,?)}";
				String StoreProc = "{call Pro_Store(?,?,?,?,?,?)}";
				CallableStatement User_pro = dbconn.AddUser(UserProc);
				User_pro.setString(1, ID);
				User_pro.setString(2, name);
				User_pro.setString(3, add);
				User_pro.setString(4, tel);
				User_pro.setString(5,sex);
				User_pro.execute();
				
				CallableStatement Card_Pro = dbconn.AddCard(CardProc);
				Card_Pro.setString(1, ID);
				Card_Pro.setString(2, A_ID);
				Card_Pro.setString(3, account);
				Card_Pro.setString(4, pwd1);
				Card_Pro.setFloat(5, money);
				Card_Pro.registerOutParameter(6, Types.VARCHAR);
				Card_Pro.execute();
				String msg = Card_Pro.getString(6);
				//System.out.println(ID+" "+A_ID+" "+account+" "+pwd1);
				//Card_Pro.execute();
				JOptionPane.showMessageDialog(null,msg);
				CallableStatement Store_pro = dbconn.Store(StoreProc);
				Store_pro.setString(1, ID);
				Store_pro.setString(2,A_ID);
				Store_pro.setString(3,account);
				Store_pro.setFloat(4,money);
				Store_pro.setInt(5,kind);
				Store_pro.registerOutParameter(6, Types.VARCHAR);
				Store_pro.execute();
				msg = Store_pro.getString(6);
				//Store_pro.execute();
				jt1.setText("");
				jtadd.setText("");
				jcb1.removeItem(account);
				jpt1.setText("");
				jpt2.setText("");
				jt4.setText("");
				jt5.setText("");
				jmoney.setText("");
			}catch (SQLException e) {
				System.out.println(e.toString());
			}

		}
		else
		{
			jt1.setText("");
			jtadd.setText("");
			jpt1.setText("");
			jpt2.setText("");
			jt4.setText("");
			jt5.setText("");
			jmoney.setText("");
			
		}
		
	}
	
}
