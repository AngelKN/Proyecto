package red.lisgar.proyecto.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import red.lisgar.proyecto.R;
import red.lisgar.proyecto.admin.AdminActualizarUsuarioActivity;
import red.lisgar.proyecto.usuario.UsuRutasActivity;
import red.lisgar.proyecto.usuario.UsuVisualizarRutaActivity;
import red.lisgar.proyecto.entidades.Usuario;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.LibrosDisponiblesViewHolder> {

    ArrayList<Usuario> listaOriginal;
    ArrayList<Usuario> listItem;
    Context context;
    String destino;
    String ventana;

    public ListaUsuariosAdapter(ArrayList<Usuario> listItem, Context context, String destino, String ventana) {
        this.listItem = listItem;
        this.context = context;
        this.destino = destino;
        this.ventana = ventana;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listItem);
    }

    @NonNull
    @Override
    public ListaUsuariosAdapter.LibrosDisponiblesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (ventana){
            case "VERTICAL":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new LibrosDisponiblesViewHolder(view);
            case "HORIZONTAL":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rutas_user, parent, false);
                return new LibrosDisponiblesViewHolder(view);
            default:
                throw new IllegalStateException("Unexpected value: " + ventana);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListaUsuariosAdapter.LibrosDisponiblesViewHolder holder, int position) {
        final Usuario item = listItem.get(position);


        switch (ventana){
            case "VERTICAL":
                holder.nombre.setText("Nombre: "+item.getNombre());
                holder.correo.setText("Correo:   "+item.getCorreo());
                holder.telefono.setText("Telefono: "+item.getTelefono());
                holder.direccion.setText(item.getDireccion());
                break;
            case "HORIZONTAL":
                holder.txtUbicacionU.setText(item.getNombre());
                holder.txtMapaU.setText(item.getDireccion());
                holder.txtIdRuta.setText(item.getTelefono());
                break;
        }
    }

    public void filter(String buscar){
        int longitud = buscar.length();

        if(longitud == 0){
            listItem.clear();
            listItem.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Usuario> collection = listItem.stream().filter(i -> i.getNombre().toLowerCase().contains(buscar.toLowerCase())).collect(Collectors.toList());
                listItem.clear();
                listItem.addAll(collection);
            }else{
                for (Usuario c : listaOriginal){
                    if(c.getNombre().toLowerCase().contains(buscar.toLowerCase())){
                        listaOriginal.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class LibrosDisponiblesViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView correo;
        TextView telefono;
        TextView direccion;

        ImageView imagenParadasU;
        TextView txtUbicacionU;
        TextView txtMapaU;
        TextView txtIdRuta;


        public LibrosDisponiblesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtItem1);
            correo = itemView.findViewById(R.id.txtItem2);
            telefono = itemView.findViewById(R.id.txtItem3);
            direccion = itemView.findViewById(R.id.txtItem4);

            imagenParadasU = itemView.findViewById(R.id.imagenRutasPrestados);
            txtUbicacionU = itemView.findViewById(R.id.txtItemNombre);
            txtMapaU = itemView.findViewById(R.id.txtItemDescripcion);
            txtIdRuta = itemView.findViewById(R.id.txtItemPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent;
                    String id;
                    switch (destino){
                        case "ACTUALIZAR":
                            intent = new Intent(context, AdminActualizarUsuarioActivity.class);
                            id = listItem.get(getAdapterPosition()).getId();
                            intent.putExtra("idi", id);
                            context.startActivity(intent);
                            break;
                    }
                }
            });
        }
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0){
            listItem.clear();
            listItem.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
                List<Usuario> collection = listItem.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listItem.clear();
                listItem.addAll(collection);
            }else {
                for (Usuario l: listaOriginal){
                    if (l.getDireccion().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listItem.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
