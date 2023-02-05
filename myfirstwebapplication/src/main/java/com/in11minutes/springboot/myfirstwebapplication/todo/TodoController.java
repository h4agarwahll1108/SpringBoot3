package com.in11minutes.springboot.myfirstwebapplication.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	public TodoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	private TodoService todoservice;

	@RequestMapping("list-todo")
	public String listOfTodo(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoservice.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String shownewTodo(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addnewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todoservice.addingTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
		return "redirect:list-todo";
	}

	@RequestMapping("delet-todo")
	public String deletTodo(@RequestParam int id) {
		todoservice.deletById(id);
		return "redirect:list-todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showupdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoservice.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updatenewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoservice.updatingTodo(todo);
		return "redirect:list-todo";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
