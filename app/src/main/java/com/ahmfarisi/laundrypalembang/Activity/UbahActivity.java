package com.ahmfarisi.laundrypalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmfarisi.laundrypalembang.API.APIRequestData;
import com.ahmfarisi.laundrypalembang.API.RetroServer;
import com.ahmfarisi.laundrypalembang.Model.ResponseModel;
import com.ahmfarisi.laundrypalembang.R;
import com.ahmfarisi.laundrypalembang.Service.DatePickerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private int xIdNikah;
    String xNikLaki,xNamaLakiNikah,xAlamatLaki,xAgamaLaki,xTeleponLaki,
            xTanggalLahirLaki,xFotoLaki,xKtpLaki,xKkLaki,
            xNikPerem,xNamaPerem,xAlamatPerem,xAgamaPerem,xTeleponPerem,
            xTanggalLahirPerem,xFotoPerem,xKtpPerem,xKkPerem,xTanggalNikah,xHariNikah,xTempatNikah;

    String yNikLaki,yNamaLakiNikah,yAlamatLaki,yAgamaLaki,yTeleponLaki,
            yTanggalLahirLaki,yFotoLaki,yKtpLaki,yKkLaki,
            yNikPerem,yNamaPerem,yAlamatPerem,yAgamaPerem,yTeleponPerem,
            yTanggalLahirPerem,yFotoPerem,yKtpPerem,yKkPerem,yTanggalNikah,yHariNikah,yTempatNikah;

    EditText etNik, etNama, etAlamat, etAgama, etTelepon, etTanggalLahir, etUploadFoto,
            etUploadKtp, etUploadKk;
    EditText etNikP, etNamaP, etAlamatP, etAgamaP, etTeleponP, etTanggalLahirP, etUploadFotoP,
            etUploadKtpP, etUploadKkP;
    EditText etjadwal, etHari, etTempat;

    String nik_l, nama_l, alamat_l, agama_l, telepon_l,tanggal_lahir_l,foto_l,ktp_l,kk_l,
            nik_p, nama_p, alamat_p, agama_p, telepon_p,tanggal_lahir_p,foto_p,ktp_p,kk_p,
            tanggl_nikah, hari, tempat;

    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xIdNikah = terima.getIntExtra("xIdNikah", -1);
        xNikLaki  = terima.getStringExtra("xNikLaki");
        xNamaLakiNikah =  terima.getStringExtra("xNamaLakiNikah");
        xAlamatLaki =  terima.getStringExtra("xAlamatLaki");
        xAgamaLaki = terima.getStringExtra("xAgamaLaki");
        xTeleponLaki = terima.getStringExtra("xTeleponLaki");
        xTanggalLahirLaki = terima.getStringExtra("xTanggalLahirLaki");
        xFotoLaki = terima.getStringExtra("xFotoLaki");
        xKtpLaki = terima.getStringExtra("xKtpLaki");
        xKkLaki = terima.getStringExtra("xKkLaki");
        xNikPerem = terima.getStringExtra("xNikPerem");
        xNamaPerem = terima.getStringExtra("xNamaPerem");
        xAlamatPerem = terima.getStringExtra("xAlamatPerem");
        xAgamaPerem = terima.getStringExtra("xAgamaPerem");
        xTeleponPerem = terima.getStringExtra("xTeleponPerem");
        xTanggalLahirPerem = terima.getStringExtra("xTanggalLahirPerem");
        xFotoPerem = terima.getStringExtra("xFotoPerem");
        xKtpPerem = terima.getStringExtra("xKtpPerem");
        xKkPerem = terima.getStringExtra("xKkPerem");
        xTanggalNikah = terima.getStringExtra("xTanggalNikah");
        xHariNikah = terima.getStringExtra("xHariNikah");
        xTempatNikah = terima.getStringExtra("xTempatNikah");

        // data calon laki-laki
        etNik = findViewById(R.id.et_nik);
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etAgama = findViewById(R.id.et_agama);
        etTelepon = findViewById(R.id.et_telepon);
        etTanggalLahir = findViewById(R.id.et_tangggal_l);
        etUploadFoto = findViewById(R.id.et_upload_foto);
        etUploadKtp = findViewById(R.id.et_upload_ktp);
        etUploadKk = findViewById(R.id.et_upload_kk);

        // data calon perempuan
        etNikP = findViewById(R.id.et_nik_p);
        etNamaP = findViewById(R.id.et_nama_p);
        etAlamatP = findViewById(R.id.et_alamat_p);
        etAgamaP = findViewById(R.id.et_agama_p);
        etTeleponP = findViewById(R.id.et_telepon_p);
        etTanggalLahirP = findViewById(R.id.et_tangggal_l_p);
        etUploadFotoP = findViewById(R.id.et_upload_foto_p);
        etUploadKtpP = findViewById(R.id.et_upload_ktp_p);
        etUploadKkP = findViewById(R.id.et_upload_kk_p);

        // jadwal
        etjadwal = findViewById(R.id.et_jadwal);
        etHari = findViewById(R.id.et_hari);
        etTempat = findViewById(R.id.et_tempat);
        btnUpdate = findViewById(R.id.btn_update);

        etNik.setText(xNikLaki);
        etNama.setText(xNamaLakiNikah);
        etAlamat.setText(xAlamatLaki);
        etAgama.setText(xAgamaLaki);
        etTelepon.setText(xTeleponLaki);
        etTanggalLahir.setText(xTanggalLahirLaki);
        etUploadFoto.setText(xFotoLaki);
        etUploadKtp.setText(xKtpLaki);
        etUploadKk.setText(xKkLaki);

        etNikP.setText(xNikPerem);
        etNamaP.setText(xNamaPerem);
        etAlamatP.setText(xAlamatPerem);
        etAgamaP.setText(xAgamaPerem);
        etTeleponP.setText(xTeleponPerem);
        etTanggalLahirP.setText(xTanggalLahirPerem);
        etUploadFotoP.setText(xFotoPerem);
        etUploadKtpP.setText(xKtpPerem);
        etUploadKkP.setText(xKkPerem);

        etjadwal.setText(xTanggalNikah);
        etHari.setText(xHariNikah);
        etTempat.setText(xTempatNikah);

        // onClick date laki
        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerLaki();
            }
        });

        // onClick date perempuan
        etTanggalLahirP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerPerem();
            }
        });

        // onClick date nikah
        etjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerNikah();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNikLaki = etNik.getText().toString();
                yNamaLakiNikah = etNama.getText().toString();
                yAlamatLaki = etAlamat.getText().toString();
                yAgamaLaki = etAgama.getText().toString();
                yTeleponLaki = etTelepon.getText().toString();
                yTanggalLahirLaki = etTanggalLahir.getText().toString();
                yFotoLaki = etUploadFoto.getText().toString();
                yKtpLaki = etUploadKtp.getText().toString();
                yKkLaki = etUploadKk.getText().toString();

                yNikPerem = etNikP.getText().toString();
                yNamaPerem = etNamaP.getText().toString();
                yAlamatPerem = etAlamatP.getText().toString();
                yAgamaPerem = etAgamaP.getText().toString();
                yTeleponPerem = etTeleponP.getText().toString();
                yTanggalLahirPerem = etTanggalLahirP.getText().toString();
                yFotoPerem = etUploadFotoP.getText().toString();
                yKtpPerem = etUploadKtpP.getText().toString();
                yKkPerem = etUploadKkP.getText().toString();

                yTanggalNikah = etjadwal.getText().toString();
                yHariNikah = etHari.getText().toString();
                yTempatNikah = etTempat.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xIdNikah,
                yNikLaki,yNamaLakiNikah,yAlamatLaki,yAgamaLaki,yTeleponLaki, yTanggalLahirLaki,yFotoLaki,yKtpLaki,yKkLaki,
                yNikPerem,yNamaPerem,yAlamatPerem,yAgamaPerem,yTeleponPerem, yTanggalLahirPerem,yFotoPerem,yKtpPerem,yKkPerem,yTanggalNikah,yHariNikah,yTempatNikah);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDatePickerLaki(){
        DatePickerService datePickerService = new DatePickerService();
        datePickerService.show(getSupportFragmentManager(), "data");
        datePickerService.setOnDateClickListener(new DatePickerService.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String year = ""+datePicker.getYear();
                String month = ""+(datePicker.getMonth()+1);
                String day = ""+datePicker.getDayOfMonth();
                String text = day+" - "+month+" - "+year;
                etTanggalLahir.setText(text);
            }
        });
    }

    public void showDatePickerPerem(){
        DatePickerService datePickerService = new DatePickerService();
        datePickerService.show(getSupportFragmentManager(), "data");
        datePickerService.setOnDateClickListener(new DatePickerService.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String year = ""+datePicker.getYear();
                String month = ""+(datePicker.getMonth()+1);
                String day = ""+datePicker.getDayOfMonth();
                String text = day+" - "+month+" - "+year;
                etTanggalLahirP.setText(text);
            }
        });
    }

    public void showDatePickerNikah(){
        DatePickerService datePickerService = new DatePickerService();
        datePickerService.show(getSupportFragmentManager(), "data");
        datePickerService.setOnDateClickListener(new DatePickerService.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String year = ""+datePicker.getYear();
                String month = ""+(datePicker.getMonth()+1);
                String day = ""+datePicker.getDayOfMonth();
                String text = day+" - "+month+" - "+year;
                etjadwal.setText(text);
            }
        });
    }
}