package com.whyte.data.api;

import java.util.Arrays;
import java.util.List;

/*A stub is just a sample implementation complete with dummy data which can be used during testing to
 * eliminate external dependencies.
*/
public class TodoServiceStub implements TodoService {

	@Override
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring",
				"Learn to Dance");
	}

	@Override
	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
	}

}
