package com.whyte.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.whyte.data.api.TodoService;
import com.whyte.data.api.TodoServiceStub;

class TodoBusinessImplStub {

	/*Below test uses a stub, the problem with stubs is the data never changes, we could pass
	 * different users into the stub eg Dummy1, Dummy2 etc and based on the user it returns different data
	 * however this could get complex quickly and the stub class could grow quite large. Also if new methods
	 * are ever added to the interface they would need to be implemented in stub also. Stubs can cause
	 * headaches from a maintenance perspective. Better in simple scenarios. */
	@Test
	public void testRetrieveTodoRelatedToSpringStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, todos.size());
	}

}
