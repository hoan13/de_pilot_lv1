package hoanhqph30066.fpoly.de_pilot.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoanhqph30066.fpoly.de_pilot.Model.Xe;
import hoanhqph30066.fpoly.de_pilot.database.DbHelper;

public class XeDAO {
    private DbHelper dbHelper;
    public XeDAO (Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Xe> getAllData() {
        ArrayList<Xe> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM XE", null);
        if (cursor.moveToFirst()) {
            do {
                Xe xe = new Xe();
                xe.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                xe.setTen(cursor.getString(cursor.getColumnIndexOrThrow("ten")));
                xe.setHangXe(cursor.getString(cursor.getColumnIndexOrThrow("hangxe")));
                xe.setNamSX(cursor.getInt(cursor.getColumnIndexOrThrow("NamSX")));
                xe.setGiaBan(cursor.getDouble(cursor.getColumnIndexOrThrow("GiaXe")));
                list.add(xe);
            } while (cursor.moveToNext());
        }
//        db.close();
        cursor.close();
        return list;
    }

    public boolean ThemXe(Xe xe) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", xe.getTen());
        values.put("hangxe", xe.getHangXe());
        values.put("NamSX",xe.getNamSX());
        values.put("GiaXe",xe.getGiaBan());

        long check = db.insert("XE", null, values);
//        db.close();
        return check != -1;
    }

    public int XoaXe(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("XE","id =?",new String[]{String.valueOf(id)});

    }
}
