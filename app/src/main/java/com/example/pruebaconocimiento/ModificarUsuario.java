package com.example.pruebaconocimiento;

import androidx.appcompat.app.AppCompatActivity;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarUsuario extends AppCompatActivity {

    EditText pt_Nombre;
    EditText pt_Apellido;
    Button btn_modificar;
    int id;
    String nombre;
    String apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);

        Bundle b = getIntent().getExtras();
        if (b!=null){
            id =b.getInt("Id");
            nombre = b.getString("Nombre");
            apellido = b.getString("Apellido");
        }

        pt_Nombre = (EditText) findViewById(R.id.pt_Nombre);
        pt_Apellido = (EditText) findViewById(R.id.pt_Apellido);
        btn_modificar = (Button) findViewById(R.id.btn_modificar);

        pt_Nombre.setText(nombre);
        pt_Apellido.setText(apellido);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, pt_Nombre.getText().toString(), pt_Apellido.getText().toString());
                onBackPressed();
            }
        });

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void Modificar(int Id, String Nombre, String Apellido){
        BaseHelper helper = new BaseHelper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql ="UPDATE USUARIO SET NOMBRE='"+Nombre+"',APELLIDO='"+Apellido+"' WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
