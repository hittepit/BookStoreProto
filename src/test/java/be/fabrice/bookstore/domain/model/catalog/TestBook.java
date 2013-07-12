package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import be.fabrice.bookstore.domain.model.InconsistentModelCreation;

public class TestBook {
	private Isbn isbn = new Isbn("9782736126650");
	
	@Test
	public void testNewlyCreatedBookMustHaveIsbnAndTitleAssigned(){
		Book b = new Book(isbn,"test");
		assertEquals(b.getIsbn(), isbn);
		assertEquals(b.getTitle(),"test");
	}
	
	@Test
	public void testTitleMustBeTrimed(){
		Book b = new Book(isbn,"   part one  ");
		assertEquals(b.getTitle(),"part one");
	}
	
	@Test 
	public void testIsbnCannotBeNull(){
		try{
			new Book(null,"title");
			fail();
		}catch(InconsistentModelCreation e){}
	}
	
	@Test
	public void testTitleCannotBeNull(){
		try{
			new Book(isbn,null);
			fail();
		}catch(InconsistentModelCreation e){}
	}
	
	@Test void testTitleCannotBeBlank(){
		try{
			new Book(isbn,"   ");
			fail();
		}catch(InconsistentModelCreation e){}
	}
	
	@Test
	public void testNewlyCreatedBookMustHaveZeroCategory(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		assertEquals(b.getCategories().size(),0);
	}
	
	@Test
	public void testAdditionOfCategoryWithDifferentNameMustIncreaseCatgoriesNumber(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		Category sf = new Category("SF");
		b.add(sf);
		assertEquals(b.getCategories().size(),1);
		Category romance = new Category("romance");
		b.add(romance);
		assertEquals(b.getCategories().size(),2);
		assertTrue(b.getCategories().contains(sf));
		assertTrue(b.getCategories().contains(romance));
	}
	
	@Test
	public void testAdditionOfCategoryWithSameNameMustNotIncreaseCatgoriesNumber(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		Category sf = new Category("SF");
		b.add(sf);
		Category romance = new Category("romance");
		b.add(romance);
		Category sf2 = new Category("SF");
		b.add(sf2);
		assertEquals(b.getCategories().size(),2);
	}
	
	@Test
	public void testRemoveOfExistingCategoryMustDecreaseCategoriesNumberByOne(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		Category sf = new Category("SF");
		b.add(sf);
		Category romance = new Category("romance");
		b.add(romance);
		Category sf2 = new Category("SF");
		b.remove(sf2);
		assertEquals(b.getCategories().size(),1);
		assertTrue(b.getCategories().contains(romance));
	}
	
	@Test
	public void testRemoveOfANonExistingCategoryMustLeaveCategoriesNumberUnchanged(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		Category sf = new Category("SF");
		b.add(sf);
		Category romance = new Category("romance");
		b.add(romance);
		Category computer = new Category("computer");
		b.remove(computer);
		assertEquals(b.getCategories().size(),2);
	}
	
	@Test
	public void testRemoveOfACategoryOnNewlyCreatedBookMustChangeNothing(){
		Book b = new Book(new Isbn("9782736126650"), "test");
		Category sf = new Category("SF");
		b.remove(sf);
		assertEquals(b.getCategories().size(),0);
	}
}
