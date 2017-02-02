package mx.grupogarcia.soportewear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import mx.grupogarcia.soportewear.adapters.PageAdapter;
import mx.grupogarcia.soportewear.fragments.ListaMascotasFragment;
import mx.grupogarcia.soportewear.fragments.MiMascotaFragment;
import mx.grupogarcia.soportewear.rest.Endpoints;
import mx.grupogarcia.soportewear.rest.adapter.RestAdapter;
import mx.grupogarcia.soportewear.rest.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setUpPageView();
        tabLayout.getTabAt(1).select();
    }



    private ArrayList<Fragment> loadFragments(){
        ArrayList<Fragment> f=new ArrayList<Fragment>();
        f.add(new ListaMascotasFragment());
        f.add(new MiMascotaFragment());
        return f;
    }

    private void setUpPageView(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),loadFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.mContacto:
                i=new Intent(MainActivity.this,ContactoActivity.class);
                startActivity(i);
                break;
            case R.id.mAbout:
                i=new Intent(MainActivity.this,AcercaDeActivity.class);
                startActivity(i);
                break;
            case R.id.topFavoritos:
                i=new Intent(MainActivity.this,Favoritas.class);
                startActivity(i);
                break;
            case R.id.mregistrarUsuario:
                i=new Intent(MainActivity.this,ConfigurarCuentaActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.mNotificaciones:
                SharedPreferences sp=getSharedPreferences("TOKEN_FIREBASE",MODE_PRIVATE);
                String token=sp.getString("TOKEN","no hay token");
                sp=getSharedPreferences("Cuenta",MODE_PRIVATE);
                String usuarioActual=sp.getString("Usuario","no hay usuario");
                if(token!="no hay token"&&usuarioActual!="no hay usuario"){
                    RestAdapter adapter=new RestAdapter();
                    Endpoints endpoints=adapter.establecerConexionFirebase();
                    Call<UsuarioResponse> respuesta=endpoints.registrarUsuario(token,usuarioActual);
                    respuesta.enqueue(new Callback<UsuarioResponse>() {
                        @Override
                        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                         UsuarioResponse usuarioResponse=response.body();
                            Toast.makeText(MainActivity.this, "Se han activado las notificaciones para el dispositivo", Toast.LENGTH_LONG).show();
                            SharedPreferences sp=getSharedPreferences("CUENTA_ACTUAL", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("ID",usuarioResponse.getId());
                            editor.putString("ID_DISPOSITIVO",usuarioResponse.getId_dispositivo());
                            editor.putString("ID_USUARIO_INSTAGRAM",usuarioResponse.getId_usuario_instagram());
                            editor.commit();
                        }

                        @Override
                        public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Hubo un error, reintenta despues", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(this, "No se ha registrado un usuario", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return true;
    }
}

