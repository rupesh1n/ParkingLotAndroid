package livedata.rupesh.com.parkinglotandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "parking_database.db";
    private static final String TABLE_NAME = "parking_lots";
    private static final String COL_1 = "id";
    private static final String COL_2 = "lot_number";
    private static final String COL_3 = "vehicle_number";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_VEHICLE = "CREATE TABLE " + TABLE_NAME  + "("
                + COL_1  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COL_2 + " INTEGER, "
                + COL_3 + " INTEGER UNIQUE) ";

        db.execSQL(CREATE_TABLE_VEHICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void parkVehicle(Vehicle vehicle) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, vehicle.getLot());
        values.put(COL_3, vehicle.getId());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<HashMap<String, Integer>> getParkedVehicle() {
        ArrayList<HashMap<String, Integer>> parkedVehicleList = new ArrayList<HashMap<String, Integer>>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + "";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                map.put("id", cursor.getInt(0));
                map.put("lot_number", cursor.getInt(1));
                map.put("vehicle_number", cursor.getInt(2));
                parkedVehicleList.add(map);
            } while (cursor.moveToNext());
        }
        // return vechile list
        return parkedVehicleList;
    }

    public HashMap<String, Integer> getParkedVehicle(int vehicleId) {
        HashMap<String, Integer> parkedVehicleList = new HashMap<String, Integer>();
        String selectQuery = "SELECT * FROM parking_lots where vehicle_number ='" + vehicleId + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                parkedVehicleList.put("id", cursor.getInt(0));
                parkedVehicleList.put("lot_number", cursor.getInt(1));
                parkedVehicleList.put("vehicle_number", cursor.getInt(2));
            } while (cursor.moveToNext());
        }
        return parkedVehicleList;
    }
}
