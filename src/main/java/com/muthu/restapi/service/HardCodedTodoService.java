/**
 * 
 */
package com.muthu.restapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.muthu.restapi.domain.Todo;

/**
 * @author Muthu
 *
 */
@Service
public class HardCodedTodoService {

	public static List<Todo> todos = new ArrayList<>();

	private static int counter = 0;

	static {

		todos.add(new Todo(++counter, "Muthukumar", "Learn to dance", new Date(), false));
		todos.add(new Todo(++counter, "Muthukumar", "Learn to Ms", new Date(), false));
		todos.add(new Todo(++counter, "Muthukumar", "Learn to Angualr", new Date(), false));
		todos.add(new Todo(++counter, "Muthukumar", "Learn to fullstack", new Date(), false));
	}

	public List<Todo> findAll() {
		return todos;
	}

	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null) {
			return null;
		}
		
		if(todos.remove(todo)) {
			return todo;
		}else{
			return null;
		}
	}

	public Todo Save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++counter);
			todos.add(todo);
		}
		else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	
	public Todo findById(long id) {
		// TODO Auto-generated method stub
		for (Todo todo : todos) {

			if (todo.getId() == id) {
				return todo;
			}

		}
		return null;
	}
}
