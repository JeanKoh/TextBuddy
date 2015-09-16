import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	@Test
	public void testSort(){
		TextBuddy.clear();
		//contents is empty.
		assertEquals(0,TextBuddy.sortByAlpha());
		
		//adding into contents
		TextBuddy.add(" aaa");
		assertEquals("1. aaa",TextBuddy.display().get(0));
		assertEquals(1,TextBuddy.sortByAlpha());
	
		//checking first item in list
		assertEquals("1. aaa", TextBuddy.display().get(0));
		
		//changing contents
		TextBuddy.clear();
		TextBuddy.add(" bbb");
		TextBuddy.add(" aaa");
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
		TextBuddy.add(" aaa");
		TextBuddy.add(" bbb");
		assertEquals("aaa",TextBuddy.searchKeyword("aaa").get(0));
		assertEquals("bbb",TextBuddy.searchKeyword("bbb").get(0));
		
		//keyword is contained in content
		TextBuddy.clear();
		TextBuddy.add(" aabb");
		assertEquals("aabb",TextBuddy.searchKeyword("aa").get(0));
		
		//keyword appears more than once
		TextBuddy.add(" paaple");
		TextBuddy.add(" bbb");
		assertEquals("aabb", TextBuddy.searchKeyword("aa").get(0));
		assertEquals("paaple", TextBuddy.searchKeyword("aa").get(1));
		
		//keyword doesn't appear in database
		assertEquals(null,TextBuddy.searchKeyword("paple"));
	}

}
