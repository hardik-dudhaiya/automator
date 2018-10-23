
package android.databinding;
import com.smileparser.automator.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.smileparser.automator.R.layout.battery_increase_decrease_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/battery_increase_decrease_dialog_0".equals(tag)) {
                            return new com.smileparser.automator.databinding.BatteryIncreaseDecreaseDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for battery_increase_decrease_dialog is invalid. Received: " + tag);
                }
                case com.smileparser.automator.R.layout.power_connection_trigger_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/power_connection_trigger_dialog_0".equals(tag)) {
                            return new com.smileparser.automator.databinding.PowerConnectionTriggerDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for power_connection_trigger_dialog is invalid. Received: " + tag);
                }
                case com.smileparser.automator.R.layout.power_button_trigger_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/power_button_trigger_dialog_0".equals(tag)) {
                            return new com.smileparser.automator.databinding.PowerButtonTriggerDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for power_button_trigger_dialog is invalid. Received: " + tag);
                }
                case com.smileparser.automator.R.layout.battery_saver_trigger_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/battery_saver_trigger_dialog_0".equals(tag)) {
                            return new com.smileparser.automator.databinding.BatterySaverTriggerDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for battery_saver_trigger_dialog is invalid. Received: " + tag);
                }
                case com.smileparser.automator.R.layout.battery_level_constraint_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/battery_level_constraint_dialog_0".equals(tag)) {
                            return new com.smileparser.automator.databinding.BatteryLevelConstraintDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for battery_level_constraint_dialog is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 959852340: {
                if(tag.equals("layout/battery_increase_decrease_dialog_0")) {
                    return com.smileparser.automator.R.layout.battery_increase_decrease_dialog;
                }
                break;
            }
            case 1266043650: {
                if(tag.equals("layout/power_connection_trigger_dialog_0")) {
                    return com.smileparser.automator.R.layout.power_connection_trigger_dialog;
                }
                break;
            }
            case 409079214: {
                if(tag.equals("layout/power_button_trigger_dialog_0")) {
                    return com.smileparser.automator.R.layout.power_button_trigger_dialog;
                }
                break;
            }
            case -1030256639: {
                if(tag.equals("layout/battery_saver_trigger_dialog_0")) {
                    return com.smileparser.automator.R.layout.battery_saver_trigger_dialog;
                }
                break;
            }
            case 930614409: {
                if(tag.equals("layout/battery_level_constraint_dialog_0")) {
                    return com.smileparser.automator.R.layout.battery_level_constraint_dialog;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}