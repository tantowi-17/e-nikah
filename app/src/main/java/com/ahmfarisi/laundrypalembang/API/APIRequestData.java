package com.ahmfarisi.laundrypalembang.API;

import com.ahmfarisi.laundrypalembang.Model.ResponseModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("nik_l") String nik_l,
            @Field("nama_l") String nama_l,
            @Field("alamat_l") String alamat_l,
            @Field("agama_l") String agama_l,
            @Field("telepon_l") String telepon_l,
            @Field("tanggal_lahir_l") String tanggal_lahir_l,
            @Part("foto_l") String foto_l,
            @Field("ktp_l") String ktp_l,
            @Field("kk_l") String kk_l,
            @Field("nik_p") String nik_p,
            @Field("nama_p") String nama_p,
            @Field("alamat_p") String alamat_p,
            @Field("agama_p") String agama_p,
            @Field("telepon_p") String telepon_p,
            @Field("tanggal_lahir_p") String tanggal_lahir_p,
            @Field("foto_p") String foto_p,
            @Field("ktp_p") String ktp_p,
            @Field("kk_p") String kk_p,
            @Field("tanggal_nikah") String tanggal_nikah,
            @Field("hari") String hari,
            @Field("tempat") String tempat
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
        @Field("id") int id
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("nik_l") String nik_l,
            @Field("nama_l") String nama_l,
            @Field("alamat_l") String alamat_l,
            @Field("agama_l") String agama_l,
            @Field("telepon_l") String telepon_l,
            @Field("tanggal_lahir_l") String tanggal_lahir_l,
            @Field("foto_l") String foto_l,
            @Field("ktp_l") String ktp_l,
            @Field("kk_l") String kk_l,
            @Field("nik_p") String nik_p,
            @Field("nama_p") String nama_p,
            @Field("alamat_p") String alamat_p,
            @Field("agama_p") String agama_p,
            @Field("telepon_p") String telepon_p,
            @Field("tanggal_lahir_p") String tanggal_lahir_p,
            @Field("foto_p") String foto_p,
            @Field("ktp_p") String ktp_p,
            @Field("kk_p") String kk_p,
            @Field("tanggal_nikah") String tanggal_nikah,
            @Field("hari") String hari,
            @Field("tempat") String tempat
    );

}
