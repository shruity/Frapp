package com.frapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class DataAdapterFavourites extends RecyclerView.Adapter<DataAdapterFavourites.MyViewHolder> {

    private List<DataModel> dataModelList;
    private Context context;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_description;
        private TextView tv_type,tv_view_count;
        private RoundedImageView imageView;
        private LinearLayout linearLayout;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            tv_title        = (TextView) view.findViewById(R.id.tv_title);
            tv_description  = (TextView) view.findViewById(R.id.tv_description);
            tv_type         = (TextView) view.findViewById(R.id.tv_type);
            tv_view_count   = (TextView) view.findViewById(R.id.tv_view_count);
            imageView       = (RoundedImageView) view.findViewById(R.id.iv_list);
            linearLayout    = (LinearLayout) view.findViewById(R.id.ll_favourites);
            cardView        = (CardView) view.findViewById(R.id.card_view);

        }
    }

    public DataAdapterFavourites(Context context, List<DataModel> dataModelList) {
        this.dataModelList       = dataModelList;
        this.context             = context;
        activity                 = (Activity) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_adapter_layout_favourites, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataModel dataModel = dataModelList.get(position);

        final String image_url      = dataModel.getImage_url();
        final String title          = dataModel.getTitle();
        final String description    = dataModel.getDescription();
        final String type           = dataModel.getType();
        final String view_count     = dataModel.getView_count();

        int count = Integer.parseInt(view_count);
        double j = count/1000.0;

        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        String view_count_temp = decimalFormat.format(j);

        String desc = context.getResources().getString(R.string.Description);
        String desc_final = desc.concat(" ").concat(description);

        String type1 = context.getResources().getString(R.string.Type);
        String type_final = type1.concat(" ").concat(type);

        String view_count1 = context.getResources().getString(R.string.view_count);
        String view_count_final = view_count1.concat(" ").concat(view_count_temp).concat("k");

        holder.tv_title.setText(title);
        holder.tv_description.setText(desc_final);
        holder.tv_type.setText(type_final);
        holder.tv_view_count.setText(view_count_final);

        Glide.with(context).load(image_url).asBitmap().into(holder.imageView);

        if (type.equals("offer")){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.list_color));
        }
        else if(type.equals("internship")){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.Tool_Bar_color));
        }

    }

    public int item_id(int pos){
        DataModel rm=dataModelList.get(pos);
        int id= Integer.parseInt(rm.getId());
        return id;
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

}

