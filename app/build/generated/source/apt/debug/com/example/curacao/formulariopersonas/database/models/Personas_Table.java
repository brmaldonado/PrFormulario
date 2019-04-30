package com.example.curacao.formulariopersonas.database.models;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.saveable.AutoIncrementModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Number;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class Personas_Table extends ModelAdapter<Personas> {
  /**
   * Primary Key AutoIncrement */
  public static final Property<Integer> id = new Property<Integer>(Personas.class, "id");

  public static final Property<String> nombre = new Property<String>(Personas.class, "nombre");

  public static final Property<Integer> edad = new Property<Integer>(Personas.class, "edad");

  public static final Property<String> ocupacion = new Property<String>(Personas.class, "ocupacion");

  public static final Property<String> email = new Property<String>(Personas.class, "email");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,nombre,edad,ocupacion,email};

  public Personas_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<Personas> getModelClass() {
    return Personas.class;
  }

  @Override
  public final String getTableName() {
    return "`Personas`";
  }

  @Override
  public final Personas newInstance() {
    return new Personas();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`nombre`":  {
        return nombre;
      }
      case "`edad`":  {
        return edad;
      }
      case "`ocupacion`":  {
        return ocupacion;
      }
      case "`email`":  {
        return email;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final void updateAutoIncrement(Personas model, Number id) {
    model.id = id.intValue();
  }

  @Override
  public final Number getAutoIncrementingId(Personas model) {
    return model.id;
  }

  @Override
  public final String getAutoIncrementingColumnName() {
    return "id";
  }

  @Override
  public final ModelSaver<Personas> createSingleModelSaver() {
    return new AutoIncrementModelSaver<>();
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, Personas model) {
    values.put("`nombre`", model.nombre);
    values.put("`edad`", model.edad);
    values.put("`ocupacion`", model.ocupacion);
    values.put("`email`", model.email);
  }

  @Override
  public final void bindToContentValues(ContentValues values, Personas model) {
    values.put("`id`", model.id);
    bindToInsertValues(values, model);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Personas model, int start) {
    statement.bindStringOrNull(1 + start, model.nombre);
    statement.bindLong(2 + start, model.edad);
    statement.bindStringOrNull(3 + start, model.ocupacion);
    statement.bindStringOrNull(4 + start, model.email);
  }

  @Override
  public final void bindToStatement(DatabaseStatement statement, Personas model) {
    int start = 0;
    statement.bindLong(1 + start, model.id);
    bindToInsertStatement(statement, model, 1);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Personas model) {
    statement.bindLong(1, model.id);
    statement.bindStringOrNull(2, model.nombre);
    statement.bindLong(3, model.edad);
    statement.bindStringOrNull(4, model.ocupacion);
    statement.bindStringOrNull(5, model.email);
    statement.bindLong(6, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Personas model) {
    statement.bindLong(1, model.id);
  }

  @Override
  public final String getInsertStatementQuery() {
    return "INSERT INTO `Personas`(`nombre`,`edad`,`ocupacion`,`email`) VALUES (?,?,?,?)";
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Personas`(`id`,`nombre`,`edad`,`ocupacion`,`email`) VALUES (?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Personas` SET `id`=?,`nombre`=?,`edad`=?,`ocupacion`=?,`email`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Personas` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Personas`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `nombre` TEXT, `edad` INTEGER, `ocupacion` TEXT, `email` TEXT)";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Personas model) {
    model.id = cursor.getIntOrDefault("id");
    model.nombre = cursor.getStringOrDefault("nombre");
    model.edad = cursor.getIntOrDefault("edad");
    model.ocupacion = cursor.getStringOrDefault("ocupacion");
    model.email = cursor.getStringOrDefault("email");
  }

  @Override
  public final boolean exists(Personas model, DatabaseWrapper wrapper) {
    return model.id > 0
    && SQLite.selectCountOf()
    .from(Personas.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Personas model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
