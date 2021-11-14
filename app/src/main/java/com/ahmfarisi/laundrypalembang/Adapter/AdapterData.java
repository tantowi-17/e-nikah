package com.ahmfarisi.laundrypalembang.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmfarisi.laundrypalembang.API.APIRequestData;
import com.ahmfarisi.laundrypalembang.API.RetroServer;
import com.ahmfarisi.laundrypalembang.Activity.LihatDataNikah;
import com.ahmfarisi.laundrypalembang.Activity.UbahActivity;
import com.ahmfarisi.laundrypalembang.Model.DataModel;
import com.ahmfarisi.laundrypalembang.Model.ResponseModel;
import com.ahmfarisi.laundrypalembang.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listNikah;
    private int idNikah;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvNamaL.setText(dm.getNama_l());
        holder.tvNamaP.setText(dm.getNama_p());
        holder.tvAlamat.setText(dm.getAlamat_l());
        holder.tvJadwal.setText(dm.getTanggal_nikah());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNamaL, tvNamaP, tvAlamat, tvJadwal;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.id_nikah);
            tvNamaL = itemView.findViewById(R.id.tv_nama_l);
            tvNamaP = itemView.findViewById(R.id.tv_nama_p);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJadwal = itemView.findViewById(R.id.tv_jadwal_nikah);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang Akan Dilakukan");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.drawable.ic_add);
                    dialogPesan.setCancelable(true);

                    idNikah = Integer.parseInt(tvId.getText().toString());

                    dialogPesan.setPositiveButton(R.string.delete_data, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((LihatDataNikah) ctx).retrieveData();
                                }
                            }, 1000);
                        }
                    });

                    dialogPesan.setNegativeButton(R.string.update_data, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idNikah);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idNikah);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listNikah = response.body().getData();

                    int vIdNikah = listNikah.get(0).getId();

                    // var laki
                    String vNikLaki = listNikah.get(0).getNik_l();
                    String vNamaLakiNikah = listNikah.get(0).getNama_l();
                    String vAlamatLaki = listNikah.get(0).getAlamat_l();
                    String vAgamaLaki = listNikah.get(0).getAgama_l();
                    String vTeleponLaki = listNikah.get(0).getTelepon_l();
                    String vTanggalLahirLaki = listNikah.get(0).getTanggal_lahir_l();
                    String vFotoLaki = listNikah.get(0).getFoto_l();
                    String vKtpLaki = listNikah.get(0).getKtp_l();
                    String vKkLaki = listNikah.get(0).getKk_l();

                    // var perempuan
                    String vNikPerem = listNikah.get(0).getNik_p();
                    String vNamaPerem = listNikah.get(0).getNama_p();
                    String vAlamatPerem = listNikah.get(0).getAlamat_p();
                    String vAgamaPerem = listNikah.get(0).getAgama_p();
                    String vTeleponPerem = listNikah.get(0).getTelepon_p();
                    String vTanggalLahirPerem = listNikah.get(0).getTanggal_lahir_p();
                    String vFotoPerem = listNikah.get(0).getFoto_p();
                    String vKtpPerem = listNikah.get(0).getKtp_p();
                    String vKkPerem = listNikah.get(0).getKk_p();

                    String vTanggalNikah = listNikah.get(0).getTanggal_nikah();
                    String vHariNikah = listNikah.get(0).getHari();
                    String vTempatNikah = listNikah.get(0).getTempat();

                    //Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan+ " | Data : "+varIdLaundry+" | "+varNamaLaundry + " | "+varAlamatLaundry+" | "+varTeleponLaundry, Toast.LENGTH_SHORT).show();

                    Intent intentSend = new Intent(ctx, UbahActivity.class);
                    intentSend.putExtra("xIdNikah", vIdNikah);
                    intentSend.putExtra("xNikLaki", vNikLaki);
                    intentSend.putExtra("xNamaLakiNikah", vNamaLakiNikah);
                    intentSend.putExtra("xAlamatLaki", vAlamatLaki);
                    intentSend.putExtra("xAgamaLaki", vAgamaLaki);
                    intentSend.putExtra("xTeleponLaki", vTeleponLaki);
                    intentSend.putExtra("xTanggalLahirLaki", vTanggalLahirLaki);
                    intentSend.putExtra("xFotoLaki", vFotoLaki);
                    intentSend.putExtra("xKtpLaki", vKtpLaki);
                    intentSend.putExtra("xKkLaki", vKkLaki);
                    intentSend.putExtra("xNikPerem", vNikPerem);
                    intentSend.putExtra("xNamaPerem", vNamaPerem);
                    intentSend.putExtra("xAlamatPerem", vAlamatPerem);
                    intentSend.putExtra("xAgamaPerem", vAgamaPerem);
                    intentSend.putExtra("xTeleponPerem", vTeleponPerem);
                    intentSend.putExtra("xTanggalLahirPerem", vTanggalLahirPerem);
                    intentSend.putExtra("xFotoPerem", vFotoPerem);
                    intentSend.putExtra("xKkPerem", vKkPerem);
                    intentSend.putExtra("xKtpPerem", vKtpPerem);
                    intentSend.putExtra("xTanggalNikah", vTanggalNikah);
                    intentSend.putExtra("xHariNikah", vHariNikah);
                    intentSend.putExtra("xTempatNikah", vTempatNikah);
                    ctx.startActivity(intentSend);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
