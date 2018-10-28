package com.smileparser.automator.action_events;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.smileparser.automator.db_helper.ActionModel;
import com.smileparser.automator.triggeractionmanager.Actionable;
import com.smileparser.automator.utils.Constants;

/**
 * Created by mayur on 2/10/18.
 */

public class MobileDataToggle implements Actionable {

    private final Context context;
    private final ActionModel actionDetails;

    public MobileDataToggle(Context context, ActionModel actionDetails) {
        this.context = context;
        this.actionDetails = actionDetails;
    }

    @Override
    public void execute() {
        try {
            if (actionDetails != null) {
                Log.e("Android->", "execute: " + new Gson().toJson(actionDetails));

                switch (Integer.parseInt(actionDetails.gettValue().getValue())) {
                    case Constants.BLUETOOTH_ON:
                        BluetoothAdapter.getDefaultAdapter().enable();
                        break;
                    case Constants.BLUETOOTH_OFF:
                        BluetoothAdapter.getDefaultAdapter().disable();
                        break;
                    case Constants.BLUETOOTH_TOGGLE:
                        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (mBluetoothAdapter.isEnabled()) {
                            mBluetoothAdapter.disable();
                        } else {
                            mBluetoothAdapter.enable();
                        }
                        break;
                    case Constants.CONNECT_AUDIO:
                        // BluetoothAdapter.getDefaultAdapter().disable();
                        break;
                    case Constants.DIS_CONNECT_AUDIO:
                        // BluetoothAdapter.getDefaultAdapter().disable();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
