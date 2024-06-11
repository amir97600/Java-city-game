abstract class BuildingBuilder{
    protected Building building; 

    public Building getBuilding(){
        return building; 
    } 

    public void createNewBuiding(){
        building = new Building(); 
    } 

    

    public abstract void buildCost(); 
    public abstract void buildNbHabitant(); 
    public abstract void buildNbTravailleur(); 
    public abstract void buildConso(); 
    public abstract void buildProduction(); 
    public abstract void  setTemps();

} 