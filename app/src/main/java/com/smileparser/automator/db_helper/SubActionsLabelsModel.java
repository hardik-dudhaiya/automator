package com.smileparser.automator.db_helper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smileparser.automator.utils.Utility;

import java.io.Serializable;

@DatabaseTable(tableName = SubActionsLabelsModel.TABLE_NAME)
public class SubActionsLabelsModel implements Serializable {

    static final String TABLE_NAME = "subActionLabelModel";

    @DatabaseField(columnName = "sALId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "sAId")
    private Long cId;

    @DatabaseField(columnName = "sName")
    private String Name;

    public SubActionsLabelsModel() {
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

    public Long getcId() {
        if (Utility.IsNotNull(cId))
            return cId;
        else
            return 0L;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }
}
