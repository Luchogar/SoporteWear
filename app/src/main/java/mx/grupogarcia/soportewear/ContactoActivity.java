package mx.grupogarcia.soportewear;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    private TextInputEditText nombre,correo,mensaje;
    private final static String EMAIL="alephcoursera@gmail.com";
    private final static String PASSWORD="petagram";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button enviar=(Button)findViewById(R.id.botonEnviar);
        nombre=(TextInputEditText)findViewById(R.id.nombreContacto);
        correo=(TextInputEditText)findViewById(R.id.correoContacto);
        mensaje=(TextInputEditText)findViewById(R.id.mensajeContacto);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailContacto=correo.getText().toString();
                String nombreContacto=nombre.getText().toString();
                String mensajeContacto=mensaje.getText().toString();

                if (emailContacto.equalsIgnoreCase("")||nombreContacto.equalsIgnoreCase("")||mensajeContacto.equalsIgnoreCase("")){
                    Toast.makeText(ContactoActivity.this,"Rellene todos los campos para poder contactarlo",Toast.LENGTH_LONG).show();

                }else {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    Session session = Session.getDefaultInstance(props,
                            new javax.mail.Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(EMAIL, PASSWORD);
                                }
                            });
                    try {
                        MimeMessage msg = new MimeMessage(session);
                        msg.setFrom(new InternetAddress(EMAIL));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailContacto));
                        msg.setSubject(getResources().getString(R.string.app_name) + "-" + getResources().getString(R.string.mContacto));
                        msg.setText(nombreContacto.toUpperCase() + "\n" + mensajeContacto, "utf-8");
                        Transport.send(msg);
                        Toast.makeText(ContactoActivity.this, "Enviado email a " + emailContacto, Toast.LENGTH_SHORT).show();
                        nombre.setText("");
                        mensaje.setText("");
                        correo.setText("");
                    } catch (Exception e) {
                        Toast.makeText(ContactoActivity.this, "Hubo un error al enviar el correo", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
