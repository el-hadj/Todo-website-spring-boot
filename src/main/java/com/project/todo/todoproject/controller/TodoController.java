package com.project.todo.todoproject.controller;

import com.project.todo.todoproject.model.Todo;
import com.project.todo.todoproject.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@RequestMapping("/todo")
@SessionAttributes("name")
public class TodoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public String listAll(ModelMap modelMap){
        String username = getLoggedAuthenticated();
        List<Todo> todos = todoService.findByUsername(username);
        modelMap.put("todos", todos);
        return "listTodos";
    }

    private String getLoggedAuthenticated(){
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/add")
    public String showAddTodoPage(ModelMap modelMap){
        String username = modelMap.get("name").toString();
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add")
    public String addNewTodo(ModelMap modelMap,
                             @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String username = modelMap.get("name").toString();
        todoService.addTodo(
                username,todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:/todo";
    }

    @GetMapping("delete")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteTodoById(id);
        return "redirect:/todo";
    }

    @GetMapping("update")
    public String updateTodo(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute("todo", todo);
        //todoService.updateTodo(id);
        return "todo";
    }

    @PostMapping("/update")
    public String updateNewTodo(ModelMap modelMap,
                             @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String username = modelMap.get("name").toString();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:/todo";
    }


}
