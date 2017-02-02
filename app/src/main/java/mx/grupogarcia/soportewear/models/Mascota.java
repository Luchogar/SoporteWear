package mx.grupogarcia.soportewear.models;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Luis García on 03/01/2017.
 */

public class Mascota  implements Comparable {
    private int votos;
    private String nombre,id,picture,fecha,id_foto;
    private ArrayList<Foto> fotos;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }

    public Mascota(String picture, int votos, String nombre, ArrayList<Foto> fotos) {
        this.picture = picture;

        this.votos = votos;
        this.nombre = nombre;
        this.fotos=fotos;

    }

    public Mascota() {

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object otra) {
        Date d=new Date(Long.parseLong(this.getFecha())*1000);
        Date d2=new Date(Long.parseLong(((Mascota)otra).getFecha())*1000);

        return -d.compareTo(d2);
        //return ((Mascota)otra).getVotos()-this.getVotos();
    }


}


