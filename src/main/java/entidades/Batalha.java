package entidades;

public class Batalha {
    private Integer id;
    private Personagem lutador1;
    private Personagem lutador2;
    private Personagem vencedor;

    public Batalha(Personagem lutador1, Personagem lutador2) {
        this.lutador1 = lutador1;
        this.lutador2 = lutador2;
    }

    public Batalha() {
    }

    public Personagem getLutador1() {
        return lutador1;
    }

    public void setLutador1(Personagem lutador1) {
        this.lutador1 = lutador1;
    }

    public Personagem getLutador2() {
        return lutador2;
    }

    public void setLutador2(Personagem lutador2) {
        this.lutador2 = lutador2;
    }

    public Personagem getVencedor() {
        return vencedor;
    }

    public void setVencedor(Personagem vencedor) {
        this.vencedor = vencedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLutador1Id() {
        return lutador1.getId();
    }


    public Integer getLutador2Id() {
       return lutador2.getId();
    }


    public Integer getVencedorId() {
        return vencedor.getId();
    }



    public void iniciar(){
        while (true){
            lutador1.atacar(lutador2);
            if (!lutador2.isVivo()){
                vencedor = lutador1;
                break;
            }

            lutador2.atacar(lutador1);
            if(!lutador1.isVivo()){
                vencedor = lutador2;
                break;
            }

        }



    }

    @Override
    public String toString() {
        return vencedor == null ? lutador1.toString() + " x " + lutador2.toString() : vencedor.toString();
    }
}
