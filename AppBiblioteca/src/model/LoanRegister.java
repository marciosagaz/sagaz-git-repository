/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author marcio
 */
public class LoanRegister extends Register {

	private String isbn;
	private String title;

	public LoanRegister() {
		setType(RegisterType.LOAN);
	}

	@Override
	public void setTo(TableType tab, String value) {
		switch (tab) {
		case ISBN: {
			isbn = value;
			break;
		}
		case TITLE: {
			title = value;
			break;
		}
		default: {
			break;
		}
		}
	}

	@Override
	public String obtainTo(TableType tab) {
		switch (tab) {
		case ISBN: {
			return isbn;
		}
		case TITLE: {
			return title;
		}
		default: {
			return null;
		}
		}
	}
}
