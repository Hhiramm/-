package exer130.team.domain;

import exer130.team.servise.Status;

public class Architect extends Designer{
    private int stock;  //股票

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString() {
        return getDetails() + "\t架构师\t" + getStatus() + "\t" + getBonus() +"\t" + getStock() + "\t" + getEquipment().getDescription();
    }
    public String getDetailsForTeam(){
        return getBasicDetailsForTeam() + "架构师" + getBonus() + "\t" + getStock();
    }
}
