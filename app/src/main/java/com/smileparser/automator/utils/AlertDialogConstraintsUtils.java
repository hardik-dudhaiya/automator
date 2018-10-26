package com.smileparser.automator.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.smileparser.automator.R;
import com.smileparser.automator.triggeractionmanager.EventManagementUtil;
import com.smileparser.automator.triggeractionmanager.EventValue;

import javax.annotation.Nullable;

public class AlertDialogConstraintsUtils {

    private static AlertDialog alertDialog;

    public static void showBatteryLevelConstraintDialog(Activity activity, @Nullable String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.battery_level_constraint_dialog, null);

        builder.setView(ItemView);

        SeekBar seekBar = ItemView.findViewById(R.id.seek_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView) ItemView.findViewById(R.id.progress_text_view)).setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setPositiveButton("Done", (dialogInterface, i) -> {
            int batteryLevelValue = Integer.parseInt(((TextView) ItemView.findViewById(R.id.progress_text_view)).getText().toString());
            int option = 0;

            switch (((RadioGroup) ItemView.findViewById(R.id.condition_radio_group)).getCheckedRadioButtonId()) {
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
            EventManagementUtil.addConstraintEvent(activity, 1, new EventValue(option, String.valueOf(batteryLevelValue)));
        });
        builder.create().show();
    }

    public interface okListener extends ItemClicks {
        void onOkClick();
    }

    public interface okCancelListener extends ItemClicks {
        void onOkClick();

        void onCancelClick();
    }

    public interface ItemClicks {
        void onItemClick(Object object);
    }
}