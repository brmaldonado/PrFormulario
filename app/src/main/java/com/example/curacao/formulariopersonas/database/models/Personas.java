package com.example.curacao.formulariopersonas.database.models;

import com.example.curacao.formulariopersonas.database.personasdb;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Locale;

@Table(database = personasdb.class)
public class Personas extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String nombre;

    @Column
    public int edad;

    @Column
    public String ocupacion;

    @Column
    public String email;


    public String toString() {
        return String.format(Locale.getDefault(), "Nombre: %s\nEdad: %d\nOcupacion: %s\nEmail: %s\n", this.nombre, this.edad, this.ocupacion, this.email);
    }
}
