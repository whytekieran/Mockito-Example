package com.whyte.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

//Some examples of mocking using the List interface Java
class ListTestMockito {

	//Here we simply mock list size() then test.
	@Test
	public void letsMockListSize() {
		//Mock list
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}
	
	//Here we simply mock list size() but get it to return multiple values instead of one, then test.
	@Test
	public void letsMockListSizeMultipleReturns() {
		//Mock list
		List list = mock(List.class);
		when(list.size()).thenReturn(10).thenReturn(20); //return 10 then 20
		assertEquals(10, list.size()); // First Call
		assertEquals(20, list.size()); // Second Call
	}

	//Here we mock the list get() method
	@Test
	public void letsMockListGet() {
		List<String> list = mock(List.class);
		when(list.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", list.get(0));
		assertNull(list.get(1)); //will return null if mock value wasnt specified
	}
	
	@Test
	public void letsMockListGetWithAnyIndex() {
		List<String> list = mock(List.class);
		/*Any int is an example of an argument matcher, argument matchers perform flexible dynamic stubbing by matching a wide
		 * range of conditions like below it matches all integers, more methods here:
		 * https://www.javatpoint.com/mockito-argument-matchers#:~:text=Argument%20matchers%20are%20mainly%20used,and%20matching%20of%20argument%20values.*/
		when(list.get(anyInt())).thenReturn("in28Minutes");
		// If you are using argument matchers, all arguments
		// have to be provided by matchers.
		assertEquals("in28Minutes", list.get(0));
		assertEquals("in28Minutes", list.get(1));
		
		//We could also check for exceptions
		when(list.get(anyInt())).thenThrow(new RuntimeException("Something happened"));
		//you could use an assertThrows or simply call the code to throw the exception like below
		//list.get(0);
		//then add (expected=RuntimeException.class) to the @Test annotation
	}
	
	//Same as above just using a BDD approach
	@Test
	public void bddAliases_UsingGivenWillReturn() {
		List<String> list = mock(List.class);

		//given
		given(list.get(anyInt())).willReturn("in28Minutes");

		//then
		assertThat("in28Minutes", is(list.get(0)));
		assertThat("in28Minutes", is(list.get(0)));
	}
}
