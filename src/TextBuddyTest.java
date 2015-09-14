import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	@Test
	public void testSort(){
		TextBuddy.clear();
		//contents is empty.
		assertFalse(TextBuddy.sortByAlpha());
		
		//adding into contents
		assertTrue(TextBuddy.add(" aaa"));
		assertTrue(TextBuddy.sortByAlpha());
	
		//checking first item in list
		assertEquals("1. aaa", TextBuddy.display().get(0));
		
		//changing contents
		TextBuddy.clear();
		assertTrue(TextBuddy.add(" bbb"));
		assertTrue(TextBuddy.add(" aaa"));
		assertTrue(TextBuddy.sortByAlpha());
		assertEquals("1. aaa", TextBuddy.display().get(0));
		assertEquals("2. bbb", TextBuddy.display().get(1));
	}
	
	public void testSearchKeyword(){
		TextBuddy.clear();
		//empty contents
		assertEquals("",TextBuddy.searchKeyword("aaa"));
	}

}
