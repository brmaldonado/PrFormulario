package com.example.curacao.formulariopersonas.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.curacao.formulariopersonas.R;
import com.example.curacao.formulariopersonas.database.models.Personas;
import com.example.curacao.formulariopersonas.database.models.Personas_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //declaracion de variable que conectará con el diseño con la vista
    private CoordinatorLayout view;
    //Declaracion de la variable que mostrará la lista de arboles guardados
    private ListView lista;
    private Personas per;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //conexion
        lista = findViewById(R.id.lista);
        view = findViewById(R.id.coordinador);
        setAdapter();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialog();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
   private String[] getPersonas() {
        //crea la lista
        List<Personas> listado = SQLite.select().from(Personas.class).queryList();
        //Crea un array para gardar la lista
        String[] array = new String[listado.size()];
        //Guarda la informacion en el array
        for (int c = 0; c < listado.size(); c++) {
            array[c] = listado.get(c).toString();
        }
        return array;
    }
    private List<Personas> getListPersonas(){
        return  SQLite.select().from(Personas.class).queryList();
    }

    //Establecer adaptador
    private void setAdapter() {
        lista.setAdapter(new CustomAdapter(getListPersonas(), getApplicationContext(), view));
    }

    public void mostrarDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        //Se crea la variable local
        View formulario = layoutInflater.inflate(R.layout.formulario, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Muestra el formulario para agregar un nuevo registro de arbolitos
        builder.setView(formulario);
        //Declaracion y conexion de variables
        final TextInputLayout nombre = formulario.findViewById(R.id.nombre);
        final TextInputLayout edad = formulario.findViewById(R.id.edad);
        final TextInputLayout ocupacion = formulario.findViewById(R.id.ocupacion);
        final TextInputLayout email = formulario.findViewById(R.id.email);

        //Instrucciones para el usuario
        builder.setMessage("Rellena los campos requeridos")
                //Titulo del cuadro de dialogo
                .setTitle("Agregar persona")
                .setCancelable(false)
                //Al hacer clic en el boton aceptar el registro es guardado
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Try catch nos permite capturar y manipular los errores que se puedan dar
                        try {
                            //llama el metodo validate
                            validate(nombre, edad, ocupacion, email);
                            //llama el metodo guardarBD
                            guardarBD(nombre, edad, ocupacion, email, per);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        AlertDialog dialogo = builder.create();
        dialogo.show();
    }

    private void validate(TextInputLayout n, TextInputLayout e, TextInputLayout o, TextInputLayout c) throws Exception {
        if (n.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir la altura del arbolito");
        }
        if (e.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir la fecha de siembra del arbolito");
        }
        if (o.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir la fecha de revisión del arbolito");
        }
        if (c.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir el nombre del encargado");
        }
    }
    private void guardarBD(TextInputLayout n, TextInputLayout e, TextInputLayout o, TextInputLayout c, Personas per) {
        if (per == null)
            per = new Personas();
        per.nombre =n.getEditText().getText().toString();
        per.edad = Integer.parseInt(e.getEditText().getText().toString());
        per.ocupacion = o.getEditText().getText().toString();
        per.email = c.getEditText().getText().toString();
        per.save();

        //Muestra el mensaje que confirma que los datos se han guardado
        Snackbar.make(view, "Se ha guardado el registro", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


        setAdapter();


    }
    private void borrarPersona(){
        SQLite.delete().from(Personas.class).where(Personas_Table.edad.between(20).and(40)).execute();
        setAdapter();

        Snackbar.make(view, "Hemos borrado el registro", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
