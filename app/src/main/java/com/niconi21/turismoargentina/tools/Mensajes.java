package com.niconi21.turismoargentina.tools;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.R;

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
    public static void MensajeToast(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static MaterialAlertDialogBuilder Dialogs(Context context, String title, String mensaje) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);

        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(mensaje);
        return dialogBuilder;
    }
}
