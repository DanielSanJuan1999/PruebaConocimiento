package com.example.pruebaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BorrarUsuario extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;
    Button btn_borrar;
    int id;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_usuario);
        listView = (ListView) findViewById(R.id.lv_borrar);

        btn_borrar = (Button) findViewById(R.id.btn_borrar);

        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });
    }

    private void CargarListado(){
        listado = ListaUsuario();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaUsuario(){
        ArrayList<String> datos = new ArrayList<String>();

        BaseHelper helper = new BaseHelper(this,"demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT ID, NOMBRE, APELLIDO FROM USUARIO";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do{
                String Linea = c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                datos.add(Linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }

    private void Eliminar(int Id){
        BaseHelper helper = new BaseHelper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            String sql ="DELETE FROM USUARIO WHERE ID="+Id;
            db.execSQL(sql);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
