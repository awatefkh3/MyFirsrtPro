package com.example.myfirsrtpro;

public class Item{

    private String description;
    private int resid; // image id to be loaded
    private boolean isHappy;
    private int amount;

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", resid=" + resid +
                ", isHappy=" + isHappy +
                ", amount=" + amount +
                '}';
    }

    public Item(String description, int resid, boolean isHappy, int amount) {
        this.description = description;
        this.resid = resid;
        this.isHappy = isHappy;
        this.amount = amount;
    }

    public Item() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
//todo delete this