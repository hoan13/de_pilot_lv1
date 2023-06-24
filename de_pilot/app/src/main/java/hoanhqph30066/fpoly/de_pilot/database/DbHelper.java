package hoanhqph30066.fpoly.de_pilot.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DB_XE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tlb_xe = "CREATE TABLE XE(id integer primary key autoincrement,ten TEXT NOT NULL UNIQUE, hangxe TEXT NOT NULL," +
                "NamSX INTEGER NOT NULL, GiaXe DOUBLE NOT NULL)";
        db.execSQL(tlb_xe);

        String xe1 = "INSERT INTO XE VALUES(1,'BMW-320i','BMW','2020','4000')";
        db.execSQL(xe1);

        String xe2 = "INSERT INTO XE VALUES(2,'Toyota Raize',' Toyota','2021','500.12')";
        db.execSQL(xe2);

        String xe3 = "INSERT INTO XE VALUES(3,'Ford Ranger XLT 2.0','Ford Ranger','2021','800.33')";
        db.execSQL(xe3);

        String xe4 = "INSERT INTO XE VALUES(4,'Honda City 1.5 RS','Honda','2022','400.1')";
        db.execSQL(xe4);

        String xe5 = "INSERT INTO XE VALUES(5,'Lexus UX200','Lexus','2020','700.123')";
        db.execSQL(xe5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_xe = "DROP TABLE IF EXISTS XE ";
        db.execSQL(delete_xe);
        onCreate(db);
    }
}
