package com.whyte.business;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.hamcrest.MatcherAssert.assertThat;

import com.whyte.data.api.TodoService;

/*There are other ways of adding things like mocks, argument captures and more, this is done via annotations.
 * The below class is a rewrite of TodoBusinessImplMock.java. The @ExtendWith annotation is an extension
 * class allowing Mockito annotations to be used with JUnit5. There are a few ways you can do with JUnit4 
 * outlined in link inside this comment however these dont work for JUnit5 as it doesnt support rules and 
 * runners. The belwo links are how to do this in JUnit5, JUnit4 and why JUnit 4 approach wont work here
 * https://www.baeldung.com/mockito-annotations
 * https://www.baeldung.com/mockito-junit-5-extension
 * https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step11.md
 * https://github.com/in28minutes/MockitoTutorialForBeginners/blob/master/Step10.md
 * https://stackoverflow.com/questions/40961057/how-to-use-mockito-with-junit5#:~:text=No%20Rules%2C%20No%20Runners,runner%20can%20not%20be%20used.&text=It%20allows%20you%20to%20write,import%20org.*/
@ExtendWith(MockitoExtension.class)
class TodoBusinessImplMockitoInjectMocksTest {

	@Mock
	TodoService todoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingMockito() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}

	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));
	}

	@Test
	public void letsTestDeleteNow() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		verify(todoService).deleteTodo("Learn to Dance");

		verify(todoService, never()).deleteTodo("Learn Spring MVC");

		//You could use Mockito.never() and import org.Mockito instead of static import
		verify(todoService, never()).deleteTodo("Learn Spring");

		verify(todoService, times(1)).deleteTodo("Learn to Dance");
		// atLeastOnce, atLeast

	}

	@Test
	public void captureArgument() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		verify(todoService).deleteTodo(stringArgumentCaptor.capture());

		assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
	}

}
