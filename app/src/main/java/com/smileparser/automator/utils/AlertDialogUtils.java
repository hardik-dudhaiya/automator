package com.smileparser.automator.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.smileparser.automator.R;
import com.smileparser.automator.triggeractionmanager.EventManagementUtil;
import com.smileparser.automator.triggeractionmanager.EventValue;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class AlertDialogUtils {

    private static AlertDialog alertDialog;

    public static void dismisDialog() {
        if (alertDialog == null)
            return;
        alertDialog.cancel();
        alertDialog.dismiss();
    }

    public static void showAlertDialog(Activity activity, @Nullable String title, @Nullable ArrayAdapter adapter, okListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        if (adapter != null)
            builder.setAdapter(adapter, (dialog, which) -> okListener.onItemClick(which));

        builder.setPositiveButton("OK", (dialog, which) -> okListener.onOkClick());

        alertDialog = builder.create();

        alertDialog.setCancelable(true);

        alertDialog.show();
    }

    public static void showMultiChoiceDialog(Activity activity, @Nullable String title, @Nullable ArrayList<String> adapter, okListener okListener) {
        ArrayList<Integer> selectedItems = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);
        CharSequence[] dialogList = new CharSequence[0];
        if (adapter != null) {
            dialogList = adapter.toArray(new CharSequence[adapter.size()]);
        }

        builder.setMultiChoiceItems(dialogList, new boolean[dialogList.length], (dialog, which, isChecked) -> {
            if (isChecked) {
                selectedItems.add(which);
            } else if (selectedItems.contains(which)) {
                selectedItems.remove(Integer.valueOf(which));
            }
        });
        builder.setPositiveButton("OK", (dialog, which) -> {
            StringBuilder selectedIndex = new StringBuilder();
            for (Integer i : selectedItems) {
                selectedIndex.append(i).append(", ");
            }
            okListener.onItemClick(selectedIndex);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
            dialog.dismiss();
        });

        alertDialog = builder.create();

        alertDialog.setCancelable(true);

        alertDialog.show();
    }

    public static void showOkCancelAlertDialog(Activity activity, @Nullable String title, @Nullable String message, ArrayAdapter adapter, okCancelListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        if (message != null)
            builder.setMessage(title);

        builder.setAdapter(adapter, (dialog, which) -> okListener.onItemClick(adapter.getItem(which)));

        builder.setPositiveButton("OK", (dialog, which) -> okListener.onOkClick());

        builder.setNegativeButton("CANCEL", (dialog, which) -> okListener.onCancelClick());

        alertDialog = builder.create();

        alertDialog.setCancelable(true);

        alertDialog.show();
    }

    public static void showBatteryLevelTriggerDialog(Activity activity, @Nullable String title, int triggerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.battery_increase_decrease_dialog, null);

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
            dialogInterface.dismiss();
            int batteryLevelValue = Integer.parseInt(((TextView) ItemView.findViewById(R.id.progress_text_view)).getText().toString());
            int option = ((RadioButton) ItemView.findViewById(R.id.increases_radio_button)).isChecked() ? 1 : 2;
            EventManagementUtil.addTriggerEvent(activity, triggerId, new EventValue(option, String.valueOf(batteryLevelValue)));
        });
        builder.create().show();
    }

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

    public static void showDialPhoneDialog(Activity activity, @Nullable String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_dial_phone, null);

        builder.setView(ItemView);

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            if (TextUtils.isEmpty(((EditText) ItemView.findViewById(R.id.edt_phone_number)).getText().toString().trim())) {
                return;
            }
            dialogInterface.dismiss();
            int batteryLevelValue = Integer.parseInt(((TextView) ItemView.findViewById(R.id.progress_text_view)).getText().toString());
            int option = ((RadioGroup) ItemView.findViewById(R.id.condition_radio_group)).getCheckedRadioButtonId() == R.id.make_call ? 1 : 2;
            //EventManagementUtil.addTriggerEvent(activity, triggerId, new EventValue(option, String.valueOf(batteryLevelValue)));
        });
        builder.create().show();
    }

    public static void showLocationChangeDialog(Activity activity, @Nullable String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_location_trigger, null);

        builder.setView(ItemView);

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {

            dialogInterface.dismiss();
            int batteryLevelValue = Integer.parseInt(((TextView) ItemView.findViewById(R.id.progress_text_view)).getText().toString());
            int option = ((RadioGroup) ItemView.findViewById(R.id.radio_check)).getCheckedRadioButtonId() == R.id.area_entered ? 1 : 2;
            //EventManagementUtil.addTriggerEvent(activity, triggerId, new EventValue(option, String.valueOf(batteryLevelValue)));
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