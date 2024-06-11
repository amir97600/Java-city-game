import java.util.*;
import java.io.*; 

public class Manager {
    
    private Vector<Building> Batiments = new Vector<Building>();
    private Vector<Building> Temporary = new Vector<Building>();
    private Vector<People> RessourceHumaines = new Vector<People>();
    private Vector<Resource> RessourceMaterielle = new Vector<Resource>();
    private static Manager instance = null;
     
     
    private Manager(){};

    public static Manager getInstance(){
        if(instance == null){
            instance = new Manager();
        }
        return instance;
    }


    // gérer le jeu 
    public void Start(){
        StartBuilding();
        StartResource();
        int time = 0; 

        displayResources(RessourceMaterielle); 
        while(true){
            System.out.println("##################City Game##################");
            System.out.println("Que voulez vous faire ? ");
            System.out.println("0\tLire les règles");
            System.out.println("1\tAjouter un batiment");
            System.out.println("2\tRetirer un batiment");
            System.out.println("3\tAjouter des personnes dans un batiment");
            System.out.println("4\tRetirer un batiment");
            int input = saisie_int(); 

            switch(input){
                case 0: regle();break;
                case 1: addBuilding(time,Batiments,RessourceMaterielle);break; 
                case 2: removeBuilding(Batiments);break; 
                case 3: addPeople(Batiments);break; 
                case 4: removePeople(Batiments);break; 
                default: System.out.println("\nEntrez une option valide\n\n");break;
            } 

            time++; 
            System.out.print("time : " + time ); 
            // ajouter conso/production des ressources en fonction du temps ici 

            consoBat(Batiments,RessourceMaterielle); 
            ProdBat(Batiments,RessourceMaterielle); 
            constructBuilding(time);
            displayResources(RessourceMaterielle); 
        } 
    }

    public void menu(){
        System.out.println("##################City Game##################");
        System.out.println("\n\nAppuyer sur une touche pour commencer la partie\n\n");
        //int input = saisie_int();
        //Start();
        while(true){
            System.out.println("0\tQuitter la partie");
            System.out.println("1\tLire les règles");
            System.out.println("2\tCommencer la partie");
            
            
            int choix = saisie_int();
            switch (choix) {
                case 0: System.exit(0); 
                case 1: regle(); break; 
                case 2: Start(); break; 
                default: System.out.println("\nEntrez une option valide\n\n");break;

            }
        }
    }

    // gérer les fonctions liées aux règles/début de partie
    public void regle(){
        System.out.println("\nLe but du jeu est de gérer vos bâtiments et vos ressources jusqu'a reussir a avoir 1000 Gold\n\n");
    }

    public void StartResource(){
        Resource resource1 = new Resource("Gold", 10);
        RessourceMaterielle.add(resource1);
        Resource resource2 = new Resource("Wood", 5);
        RessourceMaterielle.add(resource2);
        Resource resource3 = new Resource("Stone", 10);
        RessourceMaterielle.add(resource3);
        Resource resource4 = new Resource("Food", 10);
        RessourceMaterielle.add(resource4);
        Resource resource5 = new Resource("Coal", 0);
        RessourceMaterielle.add(resource5);
        Resource resource6 = new Resource("Iron", 0);
        RessourceMaterielle.add(resource6);
        Resource resource7 = new Resource("Steel", 0);
        RessourceMaterielle.add(resource7);
        Resource resource8 = new Resource("Cement", 0);
        RessourceMaterielle.add(resource8);
        Resource resource9 = new Resource("Lumber", 0);
        RessourceMaterielle.add(resource9);
        Resource resource10 = new Resource("Tools", 0);
        RessourceMaterielle.add(resource10);
        Resource resource11 = new Resource("null", 0);
        RessourceMaterielle.add(resource11);
    }

    public void StartBuilding(){
        BuildingDirector director = new BuildingDirector(); 
        BuildingBuilder Farm = new FarmBuilder();
         

        director.setBuildingBuilder(Farm); 
        director.constructBuilding();
        Building item = director.getBuilding();
        item.setNomType("start", "Farm");
        Batiments.add(item);
    }

