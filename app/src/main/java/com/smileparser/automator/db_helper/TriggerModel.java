package com.smileparser.automator.db_helper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smileparser.automator.utils.Utility;

@DatabaseTable(tableName = TriggerModel.TABLE_NAME, daoClass = CustomDao.class)
public class TriggerModel {
    static final String TABLE_NAME = "TriggerModel";

    @DatabaseField(columnName = "tId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "tEventValue", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private EventValueModel tValue;

    @DatabaseField(columnName = "tTriggerId")
    private Long TriggerId;

    @DatabaseField(columnName = "tMacroId")
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