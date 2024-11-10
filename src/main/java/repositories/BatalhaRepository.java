package repositories;

import database.DatabaseConnection;
import entidades.Batalha;
import entidades.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatalhaRepository {

    private  final PersonagemRepository personagemRepository = new PersonagemRepository();

    public void salvarBatalha(Batalha batalha) {
        // Comando SQL para inserir uma nova raça com os valores especificados
        String sql = "INSERT INTO batalhas (lutador1_id, lutador2_id, vencedor_id) VALUES (?, ?, ?)";

        // Tenta conectar ao banco de dados e preparar a execução do comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             // Prepara a instrução SQL e especifica que as chaves geradas serão retornadas
             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os valores dos parâmetros na instrução SQL
            stmt.setInt(1, batalha.getLutador1Id());
            stmt.setInt(2, batalha.getLutador2Id());
            stmt.setInt(3, batalha.getVencedorId());
            // Executa a inserção no banco de dados
            stmt.executeUpdate();

            // Obtém as chaves geradas pelo banco (neste caso, o ID da batalha inserida)
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Atribui o ID gerado à instância de batalha
                batalha.setId(generatedKeys.getInt(1));
            }

            // Mensagem de confirmação de que a raça foi salva com sucesso
            System.out.println("Batalha   salva com ID " + batalha.getId());

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }
    }

    // Método para buscar todas as raças no banco de dados
    public List<Batalha> buscarTodasBatalhas() {
        // Lista para armazenar as raças encontradas
        List<Batalha> batalhas = new ArrayList<>();
        // Comando SQL para selecionar todas as raças
        String sql = "SELECT * FROM batalhas";

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria objetos Raca para cada linha
            while (rs.next()) {
                Batalha batalha = new Batalha();
                batalha.setId(rs.getInt("id"));
                batalha.setLutador1(personagemRepository.buscarPersonagemPorId(rs.getInt("lutador1_id")));
                batalha.setLutador2(personagemRepository.buscarPersonagemPorId(rs.getInt("lutador2_id")));
                batalha.setVencedor(personagemRepository.buscarPersonagemPorId(rs.getInt("vencedor_id")));

                batalhas.add(batalha);
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a lista de raças encontradas
        return batalhas;
    }

    // Método para buscar uma raça específica pelo ID
    public Batalha buscarBatalhaPorId(int id) {
        // Comando SQL para selecionar a raça pelo ID
        String sql = "SELECT * FROM batalhas WHERE id = ?";

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define o valor do parâmetro na instrução SQL
            stmt.setInt(1, id);
            // Executa a consulta e obtém os resultados
            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se a raça foi encontrada
                if (rs.next()) {
                    Batalha batalha = new Batalha();
                    batalha.setId(rs.getInt("id"));
                    batalha.setLutador1(personagemRepository.buscarPersonagemPorId(rs.getInt("lutador1_id")));
                    batalha.setLutador2(personagemRepository.buscarPersonagemPorId(rs.getInt("lutador2_id")));
                    batalha.setVencedor(personagemRepository.buscarPersonagemPorId(rs.getInt("vencedor_id")));
                    return batalha;
                }
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a raça encontrada ou null se não existir
        return null;
    }

}
