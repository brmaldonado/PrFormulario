package com.example.curacao.formulariopersonas.database.models;

import com.example.curacao.formulariopersonas.database.personasdb;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = personasdb.class)
public class Persona extends BaseModel {
    @Column
    @PrimaryKey(autoincrement =  true)
    public  int id;
}
