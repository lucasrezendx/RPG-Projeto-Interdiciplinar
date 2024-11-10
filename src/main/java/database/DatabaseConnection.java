package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Classe responsável por gerenciar a conexão com o banco de dados
public class DatabaseConnection {
    // URL do banco de dados, com endereço, porta e nome do banco
    private static final String URL = "jdbc:mysql://localhost:3306/";
    // Usuário para acessar o banco de dados
    private static final String USER = "root";
    // Senha para acessar o banco de dados
    private static final String PASSWORD = "32323815";
    // Variável para garantir que o banco seja inicializado apenas uma vez
    private static boolean databaseInitialized = false;

    // Método para conectar ao banco de dados e inicializar se necessário
    public static Connection conectar() throws SQLException {
        // Estabelece a conexão com o banco de dados usando as credenciais fornecida
        if (databaseInitialized){
           return DriverManager.getConnection(URL + "rpg", USER, PASSWORD);
        }

        Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);

        // Verifica se o banco já foi inicializado
            // Inicializa o banco de dados, executando o script SQL necessário
            inicializarBancoDeDados(conexao);
            // Marca o banco como inicializado
            databaseInitialized = true;

        // Retorna a conexão estabelecida
        return conexao;
    }

    // Método para inicializar o banco de dados, executando um script SQL
    private static void inicializarBancoDeDados(Connection conexao) {
        try (
            // Lê o arquivo SQL para criar as tabelas e estruturas do banco de dados
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                DatabaseConnection.class.getResourceAsStream("/create_database.sql")));
            // Cria um objeto Statement para executar os comandos SQL
            Statement statement = conexao.createStatement()) {

            // StringBuilder para armazenar os comandos SQL
            StringBuilder sql = new StringBuilder();
            String linha;
            // Lê o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                sql.append(linha).append("\n");
                // Executa o comando SQL quando encontra um ponto e vírgula (indicando o fim de um comando)
                if (linha.trim().endsWith(";")) {
                    statement.execute(sql.toString());
                    // Limpa o StringBuilder para o próximo comando
                    sql.setLength(0);
                }
            }
            // Mensagem de sucesso na inicialização do banco
            System.out.println("Banco de dados inicializado com sucesso.");

        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace e uma mensagem de erro
            e.printStackTrace();
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }

    // Método para desconectar do banco de dados
    public static void desconectar(Connection conexao) {
        try {
            // Verifica se a conexão não é nula e se ainda está aberta
            if (conexao != null && !conexao.isClosed()) {
                // Fecha a conexão
                conexao.close();
            }
        } catch (SQLException e) {
            // Em caso de erro ao fechar a conexão, imprime o stack trace
            e.printStackTrace();
        }
    }
}
