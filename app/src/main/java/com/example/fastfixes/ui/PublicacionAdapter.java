package com.example.fastfixes.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;
import com.example.fastfixes.utils.ImageUtils;

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

        // Convertir la cadena Base64 a un Bitmap
        String imagenBase64 = publicacion.getImagen();
        if (imagenBase64 != null && !imagenBase64.isEmpty()) {
            Bitmap bitmap = ImageUtils.base64ToBitmap(imagenBase64);
            if (bitmap != null) {
                holder.ivPublicacion.setImageBitmap(bitmap);
            } else {
                holder.ivPublicacion.setImageResource(R.drawable.error); // Imagen de error si falla la decodificación
            }
        } else {
            holder.ivPublicacion.setImageResource(R.drawable.placeholder); // Imagen por defecto si no hay imagen
        }
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

