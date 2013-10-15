package model;

public class Book {

	private int isbn;
	private int title;
	private int author;
	private int published;

	public Book() {
	}

	public void insertTo(TableType tab, Integer ref) {
		switch (tab) {
		case ISBN: {
			isbn = ref;
			break;
		}
		case AUTHOR: {
			author = ref;
			break;
		}
		case PUBLISHER: {
			published = ref;
			break;
		}
		case TITLE: {
			title = ref;
			break;
		}
		default: {
			break;
		}
		}
	}

	public Integer obtainTo(TableType tab) {
		switch (tab) {
		case AUTHOR: {
			return author;
		}
		case ISBN: {
			return isbn;
		}
		case TITLE: {
			return title;
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
