package com.example.fastfixes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private final Context context;
    private final List<Publicacion> publicaciones;

    // Constructor
    public PublicacionAdapter(Context context, List<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion, parent, false);
        return new PublicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        Publicacion publicacion = publicaciones.get(position);

        // Bind de los datos
        holder.tvTitulo.setText(publicacion.getTitulo());
        holder.tvDescripcion.setText(publicacion.getDescripcion());
        holder.tvEstadoFecha.setText("Estado: " + publicacion.getEstado() + " | Fecha: " + publicacion.getFecha());

        // Cargar la imagen usando Glide
        Glide.with(context)
                .load(publicacion.getImagen())
                .placeholder(R.drawable.placeholder) // Imagen de carga por defecto
                .error(R.drawable.error) // Imagen de error
                .into(holder.ivPublicacion);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    // ViewHolder interno
    static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPublicacion;
        TextView tvTitulo, tvDescripcion, tvEstadoFecha;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPublicacion = itemView.findViewById(R.id.ivPublicacion);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvEstadoFecha = itemView.findViewById(R.id.tvEstadoFecha);
        }
    }
}
