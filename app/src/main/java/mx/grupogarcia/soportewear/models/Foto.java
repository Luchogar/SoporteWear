package mx.grupogarcia.soportewear.models;

/**
 * Created by Luis García on 03/01/2017.
 */

public class Foto {
    private int votos;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String fecha,url;

    public Foto(String url, int votos) {
        this.url=url;
        this.votos = votos;
    }

    public Foto() {

    }




    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
