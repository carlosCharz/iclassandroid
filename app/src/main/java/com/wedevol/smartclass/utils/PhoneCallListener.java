package com.wedevol.smartclass.utils;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/** Created by paolorossi on 12/9/16.*/
public class PhoneCallListener extends PhoneStateListener {
    private boolean isPhoneCalling = false;

    public PhoneCallListener(){
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        String LOG_TAG = "LOG_TELEPHONY";
        if (TelephonyManager.CALL_STATE_RINGING == state) {// phone ringing
            Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
        }

        if (TelephonyManager.CALL_STATE_OFFHOOK == state) {// active
            Log.i(LOG_TAG, "OFFHOOK");
            isPhoneCalling = true;
        }

        if (TelephonyManager.CALL_STATE_IDLE == state) {
            // run when class initial and phone call ended,
            // need detect flag from CALL_STATE_OFFHOOK
            Log.i(LOG_TAG, "IDLE");

            if (isPhoneCalling) {
                Log.i(LOG_TAG, "restart app");

                // restart app
                //Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(
                //                activity.getBaseContext().getPackageName());
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //activity.startActivity(i);

                isPhoneCalling = false;
            }

        }
    }
}