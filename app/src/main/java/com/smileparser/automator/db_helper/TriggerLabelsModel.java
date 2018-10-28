package com.smileparser.automator.db_helper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smileparser.automator.utils.Utility;

import java.io.Serializable;

@DatabaseTable(tableName = TriggerLabelsModel.TABLE_NAME)
public class TriggerLabelsModel implements Serializable {

    static final String TABLE_NAME = "triggerLabelModel";

    @DatabaseField(columnName = "tLId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "tLName")
    private String Name;

    public TriggerLabelsModel() {
    }

    public Long getId() {
        if (Utility.IsNotNull(Id))
            return Id;
        else
            return 0L;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        if (Utility.IsNotNull(Name))
            return Name;
        else
            return "";
    }

    public void setName(String name) {
        Name = name;
    }

}
