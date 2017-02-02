package mx.grupogarcia.soportewear.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.grupogarcia.soportewear.models.Mascota;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by Luis García on 03/01/2017.
 */
public class DataBase extends SQLiteOpenHelper{
    private Context context;
    public DataBase(Context context) {
        super(context,DBConfig.NAME_DATABASE, null, DBConfig.VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String initMascota="CREATE TABLE "+DBConfig.MASCOTA_NAME+" ( " +
                    DBConfig.MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    DBConfig.MASCOTA_NOMBRE+" TEXT, " +
                    DBConfig.MASCOTA_PICTURE+" INTEGER, " +
                    DBConfig.MASCOTA_VOTOS+" INTEGER)";

            String initFotos="CREATE TABLE "+DBConfig.FOTOS_NAME+" ( "+
                    DBConfig.FOTO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    DBConfig.FOTO_MASCOTA_ID+" INTEGER, "+
                    DBConfig.FOTO_RECURSO+" INTEGER, "+
                    DBConfig.FOTO_LIKES+" INTEGER, "+
                    "FOREIGN KEY ( "+DBConfig.FOTO_MASCOTA_ID+" ) "+
                    "REFERENCES "+DBConfig.MASCOTA_NAME+" ( "+DBConfig.MASCOTA_ID+" ) "+
                    ")";
        db.execSQL(initMascota);
        db.execSQL(initFotos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+DBConfig.MASCOTA_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+DBConfig.FOTOS_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> getMascotas(){
        ArrayList<Mascota> mascotas=new ArrayList<Mascota>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+DBConfig.MASCOTA_NAME;
        Cursor registros=db.rawQuery(query,null);

        while (registros.moveToNext()){
          /*  Mascota aux=new Mascota();
            aux.setId(registros.getInt(0));
            aux.setNombre(registros.getString(1));
            aux.setPicture(registros.getInt(2));
            aux.setVotos(registros.getInt(3));
            String queryFotos="Select * from "+DBConfig.FOTOS_NAME+" where "+DBConfig.FOTO_MASCOTA_ID+" = "+registros.getInt(0);
            Cursor registrosFotos=db.rawQuery(queryFotos,null);
            ArrayList<Foto> fotos=new ArrayList<Foto>();
            while(registrosFotos.moveToNext()){
                fotos.add(new Foto(registrosFotos.getInt(2),registrosFotos.getInt(3)));
            }

            aux.setFotos(fotos);
            mascotas.add(aux);*/
        }
        db.close();
        return mascotas;
    }


    public ArrayList<Mascota> getFavoritos(){
        ArrayList<Mascota> mascotas=getMascotas();
        Collections.sort(mascotas);
        ArrayList<Mascota> top=new ArrayList<Mascota>();
        for (int i=0;i<5;i++)
            top.add(mascotas.get(i));
        return top;
    }

    public void insertMascota(ContentValues values){
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(DBConfig.MASCOTA_NAME,null,values);
        db.close();
    }

    public void updateMascota(ContentValues values,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        String where=DBConfig.MASCOTA_ID+"="+id;
        db.update(DBConfig.MASCOTA_NAME,values,where,null);
        db.close();
    }

    public Mascota getMascota(int id){
        Mascota m=new Mascota();
          /*  String query="Select * from "+DBConfig.MASCOTA_NAME+" where "+
                    DBConfig.MASCOTA_ID+" = "+id;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.moveToNext()){
            m.setId(c.getInt(0));
            m.setNombre(c.getString(1));
            m.setPicture(c.getInt(2));
            m.setVotos(c.getInt(3));
            ArrayList<Foto> fotos=new ArrayList<Foto>();
            Cursor cu=db.rawQuery("SELECT * FROM "+DBConfig.FOTOS_NAME+" WHERE "+DBConfig.FOTO_MASCOTA_ID+" = "+m.getId(),null);

            while(cu.moveToNext()){
                fotos.add(new Foto(cu.getInt(2),cu.getInt(3)));
            }
            m.setFotos(fotos);
        }
        db.close();*/
        return m;
    }

    public void insertarFoto(ContentValues values){
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(DBConfig.FOTOS_NAME,null,values);
        db.close();
    }
}
