package com.example.aplikasimanagemetaset.ui.history;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.AplikasiManagemetAset.R;
import com.example.aplikasimanagemetaset.model.ModelDatabase;
import com.example.aplikasimanagemetaset.ui.report.ReportActivity;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 30-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<ModelDatabase> modelDatabase;
    Context mContext;
    HistoryAdapterCallback mAdapterCallback;

    public HistoryAdapter(Context context, List<ModelDatabase> modelDatabaseList,
                          HistoryAdapterCallback adapterCallback) {
        this.mContext = context;
        this.modelDatabase = modelDatabaseList;
        this.mAdapterCallback = adapterCallback;
    }

    public void setDataAdapter(List<ModelDatabase> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final ModelDatabase data = modelDatabase.get(position);

        if (data == null) return;

//        Log.d("category", data.kategori);
//        Log.d("getNama", data.nama);
//        Log.d("getTanggal", data.tanggal);
//        Log.d("getLokasi", data.lokasi);

        holder.tvKategori.setText(data.getKategori());
        holder.tvNama.setText(data.getNama());
        holder.tvDate.setText(data.getTanggal());
        holder.tvLokasi.setText(data.getLokasi());

        if (data.kategori == null) return;

        switch (data.kategori) {
            case "Laporan Kebakaran":
                holder.layoutHeader.setBackgroundResource(R.color.red);
                break;
            case "Laporan Medis":
                holder.layoutHeader.setBackgroundResource(R.color.blue);
                break;
            case "Laporan Bencana Alam":
                holder.layoutHeader.setBackgroundResource(R.color.green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    public interface HistoryAdapterCallback {
        void onDelete(ModelDatabase modelLaundry);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKategori, tvNama, tvDate, tvLokasi, tvUbah;
        public CardView cvHistory;
        public LinearLayout layoutHeader;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            cvHistory = itemView.findViewById(R.id.cvHistory);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);
            tvUbah = itemView.findViewById(R.id.tvUbah);

            tvUbah.setOnClickListener(view -> {
                ModelDatabase modelLaundry = modelDatabase.get(getAdapterPosition());

                Intent intent = new Intent(mContext, ReportActivity.class);
                intent.putExtra("TITLE", modelLaundry.kategori);
                intent.putExtra("EXTRA_TYPE", "edit");

                intent.putExtra("EXTRA_UID", modelLaundry.uid);
//                intent.putExtra("EXTRA_IMAGE", modelLaundry.image);
                intent.putExtra("EXTRA_NAMA", modelLaundry.nama);
                intent.putExtra("EXTRA_TELEPON", modelLaundry.telepon);
                intent.putExtra("EXTRA_LOKASI", modelLaundry.lokasi);
                intent.putExtra("EXTRA_TANGGAL", modelLaundry.tanggal);
                intent.putExtra("EXTRA_ISI_LAPORAN", modelLaundry.isi_laporan);

                mContext.startActivity(intent);
            });

            cvHistory.setOnClickListener(view -> {
                ModelDatabase modelLaundry = modelDatabase.get(getAdapterPosition());
                mAdapterCallback.onDelete(modelLaundry);
            });
        }
    }

}
