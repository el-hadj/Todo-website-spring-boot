package com.project.todo.todoproject.service;

import com.project.todo.todoproject.model.Todo;
import com.project.todo.todoproject.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {


    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "elhadj",
                "learning AWS", LocalDate.now(), false));
        todos.add(new Todo(++todosCount, "elhadj",
                "learning Devops", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "elhadj",
                "learning Spring", LocalDate.now().plusYears(2), false));
    }



    public List<Todo> findByUsername(String username){
        return todos.stream().filter(
                todo -> todo.getUsername()
                        .equalsIgnoreCase(username)).toList();

    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteTodoById(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }

    public void updateTodo(Todo todo){
        deleteTodoById(todo.getId());
        todos.add(todo);

    }

    public Todo findById(int id) {

        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }
}
