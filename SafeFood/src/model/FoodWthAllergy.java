package model;

public class FoodWthAllergy extends Food{
	protected String allergyStr;

	public FoodWthAllergy(Food fd, String allergyStr) {
		super(fd);
		this.allergyStr = allergyStr;
	}

	public String getAllergyStr() {
		return allergyStr;
	}

	public void setAllergyStr(String allergyStr) {
		this.allergyStr = allergyStr;
	}
}
