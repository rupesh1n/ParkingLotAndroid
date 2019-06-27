package livedata.rupesh.com.parkinglotandroid;

public class Vehicle {
    private int id;
    private int lot;

    public Vehicle(int id, int lot) {
        this.id = id;
        this.lot = lot;
    }

    public int getId() {
        return id;
    }

    public int getLot() {
        return lot;
    }
}