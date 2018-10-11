package pe.edu.upeu.loginprefe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText edtNombre;
private EditText edtPass;
private Button btnin;
private CheckBox check;
private SharedPreferences sharedPref;
private Boolean ATVRDBTN;
private static final String STRING_PREFERENCE = "Adiel","123";
private static final String PREFERENCE_ESTADO_SESION= "estado.button.sesion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ObtenerEstadocheck()){
            Intent i = new Intent(MainActivity.this,hola.class);
            startActivity(i);
        }

        edtNombre=(EditText)findViewById(R.id.edtuser);
        edtPass=(EditText)findViewById(R.id.edtpass);
        btnin=(Button)findViewById(R.id.btning);
        check =(CheckBox)findViewById(R.id.checkBox);
        ATVRDBTN = check.isChecked();//desactivado
        btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtNombre.getText().toString();
                String contraseña = edtPass.getText().toString();
                saveUser();

                if (validarString(nombre)&& validarString(contraseña)){

                }else{
                    Toast.makeText(getApplicationContext(),"DATOS INCOMPLETOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        public void Guardarestadodelcheck(){
           SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
           preferences.edit().putBoolean(PREFERENCE_ESTADO_SESION,check.isChecked()).apply();
        }
        public void ObtenerEstadocheck(){
            SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
            return preferences.getBoolean(PREFERENCE_ESTADO_SESION,false);
        }
        getUser();
    }
    private void saveUser(){
        String nombre = edtNombre.getText().toString();
        String contraseña = edtPass.getText().toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.nombre_key), nombre);
        editor.putString(getString(R.string.password_key), contraseña);
        editor.commit();
    }
    private void getUser(){
        String nombre= sharedPref.getString(("nombrekey"),"");
        edtNombre.setText(nombre);
        String contraseña = sharedPref.getString(("contraseñakey"),"");
        edtPass.setText(contraseña);
    }
    Boolean validarString(String texto){
        return  texto!= null && texto.trim().length()>0;
    }
}
