package com.niconi21.turismoargentina.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.niconi21.turismoargentina.DatosPostActivity;
import com.niconi21.turismoargentina.PostDetalleActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.adapters.PublicacionAdapter;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.models.Result;
import com.niconi21.turismoargentina.tools.Implementacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class PublicacionService {

    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/publicacion";

    public PublicacionService(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }

    public void obtenerPublicacion(PostDetalleActivity activity, String id) {
        JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/post/" + id, new JSONObject(),
                response -> {
                    try {
                        System.out.println(response.toString());
                        Result result = new Result();
                        Publicacion publicacion = result.parseResultPublicacionPost(response.getJSONObject("result"), true).getPublicacion();
                        activity.establecerDatos(publicacion);
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                    }
                }
                ,
                error -> {
                    this._showMessage(error);
                }
        );
        this._peticiones.agregarCola(peticion);
    }

    @SuppressLint("NewApi")
    public void obtenerPublicaciones(RecyclerView recyclerView) {
        JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/todas", new JSONObject(),
                response -> {
                    try {
                        Result result = new Result();
                        ArrayList<Publicacion> publicaciones = result.parseResultPublicaciones(response.getJSONObject("result")).getPublicaciones();
                        publicaciones.forEach(publicacion -> {
                            publicacion.setTipo("post");
                        });
                        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(publicaciones);
                        Implementacion.llenarListaRecycleView(this.context, recyclerView, publicacionAdapter, publicaciones);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ,
                error -> {
                    this._showMessage(error);
                }
        );

        this._peticiones.agregarCola(peticion);
    }

    @SuppressLint("NewApi")
    public void obtenerMisPublicaciones(RecyclerView recyclerView) {
        JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/propios", new JSONObject(),
                response -> {
                    try {
                        Result result = new Result();
                        ArrayList<Publicacion> publicaciones = result.parseResultPublicaciones(response.getJSONObject("result")).getPublicaciones();
                        publicaciones.forEach(publicacion -> {
                            publicacion.setTipo("misPost");
                        });
                        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(publicaciones);
                        Implementacion.llenarListaRecycleView(this.context, recyclerView, publicacionAdapter, publicaciones);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ,
                error -> {
                    this._showMessage(error);
                }
        );

        this._peticiones.agregarCola(peticion);
    }

    @SuppressLint("NewApi")
    public void obtenerFavoritos(RecyclerView recyclerView) {
        JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/favoritos", new JSONObject(),
                response -> {
                    try {
                        Result result = new Result();
                        ArrayList<Publicacion> publicaciones = result.parseResultPublicaciones(response.getJSONObject("result")).getPublicaciones();
                        publicaciones.forEach(publicacion -> {
                            publicacion.setTipo("favorito");
                        });
                        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(publicaciones);
                        Implementacion.llenarListaRecycleView(this.context, recyclerView, publicacionAdapter, publicaciones);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ,
                error -> {
                    this._showMessage(error);
                }
        );

        this._peticiones.agregarCola(peticion);
    }


    @SuppressLint("NewApi")
    public void agregarPublicacion(Publicacion publicacion, DatosPostActivity activity, Bitmap img) {
        try {
            JSONObject body = new JSONObject();
            JSONObject ubicacion = new JSONObject();

            String[] etiquetas = new String[publicacion.getEtiquetas().size()];
            etiquetas = publicacion.getEtiquetas().toArray(etiquetas);

            ubicacion.put("altitud", publicacion.getUbicacion().getLongitud());
            ubicacion.put("latitud", publicacion.getUbicacion().getLatitud());

            body.put("titulo", publicacion.getTitulo());
            body.put("descripcion", publicacion.getDescripcion());
            body.put("ubicacion", ubicacion);
            body.put("etiquetas", new JSONArray(etiquetas));
            JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/agregar", body,
                    response -> {
                        try {
                            this._showMessage(response);
                            Result result = new Result();
                            String id = result.parseResultPublicacionPost(response.getJSONObject("result"), false).getPublicacion().getId();
                            //Subir imagen
                            this._subirImagen(id, img);
                            activity.regresar(this.view);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ,
                    error -> {
                        this._showMessage(error);
                    }
            );

            this._peticiones.agregarCola(peticion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void _subirImagen(String _id, Bitmap imagen) {
        this._crearArchivo(imagen);
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String existingFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/imagen.png";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        String urlString = "https://turismomx-api.herokuapp.com/api/v1/archivo/subir/imagen/post/"+_id;

        try {
            System.out.println("abriendo archivo");
            File filesDir = this.context.getFilesDir();
            File imageFile = new File(filesDir, "imagen.jpg");
            System.out.println(imageFile.getAbsolutePath());
            //------------------ CLIENT REQUEST
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            // open a URL connection to the Servlet
            URL url = new URL(urlString);
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("token", SingletonDB.getToken());
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"imagen\";filename=\"" + existingFileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // close streams
            Log.e("Debug", "File is written");
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            Log.e("Debug", "error: " + ioe.getMessage(), ioe);
        }

        //------------------ read the SERVER RESPONSE
        try {

            inStream = new DataInputStream(conn.getInputStream());
            String str;

            while ((str = inStream.readLine()) != null) {

                Log.e("Debug", "Server Response " + str);

            }
            inStream.close();

        } catch (IOException ioex) {
            Log.e("Debug", "error: " + ioex.getMessage(), ioex);
        }
    }

    private void _crearArchivo(Bitmap imagen){
        System.out.println("Creando archivo");
        File filesDir = this.context.getFilesDir();
        File imageFile = new File(filesDir, "imagen.jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }

    }

    private void _showMessage(VolleyError error) {
        try {
            this._peticiones.showMessageSnakbar(this._peticiones.getJson(error.networkResponse), this.view);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void _showMessage(JSONObject response) {
        this._peticiones.showMessageSnakbar(response, this.view);
    }
}
