package com.smileparser.automator.utils;
/*
Create by user on 26-10-2018 at 05:03 PM for EmployeeAdmin
*/

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;

import com.smileparser.automator.R;

import java.util.ArrayList;

public class DialogUtil {

   private static AlertDialog alertDialog;

   public static void showSimpleOkDialog(Context context, String title, String message, DialogInterface.OnClickListener clickListener) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle(title);
      builder.setMessage(message);
      builder.setPositiveButton("Ok", clickListener);

      alertDialog = builder.create();
      alertDialog.show();
   }

   public static void showSingleChoiceDialog(Context context, String title, ArrayList<String> arrayList, DialogInterface.OnClickListener clickListener) {
      ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arrayList);
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle(title);
      builder.setAdapter(adapter, clickListener);

      alertDialog = builder.create();
      alertDialog.show();
   }

   public static void showMultiChoiceDialog(Context context, String title, ArrayList<String> arrayList, MyDialogInterface anInterface) {
      ArrayList<String> checked = new ArrayList<>();
      CharSequence[] cs = arrayList.toArray(new CharSequence[arrayList.size()]);
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle(title);

      builder.setMultiChoiceItems(cs, null, (dialog, which, isChecked) -> {
         if (isChecked) {
            checked.add(String.valueOf(which));
         } else if (checked.contains(String.valueOf(which))) {
            checked.remove(String.valueOf(which));
         }
      });

      builder.setPositiveButton("Ok", (dialog, which) -> {
         dialog.cancel();
         dialog.dismiss();
         anInterface.onOkay(checked);
      });

      builder.setNegativeButton("Cancel", (dialog, which) -> {
         dialog.cancel();
         dialog.dismiss();
         anInterface.onCancel();
      });

      alertDialog = builder.create();
      alertDialog.show();
   }

   public static void customLayoutDialog(Activity context, String title, MyDialogInterface anInterface) {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setTitle(title);

      View itemView = context.getLayoutInflater().inflate(R.layout.layout_location_trigger, null);

      builder.setView(itemView);

      builder.setPositiveButton("Ok", (dialog, which) -> {
         dialog.cancel();
         dialog.dismiss();

         anInterface.onOkay(null);
      });

      builder.setNegativeButton("Cancel", (dialog, which) -> {
         dialog.cancel();
         dialog.dismiss();
         anInterface.onCancel();
      });

      alertDialog = builder.create();
      alertDialog.show();
   }

   public interface MyDialogInterface {

      void onCancel();

      void onOkay(Object object);
   }

}