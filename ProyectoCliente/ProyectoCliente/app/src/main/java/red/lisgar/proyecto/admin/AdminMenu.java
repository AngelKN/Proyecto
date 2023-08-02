package red.lisgar.proyecto.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import red.lisgar.proyecto.R;
import red.lisgar.proyecto.interfaces.RutaInterface;
import red.lisgar.proyecto.login.MainActivity;
import red.lisgar.proyecto.login.SharePreference;

public class AdminMenu extends AppCompatActivity {

    //LAYOUT
    TextView rolToolbar;
    TextView nombreToolbar;
    SearchView buscadorAdminDispon;
    ImageView btnMas;
    //CLASES
    PopupMenu popupMenu;
    SharePreference sHarePreference;
    ImageView imgBarra;
    Button btnRutas;
    Button btnParadas;
    Button btnPuntos;
    Button btnUsuarios;
    Button btnCupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu2);

        buscadorAdminDispon = findViewById(R.id.buscadorAdminDispon);
        btnRutas = findViewById(R.id.btnVerRutas);
        btnParadas = findViewById(R.id.btnVerParadas);
        btnPuntos = findViewById(R.id.btnVerPuntosRecarga);
        btnUsuarios = findViewById(R.id.btnVerUsuarios);
        btnCupos = findViewById(R.id.btnVerCupos);

        //TOOLBAR
        sHarePreference = new SharePreference(this);
        btnMas = findViewById(R.id.btnMas);
        rolToolbar = findViewById(R.id.rolToolbar);
        nombreToolbar = findViewById(R.id.nombreToolbar);
        imgBarra = findViewById(R.id.imgBarra);
        imgBarra.setImageResource(R.drawable.admin);
        rolToolbar.setText("Administrador");
        nombreToolbar.setText(sHarePreference.getCorreo());
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verRutas();
            }
        });
        btnParadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verParadas();
            }
        });
        btnPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verPuntos();
            }
        });
        btnUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verUsuarios();
            }
        });
        btnCupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verCupos();
            }
        });
    }

    private void verRutas(){
        Intent intent = new Intent(this, AdminRutasActivity.class);
        startActivity(intent);
    }

    private void verParadas(){
        Intent intent = new Intent(this, AdminParadasActivity.class);
        startActivity(intent);
    }

    private void verPuntos(){
        Intent intent = new Intent(this, AdminPuntosActivity.class);
        startActivity(intent);
    }

    private void verUsuarios(){
        Intent intent = new Intent(this, AdminUsuariosActivity.class);
        startActivity(intent);
    }

    private void verCupos(){
        Intent intent = new Intent(this, AdminCuposActivity.class);
        startActivity(intent);
    }

    private void salir(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}