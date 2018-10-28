package com.smileparser.automator.utils;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.smileparser.automator.R;
import com.smileparser.automator.db_helper.EventValueModel;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Nullable;

public class AlertDialogTriggerUtils {

    private static int selectedItem;
    private static AlertDialog alertDialog;

    public static void dismissDialog() {
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
                ((TextView) ItemView.findViewById(R.id.progress_text_view)).setText(String.format("%s %%", String.valueOf(seekBar.getProgress())));
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
            int option = ((RadioButton) ItemView.findViewById(R.id.increases_radio_button)).isChecked() ? 1 : 2;
            EventManagementUtil.addTriggerEvent(triggerId, new EventValueModel(option, String.valueOf(seekBar.getProgress())));
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
            //EventManagementUtil.addTriggerEvent(activity, triggerId, new EventValueModel(option, String.valueOf(batteryLevelValue)));
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
            //EventManagementUtil.addTriggerEvent(activity, triggerId, new EventValueModel(option, String.valueOf(batteryLevelValue)));
        });
        builder.create().show();
    }

    public static void loadTimerPicker(Activity activity, TimePickerDialog.OnTimeSetListener timeSetListener) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, timeSetListener, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
        timePickerDialog.create();
        timePickerDialog.show();
    }

    public static void showRegularIntervalTriggerDialog(Activity activity, @Nullable String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_regular_interval_trigger, null);
        EditText _edtSecond, _edtMinute, _edtHour;
        Button _secondBtnPlus, _secondBtnMinus, _minuteBtnPlus, _minuteBtnMinus, _hourBtnPlus, _hourBtnMinus;
        CheckBox _chkReference, _chkUseAlarm;
        TimePicker _timePicker;

        _hourBtnMinus = ItemView.findViewById(R.id.hour_btn_minus);
        _edtHour = ItemView.findViewById(R.id.edt_hour);
        _hourBtnPlus = ItemView.findViewById(R.id.hour_btn_plus);
        _minuteBtnMinus = ItemView.findViewById(R.id.minute_btn_minus);
        _edtMinute = ItemView.findViewById(R.id.edt_minute);
        _minuteBtnPlus = ItemView.findViewById(R.id.minute_btn_plus);
        _secondBtnMinus = ItemView.findViewById(R.id.second_btn_minus);
        _edtSecond = ItemView.findViewById(R.id.edt_second);
        _secondBtnPlus = ItemView.findViewById(R.id.second_btn_plus);
        _chkReference = ItemView.findViewById(R.id.chk_reference);
        _timePicker = ItemView.findViewById(R.id.time_picker);
        _chkUseAlarm = ItemView.findViewById(R.id.chk_use_alarm);

        builder.setView(ItemView);

        _chkReference.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (_timePicker.getVisibility() == View.GONE)
                    _timePicker.setVisibility(View.VISIBLE);
            } else
                _timePicker.setVisibility(View.GONE);
        });

        _hourBtnPlus.setOnClickListener(V -> _edtHour.setText(String.valueOf(Integer.parseInt(_edtHour.getText().toString()) + 1)));

        _hourBtnMinus.setOnClickListener(V -> {
            if (Integer.parseInt(_edtHour.getText().toString()) > 0) {
                _edtHour.setText(String.valueOf(Integer.parseInt(_edtHour.getText().toString()) - 1));
            } else {
                _edtHour.getText().clear();
            }
        });

        _minuteBtnPlus.setOnClickListener(V -> _edtMinute.setText(String.valueOf(Integer.parseInt(_edtMinute.getText().toString()) + 1)));

        _minuteBtnMinus.setOnClickListener(V -> {
            if (Integer.parseInt(_edtMinute.getText().toString()) > 0) {
                _edtMinute.setText(String.valueOf(Integer.parseInt(_edtMinute.getText().toString()) - 1));
            } else {
                _edtMinute.getText().clear();
            }
        });

        _secondBtnPlus.setOnClickListener(V -> _edtSecond.setText(String.valueOf(Integer.parseInt(_edtSecond.getText().toString()) + 1)));

        _secondBtnMinus.setOnClickListener(V -> {
            if (Integer.parseInt(_edtSecond.getText().toString()) > 0) {
                _edtSecond.setText(String.valueOf(Integer.parseInt(_edtSecond.getText().toString()) - 1));
            } else {
                _edtSecond.getText().clear();
            }
        });

        builder.setPositiveButton("Done", (dialogInterface, i) -> {
            dialogInterface.dismiss();

        });
        builder.create().show();
    }

    public static void showDayTimeTriggerDialog(Activity activity, @Nullable String title) {
        ArrayList<String> checkedButtons = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_day_time_trigger, null);
        CheckBox _chkMonday, _chkSaturday, _chkFriday, _chkThursday, _chkWednesday, _chkTuesday;
        TimePicker _timePicker;

        _chkMonday = ItemView.findViewById(R.id.chk_monday);
        _chkTuesday = ItemView.findViewById(R.id.chk_tuesday);
        _chkWednesday = ItemView.findViewById(R.id.chk_wednesday);
        _chkThursday = ItemView.findViewById(R.id.chk_thursday);
        _chkFriday = ItemView.findViewById(R.id.chk_friday);
        _chkSaturday = ItemView.findViewById(R.id.chk_saturday);
        _timePicker = ItemView.findViewById(R.id.time_picker);

        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("1"))
                    checkedButtons.add("1");
            } else {
                if (checkedButtons.contains("1"))
                    checkedButtons.remove("1");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("1"))
                    checkedButtons.add("1");
            } else {
                if (checkedButtons.contains("1"))
                    checkedButtons.remove("1");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("2"))
                    checkedButtons.add("2");
            } else {
                if (checkedButtons.contains("2"))
                    checkedButtons.remove("2");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("3"))
                    checkedButtons.add("3");
            } else {
                if (checkedButtons.contains("3"))
                    checkedButtons.remove("3");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("4"))
                    checkedButtons.add("4");
            } else {
                if (checkedButtons.contains("4"))
                    checkedButtons.remove("4");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("5"))
                    checkedButtons.add("5");
            } else {
                if (checkedButtons.contains("5"))
                    checkedButtons.remove("5");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("6"))
                    checkedButtons.add("6");
            } else {
                if (checkedButtons.contains("6"))
                    checkedButtons.remove("6");
            }
        });
        _chkMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!checkedButtons.contains("7"))
                    checkedButtons.add("7");
            } else {
                if (checkedButtons.contains("7"))
                    checkedButtons.remove("7");
            }
        });

        builder.setPositiveButton("Done", (dialogInterface, i) -> {
            dialogInterface.dismiss();

        });
        builder.create().show();
    }

    public static void showDayOfWeekMonthTriggerDialog(Activity activity, @Nullable String title, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_day_of_week_trigger, null);

        LinearLayout _dayOfMonth, _dayOfWeek;
        Spinner _spnDaysOfMont, _spnDaysOfWeek, _spnMonthList;
        TimePicker _timePicker;
        CheckBox _chkUseAlarm;

        _dayOfWeek = ItemView.findViewById(R.id.day_of_week);
        _spnDaysOfWeek = ItemView.findViewById(R.id.spnDaysOfWeek);
        _dayOfMonth = ItemView.findViewById(R.id.day_of_month);
        _spnDaysOfMont = ItemView.findViewById(R.id.spnDaysOfMont);
        _spnMonthList = ItemView.findViewById(R.id.spnMonthList);
        _timePicker = ItemView.findViewById(R.id.time_picker);
        _chkUseAlarm = ItemView.findViewById(R.id.chk_use_alarm);

        if (type.equalsIgnoreCase("0")) {//Days of Week
            _dayOfWeek.setVisibility(View.VISIBLE);
            _dayOfMonth.setVisibility(View.GONE);
        } else {//Days of Month
            _dayOfMonth.setVisibility(View.VISIBLE);
            _dayOfWeek.setVisibility(View.GONE);
        }

        builder.setPositiveButton("Done", (dialogInterface, i) -> {
            dialogInterface.dismiss();

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