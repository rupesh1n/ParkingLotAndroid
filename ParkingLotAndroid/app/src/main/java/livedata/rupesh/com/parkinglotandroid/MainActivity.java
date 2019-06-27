package livedata.rupesh.com.parkinglotandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Database mDatabase;
    private EditText vehicleNumTv, lotNumTv;
    private Button parkNowBt;
    private TextView mesageTv;
    private ParkingLot parkingLot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        vehicleNumTv = findViewById(R.id.vehicle_number_field);
        lotNumTv = findViewById(R.id.lot_number_field);
        parkNowBt = findViewById(R.id.Parknow_btn);
        mesageTv = findViewById(R.id.message);

        //creating a database
        mDatabase = new Database(this);
        ArrayList<HashMap<String, Integer>> parkedVehicle = mDatabase.getParkedVehicle();
        parkingLot = new ParkingLot(this);
        
    }

    public void ParkVehicleNow(View v) {
        if (inputsAreCorrect(lotNumTv.getText().toString(), vehicleNumTv.getText().toString())) {
            int vehicleLot = Integer.parseInt(lotNumTv.getText().toString().trim());
            int vehicleNum = Integer.parseInt(vehicleNumTv.getText().toString().trim());
            Vehicle vehicle = new Vehicle(vehicleNum, vehicleLot);


            if (parkingLot.isLotBooked(vehicleLot)) {
                Toast.makeText(getApplicationContext(), "Lot already taken",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if( parkingLot.isVehicleExists(vehicleNum)) {
                Toast.makeText(getApplicationContext(), "Vehicle already present",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (mDatabase.getParkedVehicle(vehicle.getId()).isEmpty()) {
                mDatabase.parkVehicle(vehicle);
            }
        }

    }

    private boolean inputsAreCorrect(String vehicleLot, String vehicleNum) {
        if (vehicleLot.isEmpty() || Integer.parseInt(vehicleNum) <= 0) {
            lotNumTv.setError("Please enter a vehicle Lot");
            lotNumTv.requestFocus();
            return false;
        }

        if (vehicleNum.isEmpty() || Integer.parseInt(vehicleNum) <= 0) {
            vehicleNumTv.setError("Please enter vehicle Number");
            vehicleNumTv.requestFocus();
            return false;
        }
        return true;
    }
}