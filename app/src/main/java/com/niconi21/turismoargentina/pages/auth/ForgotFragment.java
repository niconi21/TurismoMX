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
import com.niconi21.turismoargentina.services.AuthServices;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Navegacion;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ForgotFragment extends Fragment {
    private Button _btnReestablecer;
    private TextInputLayout _correo;

    public ForgotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._navegacionFragment(view);
        this._establecerComponentes(view);
        this._recuperarClave(view);
    }

    private void _establecerComponentes(View view) {

        this._btnReestablecer = view.findViewById(R.id.btnReestablecerForgot);
        this._correo = view.findViewById(R.id.tfCorreoForgot);
    }

    private void _navegacionFragment(View view) {
        NavController navigation = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnLoginForgot);
        Navegacion.setNavegacion(navigation, btnLogin, R.id.loginFragment);
    }

    private void _recuperarClave(View view) {
        this._btnReestablecer.setOnClickListener(v -> {
            String correo = this._correo.getEditText().getText().toString();
            AuthServices auth = new AuthServices(this.getContext(), view);
            auth.recuperarClave(correo);

        });

    }
}
