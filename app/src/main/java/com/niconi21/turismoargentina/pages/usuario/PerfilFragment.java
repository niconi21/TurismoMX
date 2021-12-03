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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.db.UsuarioEntity;
import com.niconi21.turismoargentina.models.Usuario;
import com.niconi21.turismoargentina.services.UsuarioService;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Validaciones;


public class PerfilFragment extends Fragment {

    private TextInputLayout _nombre;
    private TextInputLayout _correo;
    private Button _btnActualizarUsuario;
    private Button _btnCambiarClave;
    private Button _btnCerrarSesion;

    private UsuarioService _usuarioService;
    private UsuarioEntity _usuarioEntity;

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
        this._usuarioService = new UsuarioService(this.getContext(), view);
        this._establecerItems(view);

        this.actualizarClave();
        this.actualizarUsuario();
        this._cerrarSesion();
    }

    private void _establecerItems(View view) {
        this._btnCambiarClave = view.findViewById(R.id.actualizarClavePerfil);
        this._btnCerrarSesion = view.findViewById(R.id.cerrarSesionPerfil);
        this._btnActualizarUsuario = view.findViewById(R.id.btnActualizarUsuarioPerfil);

        this._nombre = view.findViewById(R.id.tfNombrePerfil);
        this._correo = view.findViewById(R.id.tfCorreoPerfil);

        Validaciones.textChangedListener(this._nombre, getString(R.string.mgsErrorNombre));
        Validaciones.textChangedListener(this._correo, getString(R.string.mgsErrorCorreo));

        llenarDatosUsuario();
    }

    public void llenarDatosUsuario(){
        this._usuarioEntity = SingletonDB.getUsuarios().get(0);
        this._nombre.getEditText().setText(this._usuarioEntity.nombre);
        this._correo.getEditText().setText(this._usuarioEntity.correo);
    }

    public void actualizarUsuario() {
        this._btnActualizarUsuario.setOnClickListener(v -> {
            Boolean isValidNombre = Validaciones.isValid(this._nombre, getString(R.string.mgsErrorNombre));
            Boolean isValidCorreo = Validaciones.isValid(this._correo, getString(R.string.mgsErrorCorreo));
            if (isValidNombre && isValidCorreo) {
                String nombre = this._nombre.getEditText().getText().toString();
                String correo = this._correo.getEditText().getText().toString();
                this._usuarioService.actualizarUsuario(nombre, correo, this._usuarioEntity, this);
            } else {
                Mensajes.MensajeSnackBar(this.getView(), getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
            }
        });
    }

    public void actualizarClave() {
        this._btnCambiarClave.setOnClickListener(v -> {
            View content = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cambiar_clave, null);

            TextInputLayout claveActual = content.findViewById(R.id.tfClaveActualPerfil);
            TextInputLayout claveNueva = content.findViewById(R.id.tfClaveNuevaPerfil);

            Validaciones.textChangedListener(claveActual, getString(R.string.mgsErrorClaveActual));
            Validaciones.textChangedListener(claveNueva, getString(R.string.mgsErrorClaveNueva));

            MaterialAlertDialogBuilder dialogBuilder = Mensajes.Dialogs(getContext(), "Cambiar contraseña", "Escribe tu contraseña actual y posteriormente la nueva contraseña");
            dialogBuilder.setPositiveButton(R.string.actualizarContraseña, (dialog, which) -> {
                Boolean isValidClaveActual = Validaciones.isValid(claveActual, getString(R.string.mgsErrorClaveActual));
                Boolean isValidClaveNueva = Validaciones.isValid(claveNueva, getString(R.string.mgsErrorClaveNueva));

                if(isValidClaveActual && isValidClaveNueva){
                    String claveActualString = claveActual.getEditText().getText().toString();
                    String claveNuevaString = claveNueva.getEditText().getText().toString();
                    this._usuarioService.cambiarClave(claveActualString, claveNuevaString);
                }else{
                    Mensajes.MensajeSnackBar(this.getView(), getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
                }
            });

            dialogBuilder.setView(content);

            dialogBuilder.create();
            dialogBuilder.show();


        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void _cerrarSesion() {
        this._btnCerrarSesion.setOnClickListener(v -> {
            SingletonDB.deleteUsuarios();
            getActivity().finish();
        });
    }


}