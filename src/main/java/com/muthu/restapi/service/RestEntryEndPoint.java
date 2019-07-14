package com.muthu.restapi.service;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.muthu.restapi.domain.Todo;



@RestController
public class RestEntryEndPoint {
	
	@Autowired
	private HardCodedTodoService todoService;
	
	@GetMapping("users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}
	
	@GetMapping("users/{username}/todos/{id}")
	public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable long id) {

		Todo todo = todoService.deleteById(id);
        if(todo != null) {
        	return ResponseEntity.noContent().build();
        }
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodoById(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){		
		Todo todoUpdated = todoService.Save(todo);
		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	
	@PostMapping("users/{username}/todos")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo){
		Todo todoCreated = todoService.Save(todo);
		
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(todoCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
