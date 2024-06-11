
import java.util.*;

public class Building {
    private String nom; // nom du batiment
    private String type; // type du batiment (cabane, ferme...)
    private int [] nbHabitants = new int [2] ; // nombre d'habitant le batiment pouvant abriter 
    private int [] nbTravailleurs = new int [2]; // nombre de travailleurs le batiment pouvant abriter
    private Vector<Resident> habitants = new Vector<Resident> (); // liste des habitants du batiment
    private Vector<Worker>  travailleurs = new Vector<Worker> (); // liste des travailleurs du batiment
    private Vector<Resource> Cout = new Vector<Resource>(); // cout de la construction du batiment
    private Vector<Resource> Conso = new Vector<Resource>();
    private Vector<Resource> Prod = new Vector<Resource>();
    
    // consommation de plusieurs ressources 
    // production 
    private int niveau = 0;
    private int tempsDepart = 0;
    private int temps = 0;

    /*
    public Building(String nom, String type){
        this.nom=nom;
        this.type=type; 
    }
    */
    
    public int getTemps(){
        return this.temps;
    }

    public int getDepart(){
        return this.tempsDepart;
    }

    public String getNom(){
        return this.nom; 
    }  

    public String getType(){
        return this.type; 
    } 

    public void setNomType (String Nom, String Type){
        this.nom = Nom; 
        this.type = Type; 
    } 

    public void setTemps(int temps){
        this.temps = temps;
    }

    public void setDepart(int temps){
        this.tempsDepart = temps;
    }

    public void addHabitants(Resident people){
        if (this.habitants.size() < this.nbHabitants[0]){
            this.habitants.add(people); 
        } 
        else{
            System.out.println("erreur le nombre max d'habitants a été atteint"); 
        } 
        
    } 

    public void addWorker(Worker people){
        if (this.travailleurs.size() < this.nbTravailleurs[0]){
            this.travailleurs.add(people); 
        } 
        else{
            System.out.println("erreur le nombre max de travailleur a été atteint"); 
        } 
        
    } 

    public Vector<Resident> getHabitants(){
        return this.habitants; 
    } 

    public Vector<Worker> gettravailleur(){
        return this.travailleurs; 
    } 

    public int printResident(){
        int size = this.habitants.size(); 
        return size; 
    } 

    public int printWorker(){
        int size = this.travailleurs.size();
        return size;  
    } 

    public Vector<Resource> getCout(){
        return this.Cout;
    }

    public Vector<Resource> getConso(){
        return this.Conso;
    }

    public Vector<Resource> getProd(){
        return this.Prod;
    }

    public void setHabitants(int nb){
        this.nbHabitants[0]= nb; 
    }  

    public void setTravailleur(int nb){
        this.nbTravailleurs[0] = nb;  
    } 

    public int getNbHabitants(){
        return this.nbHabitants[0] ;
    }

    public int getNbTravailleurs(){
        return this.nbTravailleurs[0] ;
    }

    public int getNiveau(){
        return this.niveau;
    }

    public void setCout(Vector<Resource> cout){
        this.Cout = cout;
    }

    public void setConso(Vector<Resource> conso){
        this.Conso = conso; 
    } 

    public void setProd(Vector<Resource> prod){
    this.Prod = prod; 
    } 

    


}