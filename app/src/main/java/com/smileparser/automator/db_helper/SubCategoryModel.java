package com.smileparser.automator.db_helper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smileparser.automator.utils.Utility;

import java.io.Serializable;

@DatabaseTable(tableName = SubCategoryModel.TABLE_NAME)
public class SubCategoryModel implements Serializable {

    static final String TABLE_NAME = "subCategoryModel";

    @DatabaseField(columnName = "sId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "cId")
    private Long cId;

    @DatabaseField(columnName = "sName")
    private String Name;

    public SubCategoryModel() {
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
