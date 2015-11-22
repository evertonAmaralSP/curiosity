package br.com.curiosity.model;

public class ProbesResult {

	private String statusProbe1;
	private String statusProbe2;

	public ProbesResult(String statusProbe1, String statusProbe2) {
		this.statusProbe1 = statusProbe1;
		this.statusProbe2 = statusProbe2;
		
	}

	public String getStatusProbe1() {
		return statusProbe1;
	}

	public void setStatusProbe1(String statusProbe1) {
		this.statusProbe1 = statusProbe1;
	}

	public String getStatusProbe2() {
		return statusProbe2;
	}

	public void setStatusProbe2(String statusProbe2) {
		this.statusProbe2 = statusProbe2;
	}
	
	

}
