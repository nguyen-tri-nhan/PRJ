/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author nguyentrinhan2000
 */
public class SanPhams {
    String Id;
    String Name;
    int Price;
    int Quantity;
    boolean Available;

    public SanPhams() {
        Id = "";
        Name = "";
        Price= 0;
        Quantity = 0;
        Available = false;
    }

    public SanPhams(String Id, String Name, int Price, int Quantity, boolean Available) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Available = Available;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean Available) {
        this.Available = Available;
    }
    
}
