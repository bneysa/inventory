package showroom;

public class Product {

	private int id;
	private String name;
	private float price;
	private String addDate;
	private int quantity;
	
	public Product(int id, String name, float price, String addDate,int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.addDate = addDate;
		this.quantity =quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
}
