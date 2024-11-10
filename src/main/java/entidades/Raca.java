package entidades;

public class Raca {
    private Integer id;
    private String nome;
    private Integer bonusVida;
    private Integer bonusEscudo;
    private Integer bonusPoderFisico;
    private Integer bonusPoderHabilidade;


    public Raca(String nome, Integer bonusVida, Integer bonusEscudo, Integer bonusPoderFisico, Integer bonusPoderHabilidade) {
        this.nome = nome;
        this.bonusVida = bonusVida;
        this.bonusEscudo = bonusEscudo;
        this.bonusPoderFisico = bonusPoderFisico;
        this.bonusPoderHabilidade = bonusPoderHabilidade;
    }
    
    public Raca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getBonusVida() {
        return bonusVida;
    }

    public void setBonusVida(Integer bonusVida) {
        this.bonusVida = bonusVida;
    }

    public Integer getBonusEscudo() {
        return bonusEscudo;
    }

    public void setBonusEscudo(Integer bonusEscudo) {
        this.bonusEscudo = bonusEscudo;
    }

    public Integer getBonusPoderFisico() {
        return bonusPoderFisico;
    }

    public void setBonusPoderFisico(Integer bonusPoderFisico) {
        this.bonusPoderFisico = bonusPoderFisico;
    }

    public Integer getBonusPoderHabilidade() {
        return bonusPoderHabilidade;
    }

    public void setBonusPoderHabilidade(Integer bonusPoderHabilidade) {
        this.bonusPoderHabilidade = bonusPoderHabilidade;
    }
}
