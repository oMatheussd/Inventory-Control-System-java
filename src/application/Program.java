package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Inventory;
import entities.Product;
import services.InventoryException;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Inventory inventory = new Inventory();
		int response;

		inventory.registerProduct(new Product("Teclado", 12.50, 12));
		inventory.registerProduct(new Product("Notebook", 3250.99, 41));
		inventory.registerProduct(new Product("Monitor", 720.10, 5));

		do {

			System.out.println("=================================");
			System.out.println("       CONTROLE DE ESTOQUE       ");
			System.out.println("=================================");
			System.out.println();

			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - entrada de estoque");
			System.out.println("4 - Saida de estoque");
			System.out.println("5 - Consultar produto");
			System.out.println("0 - Sair");
			System.out.println();

			System.out.print("Escolha uma opção: ");
			response = sc.nextInt();
			sc.nextLine();

			switch (response) {

			case 1:

				System.out.println();
				System.out.print("Nome: ");
				String name = sc.nextLine();
				System.out.print("Preço: ");
				double price = sc.nextDouble();
				System.out.print("Quantidade inicial: ");
				int quantity = sc.nextInt();
				sc.nextLine();

				inventory.registerProduct(new Product(name, price, quantity));
				System.out.println("");
				System.out.print("Produto cadastrado!");
				System.out.println("");
				sc.nextLine();

				break;
			case 2:

				System.out.println();
				System.out.println("PRODUTOS: ");
				inventory.showProducts();
				sc.nextLine();

				break;

			case 3:

				try {
					System.out.println();
					System.out.print("ID do produto: ");
					long id = sc.nextLong();
					sc.nextLine();
					Product product = inventory.findById(id);

					if (product == null) {
						throw new InventoryException("Produto não encontrado!");
					}

					System.out.print("Quantidade de entrada: ");
					int productQuantity = sc.nextInt();
					sc.nextLine();
					inventory.addProductFromInventory(productQuantity, id);

					System.out.println();
					System.out.println("Estoque atualizado!");
					System.out.println();

					sc.nextLine();
				} catch (InventoryException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
					sc.nextLine();
				}
				break;

			case 4:
				try {
					System.out.println();
					System.out.print("ID do produto: ");
					long id = sc.nextLong();
					sc.nextLine();
					Product product = inventory.findById(id);

					if (product == null) {
						throw new InventoryException("Produto não encontrado!");
					}

					System.out.print("Quantidade de saida: ");
					int productQuantity = sc.nextInt();
					sc.nextLine();
					inventory.removeProductFromInventory(productQuantity, id);

					System.out.println();
					System.out.println("Estoque atualizado!");
					System.out.println();

					sc.nextLine();
				} catch (InventoryException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
					sc.nextLine();
				}
				break;

			case 5:

				try {
					System.out.println();
					System.out.print("ID: ");
					long id = sc.nextLong();
					sc.nextLine();
					Product product = inventory.findById(id);
					if (product == null) {
						throw new InventoryException("Produto não encontrado!");
					}
					System.out.println(product);
					sc.nextLine();
				} catch (InventoryException e) {
					System.out.println();
					System.out.println(e.getMessage());
					System.out.println();
					sc.nextLine();
				}
				break;

			case 0:

				System.out.println("Saindo...");

				break;

			default:

				break;
			}
		} while (response != 0);

		sc.close();
	}
}