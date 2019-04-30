package com.raizlabs.android.dbflow.config;

import com.example.curacao.formulariopersonas.database.models.Persona_Table;
import com.example.curacao.formulariopersonas.database.models.Personas_Table;
import com.example.curacao.formulariopersonas.database.personasdb;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class personasdbpersonasdb_Database extends DatabaseDefinition {
  public personasdbpersonasdb_Database(DatabaseHolder holder) {
    addModelAdapter(new Persona_Table(this), holder);
    addModelAdapter(new Personas_Table(this), holder);
  }

  @Override
  public final Class<?> getAssociatedDatabaseClassFile() {
    return personasdb.class;
  }

  @Override
  public final boolean isForeignKeysSupported() {
    return false;
  }

  @Override
  public final boolean backupEnabled() {
    return false;
  }

  @Override
  public final boolean areConsistencyChecksEnabled() {
    return false;
  }

  @Override
  public final int getDatabaseVersion() {
    return 1;
  }

  @Override
  public final String getDatabaseName() {
    return "personasdb";
  }
}
