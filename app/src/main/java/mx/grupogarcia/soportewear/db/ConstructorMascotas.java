package mx.grupogarcia.soportewear.db;

import android.content.ContentValues;
import android.content.Context;

import mx.grupogarcia.soportewear.models.Mascota;

import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public class ConstructorMascotas {
    private Context context;
    private DataBase db;
    public ConstructorMascotas(Context context) {
        this.context = context;
        db=new DataBase(context);
        //initData();
    }

    public ArrayList<Mascota> getMascotas(){
        return db.getMascotas();
    }

    private void initData(){
        if(db.getMascotas().size()<1){
            Mascota[] mascotas={};/*{
                    new Mascota(R.drawable.gato1,0,"Mi mascota",buscarFotos(R.drawable.gato1)),
                    new Mascota(R.drawable.pajaro1,0,"Periquillo",buscarFotos(R.drawable.pajaro1)),
                    new Mascota(R.drawable.pajaro2,0,"Pajarito",buscarFotos(R.drawable.pajaro2)),
                    new Mascota(R.drawable.perro1,0,"Doggy",buscarFotos(R.drawable.perro1)),
                    new Mascota(R.drawable.perro2,0,"Dobby",buscarFotos(R.drawable.perro2)),
                    new Mascota(R.drawable.tortuga1,0,"Torty",buscarFotos(R.drawable.tortuga1))
            };¨
            for(int j=0;j<mascotas.length;j++){
                ContentValues values=new ContentValues();
                values.put(DBConfig.MASCOTA_NOMBRE,mascotas[j].getNombre());
                values.put(DBConfig.MASCOTA_PICTURE,mascotas[j].getPicture());
                values.put(DBConfig.MASCOTA_VOTOS,mascotas[j].getVotos());
                db.insertMascota(values);
                for(int k=0;k<mascotas[j].getFotos().size();k++){
                    ContentValues valorFotos=new ContentValues();
                    valorFotos.put(DBConfig.FOTO_MASCOTA_ID,j+1);
                    valorFotos.put(DBConfig.FOTO_LIKES,mascotas[j].getFotos().get(k).getVotos());
                    valorFotos.put(DBConfig.FOTO_RECURSO,mascotas[j].getFotos().get(k).getFoto());
                    db.insertarFoto(valorFotos);
                }
            }*/
        }
    }

    public Mascota darLike(Mascota m){
        ContentValues values=new ContentValues();
        values.put(DBConfig.MASCOTA_VOTOS, m.getVotos()+1);
       // db.updateMascota(values,m.getId());
        //return db.getMascota(m.getId());
        return new Mascota();
    }

    /*private ArrayList<Foto> buscarFotos(int id){
        ArrayList<Foto> aux=new ArrayList<Foto>();
        for(int i=0;i<(int)((Math.random()*100)%10)+1;i++)
            aux.add(new Foto(R.drawable.gato1,(int)(Math.random()*100)%10));
        return aux;
    }
*/
    public ArrayList<Mascota> getFavoritos(){
        return db.getFavoritos();
    }


    public Mascota getMiMascota(){
        return db.getMascota(1);
    }
}
