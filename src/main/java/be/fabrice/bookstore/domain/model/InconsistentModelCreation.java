package be.fabrice.bookstore.domain.model;

public class InconsistentModelCreation extends RuntimeException {
	public InconsistentModelCreation(String msg){
		super(msg);
	}
}
