package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project){
        String sql = "INSERT INTO PROJECTS ("
                + "name,"
                + "description,"
                + "createdAt,"
                + "updateAt) VALUES (?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Cria um conexão com o banco
            connection = ConnectionFactory.getConnection();
            //Cria um PreparedStatement, classe usada para executar a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3,
                    new Date(project.getCreatedAt().getTime()));
            statement.setDate(4,
                    new Date(project.getUpdateAt().getTime()));
            //Executa a sql para inserção dos dados
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao salvar o projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Project project){
       String sql = "UPDATE PROJECTS SET "
                + "name = ?, "
                + "description = ?, "
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
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(
                    3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(
                    4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            //Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(
                "Erro ao atualizar o project " + e.getMessage(), e);
        } finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
    }
        
    public List<Project> getAll(){
        String sql = "SELECT * FROM PROJECTS";
        
        //Lista de tarefas que será devolvida quando a chamada do metodo acontecer
        List<Project> projects = new ArrayList<Project>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;
        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);           
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            //Enquanto houverem valores a serem percorridos no meu resulSet
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("cratedAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o project " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        // Lista de tarefas que foi criada e carregada do banco de dados
        return projects;
    }
    
    public void removeById(int idProject){
        String sql = "DELETE FROM PROJECTS WHERE ID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //Preparando a query
            statement = connection.prepareStatement(sql);
            //Setando os valores
            statement.setInt(1, idProject);
            //Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa ");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}
