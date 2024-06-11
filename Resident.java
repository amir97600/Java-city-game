
public class Resident extends People{

    public Resident(Building logement){
        super(logement);
    }

    public void manger(Resource food){
        food.rmQT(1);
    }
}