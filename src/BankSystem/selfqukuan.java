package BankSystem;
import java.awt.*;
import javax.swing.*;

public class selfqukuan extends JFrame {
	private JLabel all,keyong,money;
	private JButton ok,cancel;
	private JTextField left,keyongleft,qukuan;
	private String a_id;
	public selfqukuan()
	{
		a_id = "00000000";
		all = new JLabel("��ǰ���:");
		keyong = new JLabel("������");
		money = new JLabel("ȡ���");
		left = new JTextField(20);
		keyongleft = new JTextField(20);
		qukuan = new JTextField(20);
		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");
		this.add(all);
		this.add(left);
		this.add(keyong);
		this.add(keyongleft);
		this.add(money);
		this.add(qukuan);
		this.add(ok);
		this.add(cancel);
		this.setLayout(new GridLayout(4, 2));
		this.setVisible(true);
		this.setSize(300, 180);
		left.setEnabled(false);
		keyongleft.setEnabled(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}
	
	public static void main(String[] arg)
	{
		new selfqukuan();
	}
	

}
