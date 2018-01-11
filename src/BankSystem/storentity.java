package BankSystem;
import java.util.*;
public class storentity {
	private String U_ID,A_ID,C_ID,NAME;
	private Date DateTime;
	private float much,rate;
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	
	public String getA_ID() {
		return A_ID;
	}
	public void setA_ID(String a_ID) {
		A_ID = a_ID;
	}
	public String getC_ID() {
		return C_ID;
	}
	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}
	public Date getDateTime() {
		return DateTime;
	}
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}
	public float getMuch() {
		return much;
	}
	public void setMuch(float much) {
		this.much = much;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	private int kind;
}
