package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestCategory {
	@Test
	public void categoryMustHaveTheGivenNameWhenCreated(){
		Category c = new Category("test");
		assertEquals(c.getName(), "test");
	}
	
	@Test
	public void nameOfTheCategoryMustBeTrimmedWhenCategoryIsCreated(){
		Category c = new Category("   test 2   ");
		assertEquals(c.getName(),"test 2");
	}
	
	@Test (expectedExceptions=IllegalArgumentException.class)
	public void nameOfACategoryCannotBeNull(){
		new Category(null);
	}
	
	@Test (expectedExceptions=IllegalArgumentException.class)
	public void nameOfACategoryCannotBeEmpty(){
		new Category("   ");
	}
}
