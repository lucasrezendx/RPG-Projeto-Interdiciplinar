package rpg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DatabaseConnection;
import entidades.Batalha;
import entidades.Classe;
import entidades.Personagem;
import entidades.Raca;
import repositories.BatalhaRepository;
import repositories.ClasseRepository;
import repositories.PersonagemRepository;
import repositories.RacaRepository;

public class Main {
	public static void main(String[] args) throws SQLException {

		RacaRepository racaRepo = new RacaRepository();
		ClasseRepository classeRepo = new ClasseRepository();
		PersonagemRepository personagemRepo = new PersonagemRepository();
		BatalhaRepository batalhaRepo = new BatalhaRepository();


		Raca elfo = new Raca("Elfo", 10, 5, 2, 8);
		racaRepo.salvarRaca(elfo);
		Classe guerreiro = new Classe("Guerreiro", 20, 10, 7, 3);
		classeRepo.salvarClasse(guerreiro);
		Personagem legolas = new Personagem("Legolas", 100, 50, 100, 10, elfo, guerreiro);
		personagemRepo.salvarPersonagem(legolas);
		Personagem grom = new Personagem("Grom", 120, 80, 20, 5, elfo, guerreiro);
		personagemRepo.salvarPersonagem(grom);


		Batalha batalha = new Batalha(legolas, grom);
		batalha.iniciar();
		batalhaRepo.salvarBatalha(batalha);
		System.out.println("Resultado da batalha: " + batalha);


		System.out.println("Raças salvas: " + racaRepo.buscarTodasRacas());
		//System.out.println("Personagens salvos: " + personagemRepo.buscarTodosPersonagens());
		System.out.println("Batalhas registradas: " + batalhaRepo.buscarTodasBatalhas());
	}
}