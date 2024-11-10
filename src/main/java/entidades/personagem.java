package entidades;

public class personagem extends Lutador {

	public Integer id;
	public String raca;
	public String classe;

		public Personagem(int id, String nome, int vida, int escudo, int poderFisico, int poderHabilidade, String raca, String arquetipo) {
			super(nome, vida, escudo, poderFisico, poderHabilidade);
			this.id = id;
			this.raca = raca;
			this.arquetipo = arquetipo;
    }