package com.example.pruebaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarUsuario extends AppCompatActivity {

    EditText pt_Nombre;
    EditText pt_Apellido;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        pt_Nombre = (EditText) findViewById(R.id.pt_Nombre);
        pt_Apellido = (EditText) findViewById(R.id.pt_Apellido);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Guardar(pt_Nombre.getText().toString(), pt_Apellido.getText().toString());
            }
        });

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    private void Guardar(String Nombre, String Apellido){
        BaseHelper helper = new BaseHelper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Apellido",Apellido);
            db.insert("USUARIO",null,c);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
