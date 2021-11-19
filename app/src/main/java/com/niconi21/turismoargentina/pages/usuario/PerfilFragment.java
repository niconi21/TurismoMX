package com.niconi21.turismoargentina.pages.usuario;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.tools.Mensajes;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnCambiarClave = view.findViewById(R.id.actualizarClavePerfil);
        btnCambiarClave.setOnClickListener(v -> {
            this.cambiarClave(v);
        });
    }

    public void cambiarClave(View view) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(getContext());
        dialogBuilder.setTitle("Cambiar contraseña");
        dialogBuilder.setMessage("Escribe tu contraseña actual y posteriormente la nueva contraseña");

        dialogBuilder.setPositiveButton("Cambiar contraseña", (dialog, which) -> {
            Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_LONG).show();
        });
        dialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> {
            Toast.makeText(getContext(), "Contraseña no actualizada", Toast.LENGTH_LONG).show();
        });

        dialogBuilder.create();
        dialogBuilder.show();
//        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//// 2. Chain together various setter methods to set the dialog characteristics
//        builder.setMessage("Mensaje")
//                .setTitle("Titulo");
//
//// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }
}