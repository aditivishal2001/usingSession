package com.taskmanagerapi.taskmanagerapi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasksManager")
public class controller
{
    @GetMapping("/getAllTask")
    public void getAllTask(){

    }
    @PostMapping("/addTask/{id}/{task}/{completed}")
    public void addTask(@PathVariable int id,@PathVariable String task, @PathVariable boolean completed )
    {
        Service service = new Service();

        service.addingTask(id,task,completed);

    }
    @GetMapping("/deleteTask")
    public void deleteTask(){

    }
    @PostMapping("/isCompleteTask")
    public void isCompleteTask(){

    }

}
