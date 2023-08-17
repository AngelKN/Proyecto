package red.lisgar.proyecto.constants;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import red.lisgar.proyecto.admin.AdminActualizarUsuarioActivity;
import red.lisgar.proyecto.admin.AdminAgregarCupoActivity;
import red.lisgar.proyecto.admin.AdminAgregarParadaActivity;
import red.lisgar.proyecto.admin.AdminAgregarPuntosActivity;
import red.lisgar.proyecto.admin.AdminAgregarRutaActivity;
import red.lisgar.proyecto.admin.AdminCuposActivity;
import red.lisgar.proyecto.admin.AdminParadasActivity;
import red.lisgar.proyecto.admin.AdminPuntosActivity;
import red.lisgar.proyecto.admin.AdminRutasActivity;
import red.lisgar.proyecto.admin.AdminUsuariosActivity;
import red.lisgar.proyecto.login.MainActivity;
import red.lisgar.proyecto.login.SigninActivity;
import red.lisgar.proyecto.usuario.MisPublicacionesActivity;
import red.lisgar.proyecto.usuario.UsuPortalActivity;
import red.lisgar.proyecto.usuario.UsuPuntosActivity;
import red.lisgar.proyecto.usuario.UsuRutasActivity;

public class Intents {

    Context context;

    boolean correcto;

    public Intents(Context context) {
        this.context = context;
    }

    public void Admin(){
        Intent intent = new Intent(context, AdminRutasActivity.class);
        context.startActivity(intent);
    }

    public void User(){
        Intent intent = new Intent(context, UsuRutasActivity.class);
        context.startActivity(intent);
    }

    public void Singin(){
        Intent intent = new Intent(context, SigninActivity.class);
        context.startActivity(intent);
    }

    public void Main(){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void usuPuntos(){
        Intent intent = new Intent(context, UsuPuntosActivity.class);
        context.startActivity(intent);
    }

    public void usuRutas(){
        Intent intent = new Intent(context, UsuRutasActivity.class);
        context.startActivity(intent);
    }

    public void usuCupos() {
        Intent intent = new Intent(context, UsuPortalActivity.class);
        context.startActivity(intent);
    }

    public void adminCupos(){
        Intent intent = new Intent(context, AdminCuposActivity.class);
        context.startActivity(intent);
    }

    public void adminParadas(){
        Intent intent = new Intent(context, AdminParadasActivity.class);
        context.startActivity(intent);
    }

    public void adminPuntos(){
        Intent intent = new Intent(context, AdminPuntosActivity.class);
        context.startActivity(intent);
    }

    public void adminRutas(){
        Intent intent = new Intent(context, AdminRutasActivity.class);
        context.startActivity(intent);
    }

    public void adminUsers(){
        Intent intent = new Intent(context, AdminUsuariosActivity.class);
        context.startActivity(intent);
    }

    public void agregarCupo(){
        Intent intent = new Intent(context, AdminAgregarCupoActivity.class);
        context.startActivity(intent);
    }

    public void agregarParada(){
        Intent intent = new Intent(context, AdminAgregarParadaActivity.class);
        context.startActivity(intent);
    }

    public void agregarRuta(){
        Intent intent = new Intent(context, AdminAgregarRutaActivity.class);
        context.startActivity(intent);
    }

    public void agregarPunto(){
        Intent intent = new Intent(context, AdminAgregarPuntosActivity.class);
        context.startActivity(intent);
    }

    public void misPublicaciones() {
        Intent intent = new Intent(context, MisPublicacionesActivity.class);
        context.startActivity(intent);
    }

    public void actualizarUser() {
        Intent intent = new Intent(context, AdminActualizarUsuarioActivity.class);
        context.startActivity(intent);
    }

    public void alerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setMessage("¿Desea cerrar sesion?:")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Main();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Cerrar Sesion");
        titulo.show();
    }
}
