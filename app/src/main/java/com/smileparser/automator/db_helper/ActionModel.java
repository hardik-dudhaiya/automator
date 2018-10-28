package com.smileparser.automator.db_helper;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = ActionModel.TABLE_NAME, daoClass = CustomDao.class)
public class ActionModel implements  Serializable{

    static final String TABLE_NAME = "ActionModel";

    @DatabaseField(columnName = "aId", generatedId = true, allowGeneratedIdInsert = true)
    @SerializedName("Id")
    private Long Id;

    @DatabaseField(columnName = "aMacroId")
    @SerializedName("macroId")
    private Long macroId;

    @DatabaseField(columnName = "aActionId")
    @SerializedName("actionId")
    private Long actionId;

    @DatabaseField(columnName = "tEventValue", persisterClass = SerializableCollectionsType.class)
    @SerializedName("tValue")
    private EventValueModel tValue;

    public ActionModel() {
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
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public EventValueModel gettValue() {
        return tValue;
    }

    public void settValue(EventValueModel tValue) {
        this.tValue = tValue;
    }
}
