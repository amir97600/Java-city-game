public class Worker extends People{

    private int consoR;

    public Worker(Building logement){
        super(logement);
        this.consoR = 1;
    }

    public void travailler(Resource material){
        material.rmQT(this.consoR);
    }

}