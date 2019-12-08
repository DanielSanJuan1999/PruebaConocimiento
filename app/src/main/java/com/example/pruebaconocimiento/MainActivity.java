package com.example.pruebaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnAgregar(View view){
        Intent agregar = new Intent(this, AgregarUsuario.class);
        startActivity(agregar);
    }
    public void BtnListar(View view){
      Intent listar = new Intent(this, ListarUsuario.class);
      startActivity(listar);
    }
    public void BtnEditar(View view){
        Intent editar = new Intent(this, EditarUsuario.class);
        startActivity(editar);
    }
    public void BtnBorrar(View view){
      Intent borrar = new Intent(this, BorrarUsuario.class);
      startActivity(borrar);
    }

}
