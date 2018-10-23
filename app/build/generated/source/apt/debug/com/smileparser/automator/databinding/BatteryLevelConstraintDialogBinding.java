package com.smileparser.automator.databinding;
import com.smileparser.automator.R;
import com.smileparser.automator.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BatteryLevelConstraintDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.condition_radio_group, 1);
        sViewsWithIds.put(R.id.less_than_radio, 2);
        sViewsWithIds.put(R.id.greater_than_radio, 3);
        sViewsWithIds.put(R.id.equal_radio, 4);
        sViewsWithIds.put(R.id.progress_text_view, 5);
        sViewsWithIds.put(R.id.percent_seek, 6);
    }
    // views
    @NonNull
    public final android.widget.RadioGroup conditionRadioGroup;
    @NonNull
    public final android.widget.RadioButton equalRadio;
    @NonNull
    public final android.widget.RadioButton greaterThanRadio;
    @NonNull
    public final android.widget.RadioButton lessThanRadio;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.AppCompatSeekBar percentSeek;
    @NonNull
    public final android.widget.TextView progressTextView;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BatteryLevelConstraintDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.conditionRadioGroup = (android.widget.RadioGroup) bindings[1];
        this.equalRadio = (android.widget.RadioButton) bindings[4];
        this.greaterThanRadio = (android.widget.RadioButton) bindings[3];
        this.lessThanRadio = (android.widget.RadioButton) bindings[2];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.percentSeek = (android.support.v7.widget.AppCompatSeekBar) bindings[6];
        this.progressTextView = (android.widget.TextView) bindings[5];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BatteryLevelConstraintDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryLevelConstraintDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BatteryLevelConstraintDialogBinding>inflate(inflater, com.smileparser.automator.R.layout.battery_level_constraint_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BatteryLevelConstraintDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryLevelConstraintDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.smileparser.automator.R.layout.battery_level_constraint_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static BatteryLevelConstraintDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryLevelConstraintDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/battery_level_constraint_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BatteryLevelConstraintDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}