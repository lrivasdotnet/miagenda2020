package net.lrivas.miagenda.clases;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {
    final String TABLA_CONTACTOS = "CREATE TABLE contactos (" +
            "id_contacto  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre  TEXT, telefono  TEXT)";   

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_CONTACTOS);
        db.execSQL(TABLA_IMAGENES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactos");        
        onCreate(db);
    }
}
