package model;

public abstract class Register {

	private RegisterType type;

	public Register() {

	}

	public abstract void setTo(TableType tab, String value);

	public abstract String obtainTo(TableType tab);

	public RegisterType getType() {
		return type;
	}

	protected void setType(RegisterType type) {
		this.type = type;
	}

}
