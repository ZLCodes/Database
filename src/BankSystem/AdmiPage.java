package BankSystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class AdmiPage extends JFrame implements ActionListener{
	private String a_id;
	private JButton jb1,jb2,jb3,jb4,jb5,jb6;
	private JComboBox jcb,jcb2;
	private String str[];
	public AdmiPage(String a_id)
	{
		this.a_id = a_id;
		JPanel jp[] = new JPanel[4];
		str = new String[3];
		str[0] = "开户";
		str[1] = "销户";
		str[2] = "挂失取消";
		String s[]= {"存款记录","取款记录","流水打印","转账记录"};
		jp[0]=new JPanel();
		jcb = new JComboBox(str);
		jcb2 = new JComboBox(s);
		jb1 = new JButton("执行");
		this.setLayout(new GridLayout(4,1));
		//this.add(jb1);
		//this.add(jcb);
		jp[0].add(jb1);
		jp[0].add(jcb);
		this.add(jp[0]);
		jb2 = new JButton("取款");
		jb3 = new JButton("存款");
		jp[1]=new JPanel();
		jp[1].add(jb2);
		jp[1].add(jb3);
		this.add(jp[1]);
		jb4 = new JButton("转账");
		jb5= new JButton("挂失");
		jp[2]=new JPanel();
		jp[2].add(jb4);
		jp[2].add(jb5);
		this.add(jp[2]);
		jb6= new JButton("查询");
		//jb6 = new JButton("流水");
		jp[3]=new JPanel();
		jp[3].add(jb6);
		jp[3].add(jcb2);
		for(int i=0;i<4;i++)
			jp[i].setLayout(new GridLayout(1,2));
		this.add(jp[3]);
		this.setSize(400,300);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("操作");
		jb1.addActionListener(this);
		jb5.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb2.addActionListener(this);
		jb6.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)
		{
			if(jcb.getSelectedIndex()==0)
				new Register(a_id);
			else if(jcb.getSelectedIndex()==1)
				new desview(1);
			else 
				new desview(0);
		}
		else if(e.getSource()==jb5)
			new desview(0);
		else if(e.getSource()==jb3)
			new Store(a_id);
		else if(e.getSource()==jb4)
			new zhuanzhang(a_id);
		else if(e.getSource()==jb2)
			new qukuan(a_id);
		else
		{
			new search(jcb2.getSelectedIndex());
		}
	}
	public static void main(String[] arg)
	{
		new AdmiPage("202150401");
	}
	


}
