package com.whyte.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.whyte.data.api.TodoService;

//Unlike TodoBusinessImplStub here we use mockito mocking
class TodoBusinessImplMock {

	/*
	 * Mocking is creating objects that simulate real objects. Unlike Stubs, Mocks are created from code at 
	 * runtime hence improving performance. Stubs are hard to maintain and Mocks offer more functionality 
	 * than Stubs eg verify a method call. There are frameworks like Mockito which allow you to create
	 * mocks easily*/
	@Test
	public void testRetrieveTodoRelatedToSpringMock() {
		
		/*So instead of creating a stub for sample data we dynamically do it through code by creating a mock object 
		 * and saying what type of data we want back when we interact with the mock object in a certain way*/
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		//This is called dynamically stubbing a method
		when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
		
		/*Then we can run the test*/
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, todos.size());
	}
	
	//Same test as above accept we use a BBD style syntax to achieve results
	@Test
	public void usingMockito_UsingBDD() {
		TodoService todoService = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));//More expressive way of writing an assertEquals
	}
	
	//Example using verify to ensure methods fired correctly
	@Test
	public void letsTestDeleteNow() {

		//Create mock
		TodoService todoService = mock(TodoService.class);

		//Stub data to return
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//dynamic stub
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		//pass stub into SUT system under test
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		/*If this method executes how we expect it to anything with "Spring" should not be deleted (2 entries) and anything
		 * without the word "Spring" will be deleted (1 entry)*/
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Now we verify thats what happened, firstly the deleteToDo method should have fired for string without word "Spring"
		verify(todoService).deleteTodo("Learn to Dance");

		//It should not have fired for the other two strings containing word "Spring"
		verify(todoService, never()).deleteTodo("Learn Spring MVC");

		verify(todoService, never()).deleteTodo("Learn Spring");

		//And fired at least once for string without "Spring"
		verify(todoService, times(1)).deleteTodo("Learn to Dance");
		// atLeastOnce, atLeast
	}
	
	/*We can capture what arguments were passed to certain methods using ArgumentCaptor. Below we use it to show the
	 * last value removed was "Learn to Dance"*/
	@Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        //Using verify and argument capture we can check if the method executed and grab the argument that was passed into it.
        verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
	
	//Same as method above just using a BDD style of coding
	@Test   
    public void captureArgumentBDD() {  
      
	    //Declare an Argument Captor   
	    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);  
	      
	    //Given   
	    TodoService todoService = mock(TodoService.class);  
	      
	    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"); 
	    
	    //Same as using 'when'
	    given(todoService.retrieveTodos("dummy")).willReturn(allTodos);  
	      
	    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);  
	      
	    //When   
	    todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");  
	      
	    //Then   
	    then(todoService).should().deleteTodo(argumentCaptor.capture());  
	    assertThat(argumentCaptor.getValue(),is("Use Spring MVC"));   
    }  

}
