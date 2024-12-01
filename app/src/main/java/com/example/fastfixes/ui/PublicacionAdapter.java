package com.example.fastfixes.ui;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private final Context context;
    private final List<Publicacion> publicaciones;
    private final String tipoUsuario; // Campo para tipo de usuario

    // Constructor
    public PublicacionAdapter(Context context, List<Publicacion> publicaciones, String tipoUsuario) {
        this.context = context;
        this.publicaciones = publicaciones;
        this.tipoUsuario = tipoUsuario; // Asignamos el tipo de usuario
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
        holder.tvLugar.setText("Lugar: "+publicacion.getLugar());
        holder.tvTelefono.setText("Telefono: "+publicacion.getTelefono());
        holder.tvEstadoFecha.setText("Estado: " + publicacion.getEstado() + " | Fecha: " + publicacion.getFecha());
        holder.tvCliente.setText("Cliente: " + publicacion.getCliente());

        // Condición para ocultar los botones si el tipo de usuario no es "Profesional"
        if (!"Profesional".equals(tipoUsuario)) {
            holder.btnAceptar.setVisibility(View.GONE);
            holder.btnLlamar.setVisibility(View.GONE);
            holder.btnWhatsapp.setVisibility(View.GONE);
        } else {
            holder.btnAceptar.setVisibility(View.VISIBLE);
            holder.btnLlamar.setVisibility(View.VISIBLE);
            holder.btnWhatsapp.setVisibility(View.VISIBLE);
        }

        // Mostrar btnAceptar solo si el estado es "Solicitado"
        if ("Solicitado".equals(publicacion.getEstado())) {
            holder.btnAceptar.setVisibility(View.VISIBLE); // Mostrar si el estado es "Solicitado"
        } else {
            holder.btnAceptar.setVisibility(View.GONE); // Ocultar si el estado no es "Solicitado"
        }

        // Obtener y decodificar la imagen Base64
        String imagenBase64 = publicacion.getImagen();
        if (imagenBase64 != null && !imagenBase64.isEmpty()) {
            try {
                // Decodificar Base64 a bytes
                byte[] decodedBytes = Base64.decode(imagenBase64, Base64.DEFAULT);

                // Cargar la imagen con Glide
                Glide.with(context)
                        .asBitmap() // Trabajar con Bitmaps
                        .load(decodedBytes) // Pasar los bytes decodificados
                        .override(200, 200) // Redimensionar a 300x300 píxeles (ajustar según diseño)
                        .placeholder(R.drawable.placeholder) // Imagen de carga
                        .error(R.drawable.error) // Imagen de error
                        .diskCacheStrategy(DiskCacheStrategy.NONE) // Evitar caché en disco
                        .skipMemoryCache(true) // Evitar caché en memoria
                        .into(holder.ivPublicacion);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                holder.ivPublicacion.setImageResource(R.drawable.error); // Imagen de error
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
        TextView tvTitulo, tvDescripcion, tvCliente, tvEstadoFecha, tvLugar, tvTelefono;
        Button btnAceptar, btnLlamar, btnWhatsapp;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPublicacion = itemView.findViewById(R.id.ivPublicacion);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvLugar = itemView.findViewById(R.id.tvLugar);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvEstadoFecha = itemView.findViewById(R.id.tvEstadoFecha);

            // Inicialización de los botones
            btnAceptar = itemView.findViewById(R.id.btnAceptar);
            btnLlamar = itemView.findViewById(R.id.btnLlamar);
            btnWhatsapp = itemView.findViewById(R.id.btnWhatsapp);
        }
    }
}




