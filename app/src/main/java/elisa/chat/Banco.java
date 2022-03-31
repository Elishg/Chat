package elisa.chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final int versao = 1;
    private static final String nomedb = "chat";

    public Banco(@Nullable Context context) {
        super(context, nomedb, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (cod INTEGER PRIMARY KEY, name TEXT NOT NULL);");
        Log.i("aviso",Banco.class.getName());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
