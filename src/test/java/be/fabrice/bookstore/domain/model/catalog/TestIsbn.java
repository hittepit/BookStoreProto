package be.fabrice.bookstore.domain.model.catalog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestIsbn {
	
	@DataProvider(name="correct Isbn numbers with 10 Or 13 digits separated by dashes, dpaces, slashes or nothing")
	public Object[][] generateCorrectIsbnNumbers(){
		return new Object[][]{
				{"9782736126650","9782736126650"},
				{"9-782736-126650","9782736126650"},
				{"9/782736/126650","9782736126650"},
				{"9 782736 126650","9782736126650"},
				{"384361072X","9783843610728"},
				{" 2-266-11156-6","9782266111560"},
				{" 3/8436/1072/X ","9783843610728"},
				{"4 8436 1072 0","9784843610725"}
		};
	}
	
	@DataProvider(name="incorrect isbn numbers")
	public Object[][] generateIncorrectIsbnNumbers(){
		return new Object[][]{
				{"9782736126654"},
				{"978-27361-26651"},
				{"1234567"},
				{"truly incorrect number"}
		};
	}
	
	@Test(dataProvider="correct Isbn numbers with 10 Or 13 digits separated by dashes, dpaces, slashes or nothing")
	public void correctIsbnNumbersMustBeValidWhenValidated(String number, String unused){
		assertTrue(Isbn.validate(number));
	}
	
	@Test(dataProvider="correct Isbn numbers with 10 Or 13 digits separated by dashes, dpaces, slashes or nothing")
	public void validIsbnNumbersMustBeStoredWithoutAnyNumberCharacterWhenIsbnValueObjectIsCreated(String isbnNumber, String expectedStoredValue){
		Isbn i = new Isbn(isbnNumber);
		assertEquals(i.getValue(),expectedStoredValue);
	}
	
	@Test(dataProvider="incorrect isbn numbers")
	public void incorrectIsbn13ValueMustNotBeValid(String incorrectNumber){
		assertFalse(Isbn.validate(incorrectNumber));
	}
	
	@Test(dataProvider="incorrect isbn numbers", expectedExceptions=IllegalArgumentException.class)
	public void isbnValueObjectCannotBeCreatedWithinvalidIsbn13(String incorrectNumber){
		new Isbn(incorrectNumber);
	}
}
