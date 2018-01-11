package BankSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
public class search extends desview {
	private int temp;
	public search(int i)
	{
		super(0);
		temp = i;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==super.ok)
		{
			String ac = super.account.getText();
			String ID = super.id.getText();
			new StoreSelect(temp,ac,ID);
		}
		else 
			dispose();
	}
	public static void main(String[]arg)
	{
		new search(0);
	}
	
}