    public void constructBuilding(int temps){
        int score = 0;
        Building select = null;
        for(Building temporaire : Temporary){

            if(temps-temporaire.getDepart()>=temporaire.getTemps()){
                Vector<Resource> cost = temporaire.getCout(); 
                for (Iterator<Resource> e = cost.iterator(); e.hasNext();){
                // parcour du vecteur et analyse des ressources disponible pour la construction du batiment
                Resource item = (Resource) e.next(); 
                String nom = item.getNom(); 
                Resource Stock = FindResource(nom,RessourceMaterielle);

                if (Stock.getQuantite() > item.getQuantite()){ 
                    System.out.println("La quantité de " + nom + " est suffisante."); 
                    score++; 
                } 
            }

            if (score==cost.size()){
                // signifie que toute les ressources sont disponible
                System.out.println(temporaire.getNom() + "a été correctement créé"); 
                consoResource(cost,RessourceMaterielle);
                Batiments.add(temporaire); 
                select = temporaire;

            } 
            else{System.out.println("erreur stock");} 
            
            }
        }

        if(select!=null && temps-select.getDepart()>=select.getTemps()){
            Temporary.remove(select);
        }
    }

    // gérer la consommation et la production de ressources 

    public int controlBatiment(int temps,Building batiment,Vector<Resource> RessourceMaterielle, Vector<Building> Batiments){
        // controles des ressouces dans le vecteur et utilisation
        batiment.setDepart(temps);
        Temporary.add(batiment);
        return 0;  
    }

    public void consoBat(Vector<Building> Batiments,Vector<Resource> RessourceMaterielle){
        // gère la consommation de tout les batiments 

        for (Iterator e = Batiments.iterator(); e.hasNext();){
            Building item = (Building) e.next(); 
            Vector<Resource> cost = item.getConso();

            if(cost.equals(null)){
                System.out.println("Conso nulle de " + item.getNom()); 
            } 
            else{
                consoResource(cost,RessourceMaterielle);
                consoFood(item,RessourceMaterielle); 
            } 
        } 
    } 

    public void consoFood(Building bat, Vector<Resource> RessourceMaterielle){
        // retourner le vecteur contenant les habitants et le parcourir
        Vector <Resident> resident = bat.getHabitants(); 
        Resource Food = FindResource("Food",RessourceMaterielle );

        for (Iterator e = resident.iterator(); e.hasNext();){
            Resident pers = (Resident) e.next(); 
            pers.manger(Food); 
            System.out.println("conso food"); 
        } 
    } 

    public void ProdBat(Vector<Building> Batiments,Vector<Resource> RessourceMaterielle){
        // gère la consommation de tout les batiments 

        for (Iterator e = Batiments.iterator(); e.hasNext();){
            Building item = (Building) e.next(); 
            Vector<Resource> prod = item.getProd();
            ProdResource(prod,RessourceMaterielle); 
        } 
    }

    public void consoResource(Vector<Resource> cost, Vector<Resource> RessourceMaterielle){
        // parcour du vecteur et analyse des ressources et consommation 
        
        for (Iterator e = cost.iterator(); e.hasNext();){
        Resource item = (Resource) e.next(); // item correspondant a la ressource du vecteur des couts de constructions
        String nom = item.getNom(); 
        Resource Stock = FindResource(nom,RessourceMaterielle); // Stock présent dans les ressources globales
        int qt = item.getQuantite(); 

        if (Stock==null){
            System.out.println("conso null"); 
        }   
        else{
            Stock.rmQT(qt);   
            System.out.println(Stock.getNom() + " a été consommé");
        } 

         
        }
    } 

    public void ProdResource(Vector<Resource> prod, Vector<Resource> RessourceMaterielle){
        // parcour du vecteur et analyse des ressources et consommation 
        
        for (Iterator e = prod.iterator(); e.hasNext();){
        Resource item = (Resource) e.next(); // item correspondant a la ressource du vecteur des couts de constructions
        String nom = item.getNom(); 
        Resource Stock = FindResource(nom,RessourceMaterielle); // Stock présent dans les ressources globales
        int qt = item.getQuantite(); 
        
        // créer une fonction permettant de controller l'existance des ressources, et dans le cas contraire les ajouter dans le vecteur
        if (Stock==null){
            System.out.println("conso null"); 
        }   
        else{
        
        Stock.addQT(qt);   
        System.out.println(Stock.getNom() + " a été produit"); 
        } 
        }
    }

