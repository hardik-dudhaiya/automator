package com.smileparser.automator.triggeractionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.smileparser.automator.R;
import com.smileparser.automator.databinding.PowerConnectionTriggerDialogBinding;

/**
 * Created by mayur on 8/10/18.
 */

public class PowerConnectionConstraintDialogManager extends DialogManager {

    public PowerConnectionConstraintDialogManager(Context context) {
        super(context);
    }

    @Override
    public void show() {
        showPowerConnectionDialog();
    }

    private void showPowerConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Option");
        final PowerConnectionTriggerDialogBinding powerConnectionTriggerDialogBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.power_connection_trigger_dialog,
                        null, false);
        builder.setView(powerConnectionTriggerDialogBinding.getRoot());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                int option = powerConnectionTriggerDialogBinding.powerConnected.isChecked() ?
                        PowerConnectionTrigger.POWER_CONNECTED : PowerConnectionTrigger.POWER_DISCONNECTED;
                addConstraintEvent(3, new EventValue(option, null));
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
