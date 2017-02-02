package mx.grupogarcia.soportewear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sin_favos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.mContacto:
                i=new Intent(ConfigurarCuentaActivity.this,ContactoActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.mAbout:
                i=new Intent(ConfigurarCuentaActivity.this,AcercaDeActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }

    public void guardarCuenta(View v){
        TextInputEditText cuenta=(TextInputEditText) findViewById(R.id.usuario);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario=cuenta.getText().toString();
                SharedPreferences cuentaGuardada=getSharedPreferences("Cuenta", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=cuentaGuardada.edit();
                editor.putString("Usuario",usuario);
                editor.commit();
                Toast.makeText(ConfigurarCuentaActivity.this, "Cuenta guardada con exito", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(ConfigurarCuentaActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
