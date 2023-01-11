package TodoApp;

import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

public class Main {
    
    public static void main(String[] args) {
     
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);
        

//        ProjectController projectController = new ProjectController();
//        Project project = new Project();
//        project.setId(12);
//        project.setName("Novo nome do projeto");
//        project.setDescription("description");       
//       
//        projectController.update(project);
//        
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos: " + projects.size());
//        
//        projectController.removeById(12);
        
//        TaskController taskController = new TaskController();
//        
//        Task task = new Task();
//        task.setIdProject(2);
//        task.setName("Criar as telas da aplicação");
//        task.setDescription("Devem ser criadas telas para os cadastros");
//        task.setNotes("sem notas");
//        task.setIsCompleted(false);
//        task.setDeadline(new Date());
//        
//        taskController.save(task);
//        
//        task.setName("Alterar telas da aplicação");
//        taskController.update(task);
//        List<Task> tasks = taskController.getAll(12);
//        System.out.println("Total de tarefas:" + tasks.size());
        
    }
}