    // gérer l'ajout et le retrait de batiments    

    public void addBuilding(int temps,Vector<Building> Batiments,Vector<Resource> RessourceMaterielle){
        // construction d'un nouveau batiment
        BuildingDirector director = new BuildingDirector(); 
        System.out.println("Quel type de batiment voulez vous construire ? "); 
        System.out.println("1 : Wooden Cabin, 2 : House, 3 : Apartment Building, 4 : Farm, 5 : Quarry, 6 : Lumber Mill, 7 : Cement Plant, 8 : Steel Mill, 9 : Tool Factory");
        int typeInt = saisie_int(); 
        
        BuildingBuilder Bat = null; 
        String typeS = "" ; 

        System.out.println("Quel nom voulez-vous donner au batiment ? "); 
        String nom = saisie_string();


        switch (typeInt){
            case 1 : Bat = new WoodenCabinBuilder(); typeS = "WoodenCabin";break; 
            case 2 : Bat = new HouseBuilder(); typeS ="House";break; 
            case 3 : Bat = new ApartmentBuildingBuilder(); typeS ="ApartmentBuilding";break; 
            case 4 : Bat = new FarmBuilder(); typeS ="Farm";break; 
            case 5 : Bat = new QuarryBuilder(); typeS ="Quarry";break; 
            case 6 : Bat = new LumberMillBuilder(); typeS ="LumberMill";break; 
            case 7 : Bat = new CementPlantBuilder(); typeS ="CementPlant";break; 
            case 8 : Bat = new SteelMillBuilder(); typeS ="SteelMill";break; 
            case 9 : Bat = new ToolFactoryBuilder(); typeS ="ToolFactory";break; 

            default: System.out.println("\nEntrez une option valide\n\n");break;

        } 


        director.setBuildingBuilder(Bat); 
        director.constructBuilding();
        Building item = director.getBuilding();
        item.setNomType(nom, typeS); 

        controlBatiment(temps,item,RessourceMaterielle, Batiments); 
    } 

    public void removeBuilding(Vector<Building> Batiments){
        System.out.println("Quel batiment voulez vous retirer ? "); 
        String nom = saisie_string(); 
        Building building = FindBuilding(nom,Batiments); 
        if(Batiments.isEmpty()){
            System.out.println("Il n'existe aucun batiment"); 
        }
        else if (Batiments.contains(building)){
            System.out.println("Le batiment existe"); 
        } 
        else{
            System.out.println("Le batiment n'existe pas "); 
        } 
        int i = 0;      
        for (Iterator e = Batiments.iterator(); e.hasNext();){
            Building item = (Building) e.next(); 
            i++; 
            if ((item.getNom()).equals(nom)){
                Batiments.remove(i); 
                System.out.println("Et a été retiré"); 
        
            }
        } 
    } 

    // gérer l'ajout et le retrait de people dans le batiment 

    public void addPeople(Vector<Building> Batiments){
        System.out.println("Veuillez saisir le nom du batiment a sélectionner"); 
        String nom = saisie_string(); 
        Building item = FindBuilding(nom,Batiments); // récupération du building

        System.out.println ("Capacité du batiment : " + item.printResident() + "/" + item.getNbHabitants() + " habitants "  + item.printWorker() + "/" + item.getNbTravailleurs() + " travailleurs"  ); 
        System.out.println("1\t Ajouter un  habitant "); 
        System.out.println("2\t Ajouter un travailleur "); 
        int choice = saisie_int(); 

        System.out.println("Combien voulez vous ajouter dans le building ? "); 
        int nb = saisie_int();
        
        switch(choice){
            case 1: 
            for (int i = 0; i<nb; i++){
                Resident pers = new Resident (item); 
                item.addHabitants(pers); 
                System.out.println("habitant(s) ajouté(s) avec succès "); 
            } 
            case 2: 
            for (int i = 0; i<nb; i++){
                Worker pers = new Worker (item); 
                item.addWorker(pers); 
                System.out.println("travailleur(s) ajouté(s) avec succès "); 
            }
            default: System.out.println("\nEntrez une option valide\n\n");break;
 
 
        } 
        
    } 

