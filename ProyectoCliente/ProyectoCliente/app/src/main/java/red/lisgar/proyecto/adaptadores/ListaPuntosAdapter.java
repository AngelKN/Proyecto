package red.lisgar.proyecto.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import red.lisgar.proyecto.R;
import red.lisgar.proyecto.admin.AdminActualizarPuntoActivity;
import red.lisgar.proyecto.usuario.UsuVisualizarPuntosActivity;
import red.lisgar.proyecto.entidades.PuntoRecarga;

public class ListaPuntosAdapter extends RecyclerView.Adapter<ListaPuntosAdapter.LibrosDisponiblesViewHolder> {

    ArrayList<PuntoRecarga> listaOriginal;
    ArrayList<PuntoRecarga> listItem;
    Context context;
    String destino;
    String ventana;

    public ListaPuntosAdapter(ArrayList<PuntoRecarga> listItem, Context context, String destino, String ventana) {
        this.listItem = listItem;
        this.context = context;
        this.destino = destino;
        this.ventana = ventana;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listItem);
    }

    @NonNull
    @Override
    public ListaPuntosAdapter.LibrosDisponiblesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (ventana){
            case "VERTICAL":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
                return new LibrosDisponiblesViewHolder(view);
            case "HORIZONTAL":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_user, parent, false);
                return new LibrosDisponiblesViewHolder(view);
            default:
                throw new IllegalStateException("Unexpected value: " + ventana);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPuntosAdapter.LibrosDisponiblesViewHolder holder, int position) {
        final PuntoRecarga item = listItem.get(position);


        switch (ventana){
            case "VERTICAL":
                holder.ubicacion.setText(item.getUbicacion());
                break;
            case "HORIZONTAL":
                holder.ubicacionU.setText(item.getUbicacion());
                holder.mapaU.setText(item.getMapa());
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
                List<PuntoRecarga> collection = listItem.stream().filter(i -> i.getNombre().toLowerCase().contains(buscar.toLowerCase())).collect(Collectors.toList());
                listItem.clear();
                listItem.addAll(collection);
            }else{
                for (PuntoRecarga c : listaOriginal){
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

        TextView ubicacion;

        TextView ubicacionU;
        TextView mapaU;


        public LibrosDisponiblesViewHolder(@NonNull View itemView) {
            super(itemView);
            ubicacion = itemView.findViewById(R.id.txtItem1);

            ubicacionU = itemView.findViewById(R.id.txtItemUbicacion);
            mapaU = itemView.findViewById(R.id.txtItemMapa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent;
                    String id;
                    switch (destino){
                        case "ACTUALIZAR":
                            intent = new Intent(context, AdminActualizarPuntoActivity.class);
                            id = listItem.get(getAdapterPosition()).getId();
                            intent.putExtra("idi", id);
                            context.startActivity(intent);
                            break;
                        case "VERPRESTARLIBRO":
                            intent = new Intent(context, UsuVisualizarPuntosActivity.class);
                            intent.putExtra("ID", listItem.get(getAdapterPosition()).getId());
                            context.startActivity(intent);
                            break;
                        case "VERLIBRO":
                            Uri link = Uri.parse(listItem.get(getAdapterPosition()).getMapa());
                            intent = new Intent(Intent.ACTION_VIEW, link);
                            //id = listItem.get(getAdapterPosition()).getId();
                            //intent.putExtra("ID", id);
                            context.startActivity(intent);
                            break;
                        case "HISTORIAL":
                            intent = new Intent(context, UsuVisualizarPuntosActivity.class);
                            intent.putExtra("ID", listItem.get(getAdapterPosition()).getId());
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
                List<PuntoRecarga> collection = listItem.stream().filter(i -> i.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listItem.clear();
                listItem.addAll(collection);
            }else {
                for (PuntoRecarga l: listaOriginal){
                    if (l.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listItem.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
