package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "task_list", schema = "Tasks", catalog = "")
public class TaskList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "task")
    private String task;
    @Basic
    @Column(name = "completed")
    private boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return id == taskList.id && Objects.equals(task, taskList.task) && Objects.equals(completed, taskList.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, completed);
    }
}
