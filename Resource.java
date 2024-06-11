public class Resource {
    private String nom; 
    private int quantite; 

    public Resource(String nom,int quantite){
        this.nom=nom;
        this.quantite=quantite;
    }

    public String getNom(){
        return this.nom;
    }

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int quantite){
        this.quantite=quantite;
    }

    public void rmQT(int i){
        this.quantite-=i;
    }
    
    public void addQT(int i){
        this.quantite+=i;
    }
}