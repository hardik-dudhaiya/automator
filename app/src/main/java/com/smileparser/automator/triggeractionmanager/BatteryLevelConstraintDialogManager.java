package com.smileparser.automator.triggeractionmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.SeekBar;

import com.smileparser.automator.R;
import com.smileparser.automator.databinding.BatteryLevelConstraintDialogBinding;

/**
 * Created by mayur on 7/10/18.
 */

public class BatteryLevelConstraintDialogManager extends DialogManager {

    Context context;

    public BatteryLevelConstraintDialogManager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void show() {
        showInputDialog();
    }

    void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final BatteryLevelConstraintDialogBinding batteryLevelConstraintDialogBinding = DataBindingUtil.
                inflate(LayoutInflater.from(context), R.layout.battery_level_constraint_dialog, null, false);

        batteryLevelConstraintDialogBinding.percentSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                batteryLevelConstraintDialogBinding.progressTextView.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(batteryLevelConstraintDialogBinding.getRoot());
        builder.setTitle("Battery level should be");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                int batteryLevelValue = Integer.parseInt(String.valueOf(batteryLevelConstraintDialogBinding.percentSeek.getProgress()));
                int option = 0;
                switch (batteryLevelConstraintDialogBinding.conditionRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.less_than_radio:
                        option = 1;
                        break;

                    case R.id.greater_than_radio:
                        option = 2;
                        break;

                    case R.id.equal_radio:
                        option = 3;
                        break;
                }
                addConstraintEvent(1, new EventValue(option, String.valueOf(batteryLevelValue)));
            }
        });
        builder.create().show();
    }
}
