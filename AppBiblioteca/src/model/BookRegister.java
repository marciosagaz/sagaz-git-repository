/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author marcio
 */
public class BookRegister extends Register {

	private String isbn;
	private String title;
	private String author;
	private String published;

	public BookRegister() {
		setType(RegisterType.BOOK);
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
		case AUTHOR: {
			author = value;
			break;
		}
		case PUBLISHER: {
			published = value;
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
		case AUTHOR: {
			return author;
		}
		case PUBLISHER: {
			return published;
		}
		default: {
			return null;
		}
		}
	}
}
