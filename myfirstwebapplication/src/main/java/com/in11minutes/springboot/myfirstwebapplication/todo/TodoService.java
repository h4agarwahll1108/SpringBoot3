package com.in11minutes.springboot.myfirstwebapplication.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Service
public class TodoService 
{
	private static int todosCount=0;
	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(++todosCount, "Harshit560", "Learn Python cert", 
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "Harshit560", "Learn Java cert", 
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "Harshit560", "Learn C certificate", 
				LocalDate.now().plusYears(3), false));
	}
	public List<Todo> findByUsername(String username)
	{
		Predicate<? super Todo> predicate = todo ->todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList() ;
	}
	
	public void addingTodo(String username, String description, LocalDate targetdate, boolean done)
	{
		Todo todo = new Todo(++todosCount,username,description,targetdate,done);
		todos.add(todo);
	}
	
	public void deletById(int id)
	{
		Predicate<? super Todo> predicate = todo ->todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) 
	{
		Predicate<? super Todo> predicate = todo ->todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updatingTodo(@Valid Todo todo) 
	{
		deletById(todo.getId());
		todos.add(todo);
	}
}

