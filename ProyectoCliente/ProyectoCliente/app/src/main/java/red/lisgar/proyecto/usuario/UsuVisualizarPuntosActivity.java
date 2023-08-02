package red.lisgar.proyecto.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import red.lisgar.proyecto.constants.Intents;
import red.lisgar.proyecto.R;
import red.lisgar.proyecto.adaptadores.ListaUsuariosAdapter;
import red.lisgar.proyecto.constants.urlDeLaApi;
import red.lisgar.proyecto.entidades.PuntoRecarga;
import red.lisgar.proyecto.entidades.Usuario;
import red.lisgar.proyecto.interfaces.PuntosInterface;
import red.lisgar.proyecto.login.SharePreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuVisualizarPuntosActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ListaUsuariosAdapter adapter;
    ArrayList<PuntoRecarga> listPrestadosAdmin;
    TextView rolToolbar;
    TextView nombreToolbar;
    ImageView btnMas;
    SharePreference sHarePreference;
    ImageView imageMiLibroAdmin;
    TextView nombrePunto;
    TextView ubicacionPunto;
    TextView mapaPunto;
    ImageView imgBarra;
    PuntosInterface interfaces;
    String id;
    ImageButton btnRutas;
    ImageButton btnPuntos;
    ImageButton btnCupos;
    Intents inte = new Intents(UsuVisualizarPuntosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_visualizar_puntos);
        btnRutas = findViewById(R.id.btnRutas);
        btnPuntos = findViewById(R.id.btnPuntos);
        btnCupos = findViewById(R.id.btnCupos);

        ubicacionPunto = findViewById(R.id.ubicacionPunto);
        mapaPunto = findViewById(R.id.mapaPunto);

        //TOOLBAR
        sHarePreference = new SharePreference(this);
        rolToolbar = findViewById(R.id.rolToolbar);
        btnMas = findViewById(R.id.btnMas);
        imgBarra = findViewById(R.id.imgBarra);
        nombreToolbar = findViewById(R.id.nombreToolbar);

        //TOOLBAR
        nombreToolbar.setText(sHarePreference.getCorreo());
        imgBarra.setImageResource(R.drawable.admin);
        Usuario usuario = new Usuario();
        rolToolbar.setText("Usuario");

        if (usuario != null) {
            nombreToolbar.setText(usuario.getNombre());
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                id = extras.getString("ID");
            }
        } else {
            id = (String) savedInstanceState.getString("ID");
        }

        ver(id);


        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inte.usuPuntos();
            }
        });
        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inte.usuRutas();
            }
        });
        btnPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inte.usuPuntos();
            }
        });
        btnCupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inte.usuCupos();
            }
        });

    }


    private void ver(String id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlDeLaApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaces = retrofit.create(PuntosInterface.class);
        Call<PuntoRecarga> call = interfaces.getOne(id);
        call.enqueue(new Callback<PuntoRecarga>() {
            @Override
            public void onResponse(Call<PuntoRecarga> call, Response<PuntoRecarga> response) {
                if(!response.isSuccessful())
                {
                    Toast toast = Toast.makeText(getApplication(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("err: ",response.message());
                    return;
                }
                PuntoRecarga ver = response.body();

                if(ver != null){
                    ubicacionPunto.setText(ver.getUbicacion());
                    mapaPunto.setText(ver.getMapa());
                }else{
                    Toast toast = Toast.makeText(getApplication(), "ERROR AL ENCONTRAR LA RUTA", Toast.LENGTH_LONG);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<PuntoRecarga> call, Throwable t) {
                Toast toast = Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("err: ", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }
}