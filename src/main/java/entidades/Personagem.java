package entidades;

public class Personagem extends Lutador {

	public Integer id;
	public Raca raca;
	public Classe classe;

	public Personagem(String nome, Integer vida, Integer escudo, Integer poderFisico, Integer poderHabilidade, Raca raca, Classe classe) {
		super.setNome(nome);
		super.setVida(vida + classe.getBonusVida() + raca.getBonusVida());
		super.setEscudo(escudo + classe.getBonusEscudo() + raca.getBonusEscudo());
		super.setPoderFisico(poderFisico + classe.getBonusPoderFisico() + raca.getBonusPoderFisico());
		super.setPoderHabilidade(poderHabilidade + classe.getBonusPoderHabilidade() + raca.getBonusPoderHabilidade());
		this.raca = raca;
		this.classe = classe;

	}

	public Personagem() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Integer getRacaId() {
		return raca.getId();
	}

	public Integer getClasseId(){
		return classe.getId();
	}

}