package com.example.pruebaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ListarUsuario extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);
        listView = (ListView) findViewById(R.id.lv_list);

        CargarListado();

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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

}
