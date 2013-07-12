package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import be.fabrice.bookstore.domain.model.InconsistentModelCreation;

public class TestCategory {
	@Test
	public void testCreatedCategoryHasNameInitiliazedAsGiven(){
		Category c = new Category("test");
		assertEquals(c.getName(), "test");
	}
	
	@Test
	public void testCreatedCategoryHasNameInitiliazedAndTrimed(){
		Category c = new Category("   test 2   ");
		assertEquals(c.getName(),"test 2");
	}
	
	@Test
	public void testCategoryCreationFailIfNameNull(){
		try{
			new Category(null);
			fail("Cannot create category with null name");
		} catch(InconsistentModelCreation e) {
			
		}
	}
	
	@Test
	public void testCategoryCreationFailIfNameBlank(){
		try{
			new Category("   ");
			fail("Cannot create category with null name");
		} catch(InconsistentModelCreation e) {
			
		}
	}
}
