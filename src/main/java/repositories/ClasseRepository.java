package repositories;
import database.DatabaseConnection;
import entidades.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por acessar e manipular os dados da entidade Raca no banco de dados
public class ClasseRepository {

    // Método para salvar uma instância de Raca no banco de dados
    public void salvarClasse(Classe classe) {
        // Comando SQL para inserir uma nova raça com os valores especificados
        String sql = "INSERT INTO classes (nome, bonus_vida, bonus_escudo, bonus_poder_fisico, bonus_poder_habilidade) VALUES (?, ?, ?, ?, ?)";

        // Tenta conectar ao banco de dados e preparar a execução do comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             // Prepara a instrução SQL e especifica que as chaves geradas serão retornadas
             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os valores dos parâmetros na instrução SQL
            stmt.setString(1, classe.getNome());
            stmt.setInt(2, classe.getBonusVida());
            stmt.setInt(3, classe.getBonusEscudo());
            stmt.setInt(4, classe.getBonusPoderFisico());
            stmt.setInt(5, classe.getBonusPoderHabilidade());
            // Executa a inserção no banco de dados
            stmt.executeUpdate();

            // Obtém as chaves geradas pelo banco (neste caso, o ID da raça inserida)
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Atribui o ID gerado à instância de Raca
                classe.setId(generatedKeys.getInt(1));
            }

            // Mensagem de confirmação de que a raça foi salva com sucesso
            System.out.println("Classe " + classe.getNome() + " salva com ID " + classe.getId());

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }
    }

    // Método para buscar todas as raças no banco de dados
    public List<Classe> buscarTodasClasses() {
        // Lista para armazenar as raças encontradas
        List<Classe> classes = new ArrayList<>();
        // Comando SQL para selecionar todas as raças
        String sql = "SELECT * FROM classes";

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria objetos Raca para cada linha
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNome(rs.getString("nome"));
                classe.setBonusVida(rs.getInt("bonus_vida"));
                classe.setBonusEscudo(rs.getInt("bonus_escudo"));
                classe.setBonusPoderFisico(rs.getInt("bonus_poder_fisico"));
                classe.setBonusPoderHabilidade(rs.getInt("bonus_poder_habilidade"));
                // Adiciona a raça à lista
                classes.add(classe);
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a lista de raças encontradas
        return classes;
    }

    // Método para buscar uma raça específica pelo ID
    public Classe buscarClassePorId(int id) {
        // Comando SQL para selecionar a raça pelo ID
        String sql = "SELECT * FROM classes WHERE id = ?";
        Classe classe = null;

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define o valor do parâmetro na instrução SQL
            stmt.setInt(1, id);
            // Executa a consulta e obtém os resultados
            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se a raça foi encontrada
                if (rs.next()) {
                    classe = new Classe();
                    classe.setId(rs.getInt("id"));
                    classe.setNome(rs.getString("nome"));
                    classe.setBonusVida(rs.getInt("bonus_vida"));
                    classe.setBonusEscudo(rs.getInt("bonus_escudo"));
                    classe.setBonusPoderFisico(rs.getInt("bonus_poder_fisico"));
                    classe.setBonusPoderHabilidade(rs.getInt("bonus_poder_habilidade"));
                }
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a raça encontrada ou null se não existir
        return classe;
    }
}