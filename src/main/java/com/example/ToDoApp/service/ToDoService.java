package com.example.ToDoApp.service;

import com.example.ToDoApp.repo.ToDoRepo;
import com.example.ToDoApp.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    ToDoRepo toDoRepo;
    public List<ToDo> getAllToDoItems(){
        ArrayList<ToDo> todoList = new ArrayList<>();
        toDoRepo.findAll().forEach(todo -> todoList.add(todo));
        return todoList;
    }

    public ToDo getToDoItemById(Long id){
        return toDoRepo.findById(id).get();
    }

    public boolean updateStatus(Long id){
        ToDo todo = getToDoItemById(id);
        todo.setStatus("completed");
        return saveOrUpdateToDoItem(todo);
    }

    public boolean saveOrUpdateToDoItem(ToDo todo){
        ToDo updatedObj = toDoRepo.save(todo);
        if(getToDoItemById(updatedObj.getId())!=null){
            return true;
        }
        return false;
    }

    public boolean deleteToDoItem(Long id){
        toDoRepo.deleteById(id);
        if(toDoRepo.findById(id).isEmpty()){
            return true;
        }
        return false;
    }

}
