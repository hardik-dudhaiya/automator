package com.smileparser.automator.db_helper;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = MacroModel.TABLE_NAME, daoClass = CustomDao.class)
public class MacroModel {

    static final String TABLE_NAME = "MacroModel";

    @DatabaseField(columnName = "mId", generatedId = true, allowGeneratedIdInsert = true)
    private Long Id;

    @DatabaseField(columnName = "mIsActive")
    private boolean isActive;

    @DatabaseField(columnName = "mTriggerDetails")
    private TriggerModel triggerModel;

    @DatabaseField(columnName = "mActionDetails")
    private ActionModel actionModel;

    @DatabaseField(columnName = "mConstraintDetail")
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
