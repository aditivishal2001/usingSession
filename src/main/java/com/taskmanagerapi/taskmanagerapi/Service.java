package com.taskmanagerapi.taskmanagerapi;

import entity.TaskList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.model.ast.builder.TableDeleteBuilderSkipped;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

public class Service
{
    private EntityManager entityManager;

    public static void main(String[] args) {
        Service service = new Service();

        //service.addingTask(14,"working leetcode", false);//working
        //service.gettingAllTask();//working
        service.updateTaskForCompletion();

        int i =0;
    }
    //  /getAllTask
    public List<TaskList> gettingAllTask()
    {
        List<TaskList> listOfTasks= new ArrayList<>();

       // return session.createQuery("SELECT * FROM task_list", TaskList.class).getResultList();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
//            String query = "SELECT * FROM task_list";
//            entityManager.createNamedQuery(query);

            Query countEmpByDept = (Query) entityManager.createNativeQuery("SELECT * FROM task_list");
            //countEmpByDept.setParameter("deptName", "Java Advocacy");
            listOfTasks = countEmpByDept.getResultList();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        for (int i = 0; i < listOfTasks.size(); i++) {

            // Print all elements of List
            System.out.println(listOfTasks.get(i));
        }

        return listOfTasks;

    }
    //  /addTask
    public void addingTask( int id, String task, boolean isComplete) //working
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TaskList tl = new TaskList();
            tl.setTask(task);
            tl.setId(id);
            tl.setCompleted(isComplete);

            //entityManager.persist(tl);
            entityManager.merge(tl);

            transaction.commit();


        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
    //  /deleteTask
    public void deleteingTask()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();



            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
    //  /isCompleteTask")
    public void updateTaskForCompletion()
    {

        SessionFactory factory;
        factory  = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;


        tx = session.beginTransaction();

        Task t1 = (Task)session.get(Task.class, 4);

        t1.setTask("test Hibernate");

        session.update(t1);

        tx.commit();

        session.close();



        //Query updating = (Query) entityManager.createNativeQuery("UPDATE task_list SET completed=true WHERE id = taskId");

    }
}
