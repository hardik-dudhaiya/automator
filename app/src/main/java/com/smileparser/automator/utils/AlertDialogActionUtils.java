package com.smileparser.automator.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.smileparser.automator.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class AlertDialogActionUtils {

    public static int selected_item;
    private static AlertDialog alertDialog;

    public static void dismisDialog() {
        if (alertDialog == null)
            return;
        alertDialog.cancel();
        alertDialog.dismiss();
    }

    public static void showAlertDialog(Activity activity, @Nullable String title, @Nullable ArrayAdapter adapter, okCancelListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        if (adapter != null)
            builder.setAdapter(adapter, (dialog, which) -> okListener.onItemClick(which));

        builder.setPositiveButton("OK", (dialog, which) -> okListener.onOkClick());

        builder.setNegativeButton("Cancel", (dialog, which) -> okListener.onCancelClick());

        alertDialog = builder.create();

        alertDialog.setCancelable(true);

        alertDialog.show();
    }

    public static void showAlertDialogSingleChoiceOnly(Activity activity, @Nullable String title, @Nullable ArrayAdapter adapter, okCancelListener okListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        if (adapter != null)
            builder.setAdapter(adapter, (dialog, which) -> okListener.onItemClick(which));

        builder.setPositiveButton("OK", (dialog, which) -> okListener.onOkClick());

        builder.setNegativeButton("Cancel", (dialog, which) -> okListener.onCancelClick());

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

    public static void showInputAlertDialog(Activity activity, @Nullable String title, okListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        final EditText input = new EditText(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        builder.setView(input); // uncomment this line

        builder.setPositiveButton("OK", (dialog, which) -> listener.onItemClick(input.getText().toString()));

        alertDialog = builder.create();

        alertDialog.setCancelable(true);

        alertDialog.show();
    }

    public static void showSpeechTestDialog(Activity activity, @Nullable String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null)
            builder.setTitle(title);

        View ItemView = activity.getLayoutInflater().inflate(R.layout.layout_speech_action, null);

        SeekBar _seekPitchRate, _seekSpeedRate;
        CheckBox _chkAddQueue;
        EditText _edtText;
        Spinner _spinAudioStream;
        Button _btnTest, _btnCancel, _btnOk;

        _seekPitchRate = ItemView.findViewById(R.id.seek_pitch_rate);
        _seekSpeedRate = ItemView.findViewById(R.id.seek_speed_rate);
        _chkAddQueue = ItemView.findViewById(R.id.chk_add_queue);
        _edtText = ItemView.findViewById(R.id.edt_text);
        _spinAudioStream = ItemView.findViewById(R.id.spin_audio_stream);
        _btnCancel = ItemView.findViewById(R.id.btn_cancel);
        _btnTest = ItemView.findViewById(R.id.btn_test);
        _btnOk = ItemView.findViewById(R.id.btn_ok);

        _btnCancel.setOnClickListener(V -> {
            alertDialog.dismiss();
            alertDialog.cancel();
        });

        _btnOk.setOnClickListener(V -> {
            alertDialog.dismiss();
            alertDialog.cancel();
        });

        alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public interface okListener extends ItemClicks {
        void onOkClick();
    }

    public interface okCancelListener extends ItemClicks {
        void onOkClick();

        void onCancelClick();

        void onItemClick(Object object);
    }

    public interface ItemClicks {
        void onItemClick(Object object);
    }
}