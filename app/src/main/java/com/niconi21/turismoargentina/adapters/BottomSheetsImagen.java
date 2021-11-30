package com.niconi21.turismoargentina.adapters;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.tools.Mensajes;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BottomSheetsImagen extends BottomSheetDialogFragment {
    public Publicacion publicacion;
    private LinearLayout compartir;
    private LinearLayout descargar;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog dialog2 = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = dialog2.findViewById(R.id.bottom_sheet_imagenes);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
                BottomSheetBehavior.from(bottomSheet).setHideable(true);
            }
        });
        return bottomSheetDialog;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheets_imagen,
                container, false);

        this.compartir = view.findViewById(R.id.bsi_compartir);
        this.descargar = view.findViewById(R.id.bsi_descargar);

        this.compartir.setOnClickListener(v -> {
            String texto = "Te invito a ver mi publicación\n";
            texto += this.publicacion.getTitulo()+"\n";
            texto += this.publicacion.getDescripcion()+"\n";
            texto += this.publicacion.getImagen();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            dismiss();
        });

        this.descargar.setOnClickListener(v -> {
            Bitmap imagen = getBitmapFromURL(this.publicacion.getImagen());
            if(imagen!=null) {//Glide.with(v.getContext()).load(this.imagen).into(iv);
                MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(), imagen, this.publicacion.getTitulo(), this.publicacion.getDescripcion());
                Toast.makeText(v.getContext(), R.string.imagenSave, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(v.getContext(), R.string.imagenDontSave, Toast.LENGTH_LONG).show();
            }
            dismiss();
        });

        return view;
    }

    public static Bitmap getBitmapFromURL(String url_image) {
        try {

            URL url = new URL(url_image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
