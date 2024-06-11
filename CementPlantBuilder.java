import java.util.*;
import java.io.*;

public class CementPlantBuilder extends BuildingBuilder{

     // construction des listed de string correspondant aux cout, conso et production des batiment
    
    private String[] Cost = new String[3];
    private int[] CostPrice = new int[3]; 
    private String[] Prod = new String[1]; 
    private int[] ProdPrice = new int[1]; 
    private String[] Conso = new String[2]; 
    private int[] ConsoPrice = new int[2]; 
        
    public void setVariable(){
         
        Cost[0] = "Gold";  
        Cost[1] = "Wood";  
        Cost[2] = "Stone";


        CostPrice[0] = 6;  
        CostPrice[1] = 50; 
        CostPrice[2] = 50;


        Prod[0] = "Cement";   
        ProdPrice[0] = 4; 
 

        Conso[0] = "Stone";  
        Conso[1] = "Coat";  
        ConsoPrice[0] = 4; 
        ConsoPrice[1] = 4; 
    } 
 
    
    // fonction permettant de généraliser la construction de vecteur correspondant aux cout, conso, prod
    
    public Vector <Resource> setVector(String[] List, int[] Price ){
        Vector <Resource> res = new Vector<Resource>();
        for (int i = 0 ; i < List.length; i++){
            res.add(new Resource(List[i],Price[i]));
        } 
        return res; 
    }  


    public void buildCost(){
        setVariable();
        Vector <Resource> res = setVector(Cost,CostPrice); 
        building.setCout(res); 
    } 


    public void buildNbHabitant(){

        building.setHabitants(0);
    } 

    public void buildNbTravailleur(){
        building.setTravailleur(10);
    } 

    public void setTemps(){
        building.setTemps(4);
    }

    public void buildConso(){
        setVariable();
        Vector <Resource> res = setVector(Conso,ConsoPrice);
        building.setConso(res);
    } 

    public void buildProduction(){
        setVariable();
        Vector <Resource> res = setVector(Prod,ProdPrice);
        building.setProd(res); 
    } 
} 