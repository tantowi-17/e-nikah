package com.ahmfarisi.laundrypalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmfarisi.laundrypalembang.API.APIRequestData;
import com.ahmfarisi.laundrypalembang.API.RetroServer;
import com.ahmfarisi.laundrypalembang.Model.ResponseModel;
import com.ahmfarisi.laundrypalembang.R;
import com.ahmfarisi.laundrypalembang.Service.DatePickerService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

     EditText etNik, etNama, etAlamat, etAgama, etTelepon, etTanggalLahir, etUploadFoto,
            etUploadKtp, etUploadKk, etNikP, etNamaP, etAlamatP, etAgamaP, etTeleponP, etTanggalLahirP, etUploadFotoP,
            etUploadKtpP, etUploadKkP, etjadwal, etHari, etTempat;

     String nik_l, nama_l, alamat_l, agama_l, telepon_l,tanggal_lahir_l,foto_l,ktp_l,kk_l,
             nik_p, nama_p, alamat_p, agama_p, telepon_p,tanggal_lahir_p,foto_p,ktp_p,kk_p,
             tanggl_nikah, hari, tempat, imgUrl;

     Button btnSave;

     String part_image;
     ProgressDialog pd;
     Bitmap bitmap;
     ImageView imageFoto;



    final int REQUEST_GALLERY = 777;
    final int REQUEST_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        pd = new ProgressDialog(this);
        pd.setMessage("Proses save...");

        imageFoto = findViewById(R.id.img_foto);

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

        // upload image
        etUploadFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFoto();
            }
        });

        // button save
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //get data save
                nik_l = etNik.getText().toString();
                nama_l = etNama.getText().toString();
                alamat_l = etAlamat.getText().toString();
                agama_l = etAgama.getText().toString();
                telepon_l = etTelepon.getText().toString();
                tanggal_lahir_l = etTanggalLahir.getText().toString();
                imgUrl = convertToString();
//                foto_l = etUploadFoto.getText().toString();
                ktp_l = etUploadKtp.getText().toString();
                kk_l = etUploadKk.getText().toString();

                nik_p = etNikP.getText().toString();
                nama_p = etNamaP.getText().toString();
                alamat_p = etAlamatP.getText().toString();
                agama_p = etAgamaP.getText().toString();
                telepon_p = etTeleponP.getText().toString();
                tanggal_lahir_p = etTanggalLahirP.getText().toString();
                foto_p = etUploadFotoP.getText().toString();
                ktp_p = etUploadKtpP.getText().toString();
                kk_p = etUploadKkP.getText().toString();

                tanggl_nikah = etjadwal.getText().toString();
                hari = etHari.getText().toString();
                tempat = etTempat.getText().toString();

                if(nik_l.trim().equals("") && nik_p.trim().equals("")){
                    if (nik_l.trim().equals("")) {
                        etNik.setError("Nik laki - laki harus diisi !");
                    } else {
                        etNikP.setError("Nik perampuan harus diisi !");
                    }
                } else {
                    createData();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_GALLERY) {
                Uri path = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                    imageFoto.setImageBitmap(bitmap);
                    imageFoto.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create data nikah
     */
    private void createData(){
        pd.show();
        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<ResponseModel> saveData = ardData.ardCreateData(
                nik_l, nama_l, alamat_l, agama_l, telepon_l,tanggal_lahir_l,imgUrl,ktp_l,kk_l,
                nik_p, nama_p, alamat_p, agama_p, telepon_p,tanggal_lahir_p,foto_p,ktp_p,kk_p, tanggl_nikah, hari, tempat);

        saveData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                Log.d("Server Response", ""+responseModel.getPesan());

                Toast.makeText(TambahActivity.this, ""+responseModel.getPesan(), Toast.LENGTH_SHORT).show();
                imageFoto.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
                Toast.makeText(TambahActivity.this, "Server Not Found !", Toast.LENGTH_SHORT).show();
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

    public  void uploadFoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY);

//        Intent view = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(view, REQUEST_GALLERY);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
}
