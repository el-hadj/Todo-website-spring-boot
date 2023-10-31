package com.project.todo.todoproject.controller;

import com.project.todo.todoproject.model.Todo;
import com.project.todo.todoproject.repository.TodoRepository;
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

@Controller
@RequestMapping("/todo")
@SessionAttributes("name")
public class TodoJPAController {

    private final TodoRepository todoRepository;

    public TodoJPAController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    private  TodoService todoService;


    @GetMapping
    public String listAll(ModelMap modelMap){
        String username = getLoggedAuthenticated();
        List<Todo> todos = todoRepository.findByUsername(username);
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
        todo.setId(1006);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:/todo";
    }

    @GetMapping("delete")
    public String deleteTodo(@RequestParam int id){
        todoRepository.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("update")
    public String updateTodo(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoRepository.findById(id).get();
        modelMap.addAttribute("todo", todo);
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
        todoRepository.save(todo);
        return "redirect:/todo";
    }


}
