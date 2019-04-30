package com.example.curacao.formulariopersonas.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.curacao.formulariopersonas.R;
import com.example.curacao.formulariopersonas.database.models.Personas;
import com.example.curacao.formulariopersonas.database.models.Personas_Table;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Personas> {

    private List<Personas> dataSet;
    Context mContext;
    CoordinatorLayout view;

    // View lookup cache
    private static class ViewHolder {
        TextView txtPersonas;
        ImageButton delete;
        ImageView update;
        //Personas per;
    }

    public CustomAdapter(List<Personas> data, Context context, CoordinatorLayout l ) {
        super(context, R.layout.item, data);
        this.dataSet = data;
        this.mContext = context;
        this.view = l;

    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Personas dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
            viewHolder.txtPersonas = convertView.findViewById(R.id.text);
            viewHolder.delete = convertView.findViewById(R.id.delete);
            viewHolder.update = convertView.findViewById(R.id.imagen);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtPersonas.setText(dataModel.toString());
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog(dataModel);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataModel.delete();
                dataSet.remove(dataModel);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Se elimino el registro ", Toast.LENGTH_LONG).show();

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }


    public void mostrarDialog(final Personas per) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        //Se crea la variable local
        View formulario = layoutInflater.inflate(R.layout.formulario, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //Muestra el formulario para agregar un nuevo registro de arbolitos
        builder.setView(formulario);
        //Declaracion y conexion de variables
        final TextInputLayout nombre = formulario.findViewById(R.id.nombre);
        nombre.getEditText().setText(per.nombre);

        final TextInputLayout edad = formulario.findViewById(R.id.edad);
        edad.getEditText().setText(per.edad);

        final TextInputLayout ocupacion = formulario.findViewById(R.id.ocupacion);
        ocupacion.getEditText().setText(per.ocupacion);

        final TextInputLayout email = formulario.findViewById(R.id.email);
        email.getEditText().setText(per.email);

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
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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

    //Método que nos permite validad la informacion ingresada por el usuario
    //valida que los campos no esten vacíos
    //Recibe como parametros los campos altura, fecha de siembra, revision y encargado
    //Captura el error de un campo vacío y muestra el mensaje al usuario para indicarle
    //que informacion hace falta ingresar
    private void validate(TextInputLayout n, TextInputLayout e, TextInputLayout o, TextInputLayout c) throws Exception {
        if (n.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir el nombre");
        }
        if (e.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir la edad");
        }
        if (o.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir la ocupacion");
        }
        if (c.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Necesita escribir el correo electrónico");
        }
    }
    //Método que permite guardar la informacion y crear el registro de un nuevo arbolito
    //Hace la conexion con la base de datos para poder almacenarlos alli
    private void guardarBD(TextInputLayout n, TextInputLayout e, TextInputLayout o, TextInputLayout c, Personas per) {
        per.nombre = n.getEditText().getText().toString();
        per.edad = Integer.parseInt(e.getEditText().getText().toString());
        per.ocupacion = o.getEditText().getText().toString();
        per.email = c.getEditText().getText().toString();
        per.save();

        //Muestra el mensaje que confirma que los datos se han guardado
        Snackbar.make(view, "Se ha guardado el registro", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


        notifyDataSetChanged();


    }
}
