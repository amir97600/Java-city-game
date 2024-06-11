import java.util.*;
import java.io.*;

public class FarmBuilder extends BuildingBuilder{

     // construction des listed de string correspondant aux cout, conso et production des batiment
    
    private String[] Cost = new String[3];
    private int[] CostPrice = new int[3]; 
    private String[] Prod = new String[1]; 
    private int[] ProdPrice = new int[1]; 
    private String[] Conso = new String[1]; 
    private int[] ConsoPrice = new int[1]; 
        
    public void setVariable(){
         
        Cost[0] = "Gold";  
        Cost[1] = "Wood";  
        Cost[2] = "Stone";  

        CostPrice[0] = 4;  
        CostPrice[1] = 5; 
        CostPrice[2] = 5;


        Prod[0] = "Food";  
        ProdPrice[0] = 10;


        Conso[0] = null;  
        ConsoPrice[0] = 0; 
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

        building.setHabitants(5);
    } 

    public void buildNbTravailleur(){
        building.setTravailleur(3);
    } 


    public void buildConso(){
        setVariable();
        Vector <Resource> res = setVector(Conso,ConsoPrice);
        building.setConso(res);
    } 

    public void setTemps(){
        building.setTemps(4);
    }
    
    public void buildProduction(){
        setVariable();
        Vector <Resource> res = setVector(Prod,ProdPrice);
        building.setProd(res); 
    } 
} 