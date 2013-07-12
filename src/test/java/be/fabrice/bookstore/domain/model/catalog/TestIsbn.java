package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import be.fabrice.bookstore.domain.model.InconsistentModelCreation;

public class TestIsbn {
	@Test
	public void testCorrectIsbn13AreValid(){
		assertTrue(Isbn.validate("9782736126650"));
	}
	
	@Test
	public void testCorrectIsbn13WithDashesAreValid(){
		assertTrue(Isbn.validate("9-782736-126650"));
	}
	
	@Test
	public void testCorrectIsbn13WithSlashesAreValid(){
		assertTrue(Isbn.validate("9/782736/126650"));
	}
	
	@Test
	public void testCorrectIsbn13WithSpacesAreValid(){
		assertTrue(Isbn.validate("9 782736 126650"));
	}
	
	@Test
	public void testCorrectIsbn10WithDashesAreValid(){
		assertTrue(Isbn.validate(" 2-266-11156-6"));
	}
	
	@Test
	public void testCorrectIsbn10WithSlashesAreValid(){
		assertTrue(Isbn.validate(" 3/8436/1072/X "));
	}
	
	
	@Test
	public void testCorrectIsbn10WithSpacesAreValid(){
		assertTrue(Isbn.validate("4 8436 1072 0"));
	}

	@Test
	public void testIncorrectCorrectIsbn13AreNotValid(){
		assertFalse(Isbn.validate("9782736126654"));
	}
	
	@Test
	public void testValidIsbn13IsStored(){
		Isbn i = new Isbn("9782736126650");
		assertEquals(i.getValue(),"9782736126650");
	}
	
	@Test
	public void testValidIsbn13WithDashIsStoredWithoutDash(){
		Isbn i = new Isbn("978-27361-26650");
		assertEquals(i.getValue(),"9782736126650");
	}
	
	@Test
	public void testValidIsbn10AreStoredAsIsbn13(){
		Isbn i = new Isbn("7-8436-1072-3");
		assertEquals(i.getValue(), "9787843610726");
	}
	
	@Test
	public void testInvalidIsbn13CreationThrowException(){
		try{
			new Isbn("978-27361-26651");
			fail();
		}catch(InconsistentModelCreation e){
			
		}
	}
	
	@Test
	public void testInvalidIsbn10CreationThrowException(){
		try{
			new Isbn("7-8436-1072-0");
			fail();
		}catch(InconsistentModelCreation e){
			
		}
	}
	
	@Test
	public void testInvalidIsbnxCreationThrowException(){
		try{
			new Isbn("1234567");
			fail();
		}catch(InconsistentModelCreation e){
			
		}
	}
}
