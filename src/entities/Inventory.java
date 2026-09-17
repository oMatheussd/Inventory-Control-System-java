package entities;

import java.util.ArrayList;
import java.util.List;

import services.InventoryException;

public class Inventory {

	private List<Product> products = new ArrayList<>();

	public Inventory() {
	}

	public List<Product> getProducts() {
		return products;
	}

	public void registerProduct(Product product) {
		products.add(product);
	}

	public void addProductFromInventory(int quantity, long id) {
		Product product = findById(id);
		int newQuantity = product.getQuantity() + quantity;
		product.setQuantity(newQuantity);
	}

	public void removeProductFromInventory(int quantity, long id) {
		Product product = findById(id);
		if (quantity > product.getQuantity()) {
			throw new InventoryException("A quantidade informada é maior que a do estoque atual");
		}
		int newQuantity = product.getQuantity() - quantity;
		product.setQuantity(newQuantity);
	}

	public void showProducts() {
		for (Product p : products) {
			System.out.println(p);
		}
	}

	public Product findById(long id) {
		for (Product p : products) {
			if (id == p.getId()) {
				return p;
			}
		}
		return null;
	}
}
