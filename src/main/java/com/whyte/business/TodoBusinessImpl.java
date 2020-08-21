package com.whyte.business;

import java.util.ArrayList;
import java.util.List;

import com.whyte.data.api.TodoService;

//This class is SUT (System under Test)
public class TodoBusinessImpl {
	/*Dependency of SUT class but we do not have any implementation, imagine its an external interface or
	 * developed by some other team so to use this in a test we will have to stub or mock it. */
	private TodoService todoService;

	TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}
}
