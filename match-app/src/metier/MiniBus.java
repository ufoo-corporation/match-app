package metier;

public class MiniBus {
    private int no;
    private int capacite;
    
    public MiniBus(int no, int capacite){
        this.no = no;
        this.capacite = capacite;
    }
    
    public int getNo(){
        return no;
    }
    
    public int getCapacite(){
        return capacite;
    }
}