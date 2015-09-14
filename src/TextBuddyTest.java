import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	@Test
	public void testSort(){
		//contents is empty.
		assertFalse(TextBuddy.sort());
		
		//adding into contents
		assertTrue(TextBuddy.add(" aaa"));
		assertTrue(TextBuddy.sort());
	
		//checking first item in list
		assertEquals("1. aaa", TextBuddy.display().get(0));
		
		//changing contents
		TextBuddy.clear();
		assertTrue(TextBuddy.add(" bbb"));
		assertTrue(TextBuddy.add(" aaa"));
		assertTrue(TextBuddy.sort());
		assertEquals("1. aaa", TextBuddy.display().get(0));
		assertEquals("2. bbb", TextBuddy.display().get(1));
	}

}