    public void removePeople(Vector<Building> Batiments){
        System.out.println("Veuillez saisir le nom du batiment a sélectionner"); 
        String nom = saisie_string(); 
        Building item = FindBuilding(nom,Batiments); // récupération du building

        System.out.println ("Capacité du batiment : " + item.printResident() + "/" + item.getNbHabitants() + " habitants "  + item.printWorker() + "/" + item.getNbTravailleurs() + " travailleurs"  ); 
        System.out.println("1\t Retirer un  habitant "); 
        System.out.println("2\t Retirer un travailleur "); 
        int choice = saisie_int(); 

        System.out.println("Combien voulez vous en retirer du building ? "); 
        int nb = saisie_int();
        
        switch(choice){
            case 1: 
            Vector<Resident> hab = item.getHabitants(); 
            for (int i = 0; i<nb; i++){
                
                hab.remove(i); 
                System.out.println("habitant(s) retiré(s) avec succès "); 
            } 
            case 2: 
            Vector<Worker> wor = item.gettravailleur(); 
            
            for (int i = 0; i<nb; i++){
                 
                wor.remove(i); 
                System.out.println("travailleur(s) retiré(s) avec succès "); 
            }
            default: System.out.println("\nEntrez une option valide\n\n");break;
 
 
        } 
        
    } 

    // affichage des ressources et des batiments a l'écran 
    // A IMPLEMENTER
    public void displayResources(Vector<Resource> RessourceMaterielle){

        String print = ""; 
        for (Iterator e = RessourceMaterielle.iterator(); e.hasNext(); ){
            Resource item = (Resource) e.next(); 

            print += item.getNom() + ":" + item.getQuantite() + " "; 
        }
        System.out.println(print);  
    } 

    public void displayBuilding(Vector<Building> Batiments){
        String print = ""; 
        for (Iterator e = Batiments.iterator(); e.hasNext(); ){
            Building item = (Building) e.next(); 

            print += item.getNom() + "| " + item.getType() + " "; 
        }
        System.out.println(print);
    } 
    // recherche de ressource ou de batiments
    public Building FindBuilding(String nom,Vector<Building> Batiments){
        // trouve un batiment dans le vecteur et le renvoie 
        if(Batiments.isEmpty()){
                    System.out.println("Il n'existe aucun batiment"); 
                
        }       
        for (Iterator e = Batiments.iterator(); e.hasNext();){
            Building item = (Building) e.next(); 

            if ((item.getNom()).equals(nom)){
                System.out.println("Batiment trouvé"); 
                return item;
            }
        }
        System.out.println("Batiment indisponible"); 
        return null; 
            
    } 

    public Resource FindResource(String nom,Vector<Resource> RessourceMaterielle){
        // trouve une ressource dans le vecteur et le renvoie
        if(RessourceMaterielle.isEmpty()){
                    System.out.println("Il n'existe aucun stock \n Vous devez créer un stock"); 
                
        }       
        for (Iterator e = RessourceMaterielle.iterator(); e.hasNext();){
            Resource item = (Resource) e.next(); 

            if ((item.getNom()).equals(nom)){
                return item;
            }
        }
        return null; 
            
    } 




    // saisie int et saisie string 
    public static int saisie_int(){
        try{
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String chaine = buff.readLine();
            int num = Integer.parseInt(chaine);
            return num;
        }
        catch(IOException e){
            System.out.println("impossible int " + e);
            return -1;
        }// fin catch
        } // fin saisie int

    public static String saisie_string()
        {
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String chaine = buff.readLine();
            return chaine;
        }
        catch(IOException e){
            System.out.println("Impossible str" + e);
            return null;
        } // FIN CATCH
        } // fin saisie string


}
