package com.smileparser.automator.db_helper;


import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

//@DatabaseTable(tableName = MacroModel.TABLE_NAME, daoClass = CustomDao.class)
@DatabaseTable(tableName = MacroModel.TABLE_NAME)
public class MacroModel implements Serializable {

    static final String TABLE_NAME = "MacroModel";

    @DatabaseField(columnName = "mId", generatedId = true, allowGeneratedIdInsert = true)
    @SerializedName("Id")
    private Long Id;

    @DatabaseField(columnName = "mIsActive")
    @SerializedName("isActive")
    private boolean isActive;

    @DatabaseField(columnName = "mTriggerDetails", persisterClass = SerializableCollectionsType.class)
    @SerializedName("triggerModel")
    private TriggerModel triggerModel;

    @DatabaseField(columnName = "mActionDetails", persisterClass = SerializableCollectionsType.class)
    @SerializedName("actionModel")
    private ActionModel actionModel;

    @DatabaseField(columnName = "mConstraintDetail", persisterClass = SerializableCollectionsType.class)
    @SerializedName("constraintModel")
    private ConstraintModel constraintModel;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public TriggerModel getTriggerModel() {
        return triggerModel;
    }

    public void setTriggerModel(TriggerModel triggerModel) {
        this.triggerModel = triggerModel;
    }

    public ActionModel getActionModel() {
        return actionModel;
    }

    public void setActionModel(ActionModel actionModel) {
        this.actionModel = actionModel;
    }

    public ConstraintModel getConstraintModel() {
        return constraintModel;
    }

    public void setConstraintModel(ConstraintModel constraintModel) {
        this.constraintModel = constraintModel;
    }
}
