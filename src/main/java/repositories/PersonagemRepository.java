package repositories;
import database.DatabaseConnection;
import entidades.Personagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por acessar e manipular os dados da entidade Raca no banco de dados
public class PersonagemRepository {

    private final ClasseRepository classeRepository = new ClasseRepository();
    private final RacaRepository racaRepository = new RacaRepository();

    // Método para salvar uma instância de Raca no banco de dados
    public void salvarPersonagem(Personagem personagem) {
        // Comando SQL para inserir uma nova raça com os valores especificados
        String sql = "INSERT INTO personagens (nome, vida, escudo, poder_fisico, poder_habilidade, raca_id, classe_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Tenta conectar ao banco de dados e preparar a execução do comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             // Prepara a instrução SQL e especifica que as chaves geradas serão retornadas
             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os valores dos parâmetros na instrução SQL
            stmt.setString(1, personagem.getNome());
            stmt.setInt(2, personagem.getVida());
            stmt.setInt(3, personagem.getEscudo());
            stmt.setInt(4, personagem.getPoderFisico());
            stmt.setInt(5, personagem.getPoderHabilidade());
            stmt.setInt(6, personagem.getRacaId());
            stmt.setInt(7, personagem.getClasseId());
            // Executa a inserção no banco de dados
            stmt.executeUpdate();

            // Obtém as chaves geradas pelo banco (neste caso, o ID da raça inserida)
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Atribui o ID gerado à instância de Raca
                personagem.setId(generatedKeys.getInt(1));
            }

            // Mensagem de confirmação de que a personagem foi salva com sucesso
            System.out.println("Personagem " + personagem.getNome() + " salva com ID " + personagem.getId());

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }
    }

    // Método para buscar todas as raças no banco de dados
    public List<Personagem> buscarTodasPersonagens() {
        // Lista para armazenar as raças encontradas
        List<Personagem> personagens = new ArrayList<>();
        // Comando SQL para selecionar todas as raças
        String sql = "SELECT * FROM personagens";

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria objetos Raca para cada linha
            while (rs.next()) {
                Personagem personagem = new Personagem();
                personagem.setId(rs.getInt("id"));
                personagem.setNome(rs.getString("nome"));
                personagem.setVida(rs.getInt("vida"));
                personagem.setEscudo(rs.getInt("escudo"));
                personagem.setPoderFisico(rs.getInt("poder_fisico"));
                personagem.setPoderHabilidade(rs.getInt("poder_habilidade"));
                personagem.setRaca(racaRepository.buscarRacaPorId(rs.getInt("raca_id")));
                personagem.setClasse(classeRepository.buscarClassePorId(rs.getInt("classe_id")));
                // Adiciona a raça à lista
                personagens.add(personagem);
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a lista de raças encontradas
        return personagens;
    }

    // Método para buscar uma personagem específica pelo ID
    public Personagem buscarPersonagemPorId(int id) {
        // Comando SQL para selecionar a raça pelo ID
        String sql = "SELECT * FROM personagens WHERE id = ?";
        Personagem personagem = null;

        // Tenta conectar ao banco de dados e executar o comando SQL
        try (Connection conexao = DatabaseConnection.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define o valor do parâmetro na instrução SQL
            stmt.setInt(1, id);
            // Executa a consulta e obtém os resultados
            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se a raça foi encontrada
                if (rs.next()) {
                    personagem = new Personagem();
                    personagem.setId(rs.getInt("id"));
                    personagem.setNome(rs.getString("nome"));
                    personagem.setVida(rs.getInt("vida"));
                    personagem.setEscudo(rs.getInt("escudo"));
                    personagem.setPoderFisico(rs.getInt("poder_fisico"));
                    personagem.setPoderHabilidade(rs.getInt("poder_habilidade"));
                    personagem.setRaca(racaRepository.buscarRacaPorId(rs.getInt("raca_id")));
                    personagem.setClasse(classeRepository.buscarClassePorId(rs.getInt("classe_id")));
                }
            }

        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace para facilitar a identificação do problema
            e.printStackTrace();
        }

        // Retorna a raça encontrada ou null se não existir
        return personagem;
    }
}