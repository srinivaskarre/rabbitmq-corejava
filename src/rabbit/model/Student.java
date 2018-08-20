package rabbit.model;

import java.io.Serializable;

public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3799937382795228327L;

	private String name;
	
	private String colg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColg() {
		return colg;
	}

	public void setColg(String colg) {
		this.colg = colg;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", colg=" + colg + "]";
	}
}
