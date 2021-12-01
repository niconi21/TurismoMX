package com.niconi21.turismoargentina.pages.usuario;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.tools.Mensajes;

public class PerfilFragment extends Fragment {
    private Button _btnCambiarClave;
    private Button _btnCerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._establecerItems(view);
        this.actualizarClave();
        this._cerrarSesion();
    }

    private void _establecerItems(View view) {
        this._btnCambiarClave = (Button) view.findViewById(R.id.actualizarClavePerfil);
        this._btnCerrarSesion = (Button) view.findViewById(R.id.cerrarSesionPerfil);
    }

    public void actualizarClave() {
        this._btnCambiarClave.setOnClickListener(v -> {
            Mensajes.Dialogs(getContext(), "Cambiar contraseña", "Escribe tu contraseña actual y posteriormente la nueva contraseña")
                    .setNegativeButton(R.string.cancelar, (dialog, which) -> {
                        Mensajes.MensajeSnackBar(v, "Contraseña no actualizada", Snackbar
                                .LENGTH_LONG);
                    })
                    .setPositiveButton(R.string.actualizarContraseña, (dialog, which) -> {
                        this._actualizarClave();
                    })
                    .create()
                    .show();
        });
    }

    private void _actualizarClave() {
        Mensajes.MensajeSnackBar(this.getView(), "Contraseña actualizada", Snackbar.LENGTH_LONG);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void _cerrarSesion() {
        this._btnCerrarSesion.setOnClickListener(v -> {
            SingletonDB.deleteUsuarios();
            getActivity().finish();

        });
    }


}