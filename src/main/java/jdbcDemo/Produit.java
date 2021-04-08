package jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Produit {

	private Long id;
	private String designation;
	private double prix;
	private int quantite;
	
	public void setId(Long id) {
		this.id = id;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public void setPrix(double prix) {
		this.prix = prix;
	}





	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public Long getId() {
		return id;
	}




	public String getDesignation() {
		return designation;
	}




	public double getPrix() {
		return prix;
	}




	public int getQuantite() {
		return quantite;
	}




	public List<Produit> produitsParMc(String mc) throws ClassNotFoundException, SQLException{
		//charger le pilote jdbc en memoire
		Class.forName("com.mysql.jdbc.Driver");
		//Etablir la connection avec la base de données
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cat?serverTimezone=UTC","root","root");
		//Ecrire la requete
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION like ?");
		ps.setString(1, mc);
		//Envoyer la requette vers le sgbd, retourner les resultat vers l'app dans un objet de type resultset qui contient un jeux d'enregistrements
		ResultSet rs = ps.executeQuery();
		List<Produit> produits = new ArrayList<Produit>();
		//parcourir le resultset ligne par ligne et faire le mapping
		while(rs.next()) {
			Produit p = new Produit();
			p.setId(rs.getLong("ID"));
			p.setDesignation(rs.getString("DESIGNATION"));
			p.setPrix(rs.getDouble("PRIX"));
			p.setQuantite(rs.getInt("QUANTITE"));
			produits.add(p);
		}
		return produits;
	}




	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + "]";
	}
}
