package entidades;

public abstract class Lutador {
    private String nome;
    private Integer vida;
    private Integer escudo;
    private Integer poderFisico;
    private Integer poderHabilidade;

    public void atacar(Lutador alvo){
        alvo.defender(poderFisico + poderHabilidade);
    }

    @Override
    public String toString() {
        return nome;
    }

    public void defender(Integer dano){
        Integer danoRecebido = dano - escudo;
        if (danoRecebido > 0){
            vida = vida - danoRecebido;
        }
    }

    public boolean isVivo(){
        return vida > 0;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getEscudo() {
        return escudo;
    }

    public void setEscudo(Integer escudo) {
        this.escudo = escudo;
    }

    public Integer getPoderFisico() {
        return poderFisico;
    }

    public void setPoderFisico(Integer poderFisico) {
        this.poderFisico = poderFisico;
    }

    public Integer getPoderHabilidade() {
        return poderHabilidade;
    }

    public void setPoderHabilidade(Integer poderHabilidade) {
        this.poderHabilidade = poderHabilidade;
    }
}
