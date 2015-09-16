import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	@Test
	public void testSort(){
		TextBuddy.clear();
		//contents is empty.
		assertEquals(0,TextBuddy.sortByAlpha());
		
		//adding into contents
		assertEquals(1,TextBuddy.add(" aaa"));
		assertEquals(1,TextBuddy.sortByAlpha());
	
		//checking first item in list
		assertEquals("1. aaa", TextBuddy.display().get(0));
		
		//changing contents
		TextBuddy.clear();
		assertEquals(1,TextBuddy.add(" bbb"));
		assertEquals(1,TextBuddy.add(" aaa"));
		assertEquals(1,TextBuddy.sortByAlpha());
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
		assertEquals(1,TextBuddy.add(" aaa"));
		assertEquals(1,TextBuddy.add(" bbb"));
		assertEquals("aaa",TextBuddy.searchKeyword("aaa").get(0));
		assertEquals("bbb",TextBuddy.searchKeyword("bbb").get(0));
		
		//keyword is contained in content
		TextBuddy.clear();
		assertEquals(1,TextBuddy.add(" aabb"));
		assertEquals("aabb",TextBuddy.searchKeyword("aa").get(0));
		
		//keyword appears more than once
		assertEquals(1,TextBuddy.add(" paaple"));
		assertEquals(1,TextBuddy.add(" bbb"));
		assertEquals("aabb", TextBuddy.searchKeyword("aa").get(0));
		assertEquals("paaple", TextBuddy.searchKeyword("aa").get(1));
		
		//keyword doesn't appear in database
		assertEquals(null,TextBuddy.searchKeyword("paple"));
	}

}
