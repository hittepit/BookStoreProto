package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TestBook {
	private Isbn isbn = new Isbn("9782736126650");
	private Category sf = new Category("SF");
	private Category romance = new Category("romance");
	private Category computer = new Category("computer");
	
	@Test
	public void BookMustHaveIsbnAndTitleAssignedWhenCreated(){
		Book b = new Book(isbn,"test");
		assertEquals(b.getIsbn(), isbn);
		assertEquals(b.getTitle(),"test");
	}
	
	@Test
	public void titleMustBeTrimed(){
		Book b = new Book(isbn,"   part one  ");
		assertEquals(b.getTitle(),"part one");
	}
	
	@Test (expectedExceptions=IllegalArgumentException.class)
	public void isbnCannotBeNull(){
		new Book(null,"title");
	}
	
	@Test (expectedExceptions=IllegalArgumentException.class)
	public void titleCannotBeNull(){
		new Book(isbn,null);
	}
	
	@Test (expectedExceptions=IllegalArgumentException.class)
	void titleCannotBeBlank(){
		new Book(isbn,"   ");
	}
	
	@Test
	public void bookMustBelongToNoCategoryWhenCreated(){
		Book b = new Book(isbn, "test");
		assertEquals(b.getCategories().size(),0);
	}
	
	@Test
	public void bookMustBelongToNewAddedCategories(){
		Book b = new Book(isbn, "test");
		b.add(sf);
		b.add(romance);
		assertTrue(b.getCategories().contains(sf));
		assertTrue(b.getCategories().contains(romance));
	}
	
	@Test
	public void numberOfCategoriesMustNotIncreaseWhenAlreadyExistingCatgeoryIsAdded(){
		Book b = new Book(isbn, "test");
		b.add(sf);
		b.add(romance);
		Category sf2 = new Category("SF");
		b.add(sf2);
		assertEquals(b.getCategories().size(),2);
	}
	
	@Test
	public void numberOfCatgeoriesMustDecreaseByOneWhenExistingCategoryIsRemoved(){
		Book b = new Book(isbn, "test");
		b.add(sf);
		b.add(romance);
		Category sf2 = new Category("SF");
		b.remove(sf2);
		assertEquals(b.getCategories().size(),1);
		assertTrue(b.getCategories().contains(romance));
	}
	
	@Test
	public void numberOfCategoriesMustNotChangeWhenANonExistingCategoryIsRemoved(){
		Book b = new Book(isbn, "test");
		b.add(sf);
		b.add(romance);
		b.remove(computer);
		assertEquals(b.getCategories().size(),2);
	}
}
