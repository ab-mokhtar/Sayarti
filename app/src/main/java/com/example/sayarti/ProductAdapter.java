package com.example.sayarti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final Context mCtx;
    private final List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image
//        Glide.with(mCtx)
//                .load(product.getImage())
//                .into(holder.imageView);

        holder.nom.setText(product.getNom());
        holder.prix.setText(product.getPrix());
        holder.type_carburant.setText(product.getType_carburant());
        holder.puissance_fiscale.setText(product.getPuissance_fiscale());
        holder.boite.setText(product.getBoite());
        holder.puissance_ch.setText(product.getPuissance_ch());
        holder.trans.setText(product.getTrans());
        Glide.with(mCtx)
                .load(product.getPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView nom;
        private final TextView prix;
        private final TextView type_carburant;
        private final TextView puissance_fiscale;
        private final TextView puissance_ch;
        private final TextView boite;
        private final TextView trans;
        private final ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nomVoiture);
            prix = itemView.findViewById(R.id.prix);
            type_carburant=itemView.findViewById(R.id.type_carb);
            puissance_fiscale = itemView.findViewById(R.id.PuissanceF);
            puissance_ch = itemView.findViewById(R.id.puissance_ch);
            boite = itemView.findViewById(R.id.Boite);
            imageView = itemView.findViewById(R.id.imageView);
            trans = itemView.findViewById(R.id.Trans);
        }
    }
}