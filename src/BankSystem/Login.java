package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class Login extends JFrame implements ActionListener{

	private JLabel jl1,jl2;
	private JTextField jtf;
	private JPasswordField jpf;
	private JButton jb1,jb2;
	private JPanel jp1,jp2,jp3;
	private JRadioButton jrb1,jrb2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 try {
	        	org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	            UIManager.put("RootPane.setupButtonVisible", false);
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
		Login a = new Login();		
		
	}
	public Login()
	{
		jl1 = new JLabel("ÕËºÅ:");
		jl2 = new JLabel("ÃÜÂë:");
		jtf = new JTextField(20);
		jpf = new JPasswordField(20);
		jb1 = new JButton("µÇÂ½");
		jb1.addActionListener(this);
		jb2 = new JButton("×¢²á");
		jb2.addActionListener(this);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jrb1 = new JRadioButton("¹ÜÀíÔ±");
		jrb2 = new JRadioButton("ÓÃ»§");
		jp1.add(jl1);
		jp1.add(jtf);
		jp2.add(jl2);
		jp2.add(jpf);
		//jp3.add(jrb1);
		//jp3.add(jrb2);
		jp3.add(jb1);
		jp3.add(jb2);
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);		
		this.setLayout(new GridLayout(3, 1));
		this.setSize(300,200);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setTitle("µÇÂ½");
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==jb1)
		{
			String user = jtf.getText();
			String Passwd = jpf.getText();
			db dbconn;
			try {
				dbconn = new db();
				String sql = "select * from AdminInfo where A_ID='"+user+"'and A_Passwd = '"+Passwd+"'";
				ResultSet rs = dbconn.executeQuery(sql);
				if(rs.next())
				{
					new AdmiPage(user);
					//this.setVisible(false);
					
					this.dispose();
					return;
				}
				sql = "select * from CardInfo where C_ID='"+user+"'and Passwd = '"+Passwd+"'";
				rs = dbconn.executeQuery(sql);
				if(rs.next())
				{
					new kehuView(user);
					//this.setVisible(false);
					this.dispose();
				}
				else
					JOptionPane.showMessageDialog(null,"ÃÜÂë»òÕËºÅ³ö´í£¬Çë¼ì²éºóµÇÂ½£¡");
				
			} 
			 catch (SQLException sqle) {
					System.out.println(sqle.toString());
				} catch (Exception a) {
					System.out.println(a.getMessage());
				}
			
		}
	}
}
