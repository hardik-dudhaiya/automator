package com.smileparser.automator.databinding;
import com.smileparser.automator.R;
import com.smileparser.automator.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BatteryIncreaseDecreaseDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.increases_radio_button, 1);
        sViewsWithIds.put(R.id.decreases_radio_button, 2);
        sViewsWithIds.put(R.id.progress_text_view, 3);
        sViewsWithIds.put(R.id.percent_seek, 4);
        sViewsWithIds.put(R.id.seek_bar, 5);
    }
    // views
    @NonNull
    public final android.widget.RadioButton decreasesRadioButton;
    @NonNull
    public final android.widget.RadioButton increasesRadioButton;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.AppCompatSeekBar percentSeek;
    @NonNull
    public final android.widget.TextView progressTextView;
    @NonNull
    public final android.widget.SeekBar seekBar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BatteryIncreaseDecreaseDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.decreasesRadioButton = (android.widget.RadioButton) bindings[2];
        this.increasesRadioButton = (android.widget.RadioButton) bindings[1];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.percentSeek = (android.support.v7.widget.AppCompatSeekBar) bindings[4];
        this.progressTextView = (android.widget.TextView) bindings[3];
        this.seekBar = (android.widget.SeekBar) bindings[5];
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
    public static BatteryIncreaseDecreaseDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryIncreaseDecreaseDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BatteryIncreaseDecreaseDialogBinding>inflate(inflater, com.smileparser.automator.R.layout.battery_increase_decrease_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BatteryIncreaseDecreaseDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryIncreaseDecreaseDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.smileparser.automator.R.layout.battery_increase_decrease_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static BatteryIncreaseDecreaseDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BatteryIncreaseDecreaseDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/battery_increase_decrease_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BatteryIncreaseDecreaseDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}