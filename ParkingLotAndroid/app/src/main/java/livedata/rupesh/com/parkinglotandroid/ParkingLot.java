package livedata.rupesh.com.parkinglotandroid;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Database dbHelper;

    public ParkingLot(Context context) {
        dbHelper = new Database(context);
    }
    private static HashMap<Integer, Vehicle> parkedVehicle;
    private static HashMap<Integer, Integer> vehicle_lot_data;

    public static Map<Integer, Vehicle> parkedVehicleMap() {
        if (parkedVehicle == null) {
            initializeParkVehicleMap();
        }
        return parkedVehicle;
    }

    public static void initializeParkVehicleMap() {
        if (parkedVehicle == null) {
            synchronized (ParkingLot.class) {
                if (parkedVehicle == null) {
                    parkedVehicle = new HashMap<Integer, Vehicle>();
                    vehicle_lot_data = new HashMap<Integer, Integer>();
                }
            }
        }
    }

    public static Map<Integer, Integer> getVehicleLotMap() {
        if (vehicle_lot_data == null) {
            initializeParkVehicleMap();
        }
        return vehicle_lot_data;
    }

    public void parkVehicle(Vehicle vehicle, int lot){
       parkedVehicle.put(lot, vehicle);
    }
    public boolean isLotBooked(int lot) {
        if (vehicle_lot_data != null)
        {
            for (int l : vehicle_lot_data.values())
            {
                if (l == lot)
                    return true;
            }
            return false;
        }
        return false;
    }

    public boolean isVehicleExists(int vehicleId) {
        if (parkedVehicle != null)
        {
            for (Vehicle v : parkedVehicle.values())
            {
                if (v.getId() == vehicleId)
                    return true;
            }
            return false;
        }
        return false;
    }
}
