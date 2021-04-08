package jdbcDemo;

import java.sql.SQLException;
import java.util.List;

public class Pres {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Produit p = new Produit();
		System.out.println("hello");
		List<Produit> produits =  p.produitsParMc("%p%");
		produits.forEach(produit->{
			System.out.println(produit);

		});
	}

}
