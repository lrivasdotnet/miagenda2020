package net.lrivas.miagenda;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.lrivas.miagenda.clases.ConexionSQLite;

public class RegistrarContacto extends AppCompatActivity {
    ConexionSQLite objConexion;
    final String NOMBRE_BASE_DATOS = "miagenda";
    EditText nombre, telefono;
    Button botonAgregar, botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contacto);
        objConexion = new ConexionSQLite(RegistrarContacto.this,NOMBRE_BASE_DATOS,null,1);
        nombre = (EditText) findViewById(R.id.txtNombreCompleto);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        botonAgregar = (Button) findViewById(R.id.btnGuardarContacto);
        botonRegresar = (Button) findViewById(R.id.btnRegresar);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar() {
        try{
            SQLiteDatabase miBaseDatos = objConexion.getWritableDatabase();
            String comando = "INSERT INTO contactos (nombre,telefono) VALUES " +
                    "('"+ nombre.getText() +"','"+ telefono.getText() +"')";
            miBaseDatos.execSQL(comando);
            miBaseDatos.close();
            Toast.makeText(RegistrarContacto.this, "Datos Registrados con exito", Toast.LENGTH_SHORT).show();
        }catch (Exception error){
            Toast.makeText(RegistrarContacto.this, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void regresar() {
        Intent actividad = new Intent(RegistrarContacto.this, MainActivity.class);
        startActivity(actividad);
        RegistrarContacto.this.finish();
    }
}
