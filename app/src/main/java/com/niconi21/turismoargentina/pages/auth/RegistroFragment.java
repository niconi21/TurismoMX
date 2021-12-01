package com.niconi21.turismoargentina.pages.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Usuario;
import com.niconi21.turismoargentina.services.AuthServices;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Navegacion;
import com.niconi21.turismoargentina.tools.Validaciones;

public class RegistroFragment extends Fragment {
    private Button _btnRegistro;
    private TextInputLayout _correo;
    private TextInputLayout _clave;
    private TextInputLayout _nombre;

    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._navegarcionFragment(view);
        this._declararComponentes(view);
        this._registro(view);

    }

    private void _declararComponentes(View view) {
        this._btnRegistro = view.findViewById(R.id.btnRegistrarseRegistro);
        this._correo = view.findViewById(R.id.tfCorreoRegistro);
        this._clave = view.findViewById(R.id.tfClaveRegistro);
        this._nombre = view.findViewById(R.id.tfNombreRegistro);
        Validaciones.textChangedListener(this._correo, getString(R.string.mgsErrorCorreo));
        Validaciones.textChangedListener(this._clave, getString(R.string.mgsErrorClave));
        Validaciones.textChangedListener(this._nombre, getString(R.string.mgsErrorNombre));

    }

    private void _navegarcionFragment(View view) {
        NavController navigator = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnLoginRegistro);
        Navegacion.setNavegacion(navigator, btnLogin, R.id.loginFragment);
    }

    private void _registro(View view) {
        this._btnRegistro.setOnClickListener(v -> {
            Usuario usuario = new Usuario();
            AuthServices auth = new AuthServices(this.getContext(), view);
            Boolean isValidEmail = Validaciones.isValid(this._correo, getString(R.string.mgsErrorCorreo));
            Boolean isValidPassword = Validaciones.isValid(this._clave, getString(R.string.mgsErrorClave));
            Boolean isValidName = Validaciones.isValid(this._nombre, getString(R.string.mgsErrorNombre));
            if (isValidEmail && isValidPassword && isValidName) {
                usuario.setNombre(this._nombre.getEditText().getText().toString());
                usuario.setCorreo(this._correo.getEditText().getText().toString());
                usuario.setClave(this._clave.getEditText().getText().toString());
                auth.registro(usuario);
            } else {
                Mensajes.MensajeSnackBar(view, getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
            }
        });
    }
}