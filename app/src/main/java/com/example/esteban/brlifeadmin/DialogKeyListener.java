package com.example.esteban.brlifeadmin;

import android.content.DialogInterface;
import android.view.KeyEvent;

import java.security.Key;

public class DialogKeyListener implements android.content.DialogInterface.OnKeyListener {


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
          dialog.dismiss();
            return false;
        }

        return true;
    }
}
