package com.smileparser.automator.db_helper;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smileparser.automator.utils.Utility;

import java.io.Serializable;

@DatabaseTable(tableName = TriggerModel.TABLE_NAME)
public class TriggerModel implements Serializable {
    static final String TABLE_NAME = "TriggerModel";

    @DatabaseField(columnName = "tId", generatedId = true, allowGeneratedIdInsert = true)
    @SerializedName("Id")
    private Long Id;

    @DatabaseField(columnName = "tEventValue", persisterClass = SerializableCollectionsType.class)
    @SerializedName("tValue")
    private EventValueModel tValue;

    @DatabaseField(columnName = "tTriggerId")
    @SerializedName("TriggerId")
    private Long TriggerId;

    @DatabaseField(columnName = "tMacroId")
    @SerializedName("MacroId")
    private Long MacroId;

    public TriggerModel() {
    }

    public Long getId() {
        if (!Utility.IsNotNull(Id)) {
            return Id;
        } else {
            return 0L;
        }
    }

    public EventValueModel gettValue() {
        return tValue;
    }

    public void settValue(EventValueModel tValue) {
        this.tValue = tValue;
    }

    public Long getTriggerId() {
        return TriggerId;
    }

    public void setTriggerId(Long triggerId) {
        TriggerId = triggerId;
    }

    public Long getMacroId() {
        return MacroId;
    }

    public void setMacroId(Long macroId) {
        MacroId = macroId;
    }
}