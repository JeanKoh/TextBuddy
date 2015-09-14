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
	
	@Test
	public void testSearchKeyword(){
		TextBuddy.clear();
		//empty contents
		assertEquals(null,TextBuddy.searchKeyword("aaa"));
		
		//empty string
		assertEquals(null,TextBuddy.searchKeyword(null));
		
		//keyword is equal to content
		assertTrue(TextBuddy.add(" aaa"));
		assertTrue(TextBuddy.add(" bbb"));
		assertEquals("aaa",TextBuddy.searchKeyword("aaa").get(0));
		assertEquals("bbb",TextBuddy.searchKeyword("bbb").get(0));
		
		//keyword is contained in content
		TextBuddy.clear();
		assertTrue(TextBuddy.add(" aabb"));
		assertEquals("aabb",TextBuddy.searchKeyword("aa"));
	}

}
