package be.fabrice.bookstore.domain.model.catalog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


public class Book {
	private Isbn isbn;
	private String title;
	private String author;
	private Set<Category> categories;
	private double price;
	
	/**
	 * Constructeur obligeant la création d'un livre valide (isbn et titre existant).
	 * Pas de constructeur sans paramètre défini pour le moment, mais ne devra pas casser la cohéence de la classe.
	 * L'isbn est immutable.
	 * @param name
	 */
	public Book(Isbn isbn, String title){
		if(isbn==null){
			throw new IllegalArgumentException("Isbn cannot be null");
		}
		if(StringUtils.isBlank(title)){
			throw new IllegalArgumentException("Title cannot be blank nor null: "+title);
		}
		this.isbn = isbn;
		this.title = title.trim();
	}
	
	/**
	 * Cette méthode retour un set immutable des {@link Category} auquel le livre appartient.<br />
	 * Pour ajouter ou retirer des {@link Category}, utiliser les méthodes add et remove.
	 * @return
	 */
	public Set<Category> getCategories() {
		return this.categories==null?Collections.EMPTY_SET:Collections.unmodifiableSet(this.categories);
	}
	
	public void add(Category category){
		if(this.categories==null){
			this.categories = new HashSet<Category>();
		}
		this.categories.add(category);
	}
	
	public void remove(Category category){
		if(this.categories!=null){
			this.categories.remove(category);
		}
	}

	public Isbn getIsbn() {
		return this.isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
}
