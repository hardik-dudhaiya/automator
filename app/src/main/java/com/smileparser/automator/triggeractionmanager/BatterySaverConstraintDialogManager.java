package com.smileparser.automator.triggeractionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.smileparser.automator.R;
import com.smileparser.automator.databinding.BatterySaverTriggerDialogBinding;

/**
 * Created by mayur on 7/10/18.
 */

public class BatterySaverConstraintDialogManager extends DialogManager {

    Context context;

    public BatterySaverConstraintDialogManager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void show() {
        showBatterySaverDialog();
    }

    private void showBatterySaverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Option");
        final BatterySaverTriggerDialogBinding batterySaverTriggerDialogBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.battery_saver_trigger_dialog,
                        null, false);
        builder.setView(batterySaverTriggerDialogBinding.getRoot());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                int option = batterySaverTriggerDialogBinding.enabledRadio.isChecked() ?
                        BatterySaverTrigger.BATTERY_SAVER_ENABLED : BatterySaverTrigger.BATTERY_SAVER_DISABLED;
                addConstraintEvent(2, new EventValue(option, null));
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
