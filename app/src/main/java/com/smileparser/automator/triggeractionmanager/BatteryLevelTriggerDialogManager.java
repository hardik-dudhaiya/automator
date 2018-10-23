package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.smileparser.automator.Activity.CreateActivity;
import com.smileparser.automator.R;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.databinding.BatteryIncreaseDecreaseDialogBinding;

/**
 * Created by mayur on 7/10/18.
 */

public class BatteryLevelTriggerDialogManager extends DialogManager {

    Context context;

    public BatteryLevelTriggerDialogManager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void show() {
        showTriggerOptionsDialog();
    }

    void showTriggerOptionsDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setTitle("Select Option");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
        arrayAdapter.add("Increase/Decrease");
        arrayAdapter.add("Any change");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        showInputDialog(1);
                        break;

                    case 1:
                        addTriggerEvent(1, new EventValue(BatteryLevelTrigger.BATTERY_LEVEL_ANY, null));
                        break;
                }
            }
        });
        builderSingle.show();
    }

    void showInputDialog(final int triggerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final BatteryIncreaseDecreaseDialogBinding batteryIncreaseDecreaseDialogBinding = DataBindingUtil.
                inflate(LayoutInflater.from(context), R.layout.battery_increase_decrease_dialog, null, false);

        batteryIncreaseDecreaseDialogBinding.percentSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                batteryIncreaseDecreaseDialogBinding.progressTextView.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setView(batteryIncreaseDecreaseDialogBinding.getRoot());
        builder.setTitle("Increases/decreases to");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                int batteryLevelValue = Integer.parseInt(String.valueOf(batteryIncreaseDecreaseDialogBinding.percentSeek.getProgress()));
                int option = batteryIncreaseDecreaseDialogBinding.increasesRadioButton.isChecked() ? 1 : 2;
                addTriggerEvent(triggerId, new EventValue(option, String.valueOf(batteryLevelValue)));
            }
        });
        builder.create().show();
    }
}
