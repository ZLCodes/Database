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
		all = new JLabel("当前余额:");
		keyong = new JLabel("活期余额：");
		money = new JLabel("取款金额：");
		left = new JTextField(20);
		keyongleft = new JTextField(20);
		qukuan = new JTextField(20);
		ok = new JButton("确认");
		cancel = new JButton("取消");
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
