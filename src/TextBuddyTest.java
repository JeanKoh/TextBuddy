import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	public static ArrayList<String> CONTENTS = new ArrayList<String>();
	@Test
	public void testSort(){
		CONTENTS.clear();
		//CONTENTS is empty.
		assertFalse(TextBuddy.sort());
		
		//adding into CONTENTS
		assertTrue(TextBuddy.add("aaa"));
		// when item is inserted alphabetically 
		assertTrue(TextBuddy.sort());
		
	}

}
