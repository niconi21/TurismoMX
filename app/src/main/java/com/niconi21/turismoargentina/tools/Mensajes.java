package com.niconi21.turismoargentina.tools;

import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Mensajes {

    public static void MensajeSnackBar(View v, String msg, int duration) {
        Snackbar.make(v, msg, duration).show();
    }


    public static void MensajeSnackBar(View v, Button btn, String msg, int duration) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, msg, duration).show();
            }
        });
    }
}
