
public class BuildingDirector{
    private BuildingBuilder buildingbuilder; 

    public void setBuildingBuilder(BuildingBuilder bb){
        buildingbuilder = bb; 
    } 

    public Building getBuilding(){
        return buildingbuilder.getBuilding(); 
    } 

    public void constructBuilding(){
        buildingbuilder.createNewBuiding(); 
        buildingbuilder.buildCost(); 
        buildingbuilder.buildNbHabitant();
        buildingbuilder.buildNbTravailleur();
        buildingbuilder.buildConso();
        buildingbuilder.buildProduction();
        buildingbuilder.setTemps();
    } 

} 