package mx.grupogarcia.soportewear.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mx.grupogarcia.soportewear.R;
import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.rest.ConstantsApi;
import mx.grupogarcia.soportewear.rest.Endpoints;
import mx.grupogarcia.soportewear.rest.adapter.RestAdapter;
import mx.grupogarcia.soportewear.rest.model.LikeResponse;
import mx.grupogarcia.soportewear.rest.model.UsuarioResponse;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Luis García on 03/01/2017.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota m=mascotas.get(position);
        //holder.foto.setImageResource(m.getPicture());
        Picasso.with(activity)
                .load(m.getPicture())
                .placeholder(R.drawable.cat_footprint_48)
                .into(holder.foto);

        if(position%2==0)
            holder.foto.setBackgroundResource(R.color.fondo1);
        else
            holder.foto.setBackgroundResource(R.color.fondo2);
            Calendar c=Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(m.getFecha())*1000);
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        holder.fecha.setText(sdf.format(c.getTime()));
        holder.nombre.setText(m.getNombre());
        holder.votos.setText(m.getVotos()+"");

        holder.iconoFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RestAdapter adapterRest=new RestAdapter();
                Endpoints endpoints=adapterRest.establecerConexionInstagram();
                Call<LikeResponse> peticion;
                peticion=endpoints.quitarLike(m.getId_foto());
                peticion.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        Call<LikeResponse> peticion2;
                        peticion2 = endpoints.darLike(ConstantsApi.ACCESS_TOKEN, m.getId_foto());
                        peticion2.enqueue(new Callback<LikeResponse>() {
                            @Override
                            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(activity, "like a la foto", Toast.LENGTH_LONG).show();
                                    m.setVotos(m.getVotos() + 1);
                                    holder.votos.setText(m.getVotos()+"");
                                    enviarLikeFirebase(m.getId_foto(),m.getNombre());
                                } else {
                                    Toast.makeText(activity, "Algo no salio bien", Toast.LENGTH_LONG).show();
                                    Log.d("PETICION LIKE", response.code()+"");
                                }
                            }
                            @Override
                            public void onFailure(Call<LikeResponse> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(activity, "Error en la peticion", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Toast.makeText(activity, "Algo salio mal, intentelo mas tarde", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

                }

        });

    }

    private void enviarLikeFirebase(String id_foto,String propietario) {
        SharedPreferences sp=activity.getSharedPreferences("TOKEN_FIREBASE",MODE_PRIVATE);
        String token=sp.getString("TOKEN","no hay token");
        sp=activity.getSharedPreferences("Cuenta",MODE_PRIVATE);
        String usuarioActual=sp.getString("Usuario","no hay usuario");
        RestAdapter adapter=new RestAdapter();
        Endpoints endpoints=adapter.establecerConexionFirebase();
        if(usuarioActual!="no hay usuario"||token!="no hay token"){
        Call<UsuarioResponse> res=endpoints.likeFirebase(id_foto,usuarioActual,token,propietario);
        res.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.d("RESPUESTA_NODE",response.code()+"");
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("ERROR_NODE","error en el usuario");
            }
        });}else{
            Toast.makeText(activity, "No se ha configurado la cuenta o no se han activado las notificaciones", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto,iconoFavs;
        private TextView nombre,votos,fecha;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            foto=(ImageView) itemView.findViewById(R.id.foto);
            nombre=(TextView)itemView.findViewById(R.id.nombre);
            votos=(TextView)itemView.findViewById(R.id.votos);
            fecha=(TextView)itemView.findViewById(R.id.fecha);
            iconoFavs=(ImageView)itemView.findViewById(R.id.iconoFavs);
        }
    }
}
