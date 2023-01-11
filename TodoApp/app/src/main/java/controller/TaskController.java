package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO TASKS (idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updateAt) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6,
                    new Date(task.getDeadline().getTime()));
            statement.setDate(7,
                    new Date(task.getCreatedAt().getTime()));
            statement.setDate(8,
                    new Date(task.getUpdateAt().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao salvar a tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {
        String sql = "UPDATE TASKS SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updateAt = ?, "
                + "WHERE id = ? ";
       
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //Preparando a query
            statement = connection.prepareStatement(sql);
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(
                    6, new Date(task.getDeadline().getTime()));
            statement.setDate(
                    7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(
                    8, new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            //Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao atualizar a tarefa " + e.getMessage(), e);
        } finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
    }

    public void removeById(int taskId){
        String sql = "DELETE FROM TASKS WHERE ID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //Preparando a query
            statement = connection.prepareStatement(sql);
            //Setando os valores
            statement.setInt(1, taskId);
            //Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa ");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {
        String sql = "SELECT * FROM TASKS WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //Lista de tarefas que será devolvida quando a chamada do metodo acontecer
        List<Task> tasks = new ArrayList<Task>();

        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            //Setando o valor que correspode ao filtro de busca
            statement.setInt(1, idProject);
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            //Enquanto houverem valores a serem percorridos no meu resulSet
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("cratedAt"));
                task.setUpdateAt(resultSet.getDate("updateAt"));

                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir a tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        // Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }

    public List<Task> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
