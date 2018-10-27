package com.smileparser.automator.db_helper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = ConstraintModel.TABLE_NAME, daoClass = CustomDao.class)
public class ConstraintModel implements Serializable {

    static final String TABLE_NAME = "ConstraintModel";

    @DatabaseField(columnName = "cId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "cMacroId")
    private Long macroId;

    @DatabaseField(columnName = "cConstraintId")
    private Long constraintId;

    @DatabaseField(columnName = "cEventValue")
    private EventValueModel tValue;

    public ConstraintModel() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getMacroId() {
        return macroId;
    }

    public void setMacroId(Long macroId) {
        this.macroId = macroId;
    }

    public Long getActionId() {
        return constraintId;
    }

    public void setActionId(Long actionId) {
        this.constraintId = actionId;
    }

    public EventValueModel gettValue() {
        return tValue;
    }

    public void settValue(EventValueModel tValue) {
        this.tValue = tValue;
    }
}
