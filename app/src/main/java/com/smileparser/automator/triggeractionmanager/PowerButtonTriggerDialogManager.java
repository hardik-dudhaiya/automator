package com.smileparser.automator.triggeractionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.smileparser.automator.R;
import com.smileparser.automator.databinding.BatterySaverTriggerDialogBinding;
import com.smileparser.automator.databinding.PowerButtonTriggerDialogBinding;

/**
 * Created by mayur on 8/10/18.
 */

public class PowerButtonTriggerDialogManager extends DialogManager {

    public PowerButtonTriggerDialogManager(Context context) {
        super(context);
    }

    @Override
    public void show() {
        showPowerButtonDialog();
    }

    private void showPowerButtonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Option");
        final PowerButtonTriggerDialogBinding powerButtonTriggerDialogBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.power_button_trigger_dialog,
                        null, false);
        builder.setView(powerButtonTriggerDialogBinding.getRoot());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                int option = PowerButtonTrigger.THREE_TIMES;
                if (powerButtonTriggerDialogBinding.fourTimes.isChecked()) {
                    option = PowerButtonTrigger.FOUR_TIMES;
                } else if (powerButtonTriggerDialogBinding.fiveTimes.isChecked()) {
                    option = PowerButtonTrigger.FIVE_TIMES;
                }
                addTriggerEvent(3, new EventValue(option, null));
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
