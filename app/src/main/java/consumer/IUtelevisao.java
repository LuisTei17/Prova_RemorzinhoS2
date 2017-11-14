package consumer;

import java.util.List;

import pojo.Televisao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Jotaro on 11/14/2017.
 */

public interface IUtelevisao {

    final static String baseURL = "http://192.168.241.94:8080/compubras/";

    @POST("televisao/")
    Call<Televisao> cadastraTelevisao(@Body Televisao televisao);

    @PUT("televisao/")
    Call<Televisao> editaTelevisao(@Body Televisao televisao) ;

    @GET("televisao/")
    Call<List<Televisao>> pegaListaTelevisaos();

    @GET("televisao/{id}")
    Call<Televisao> pegaTelevisaoPorId(@Path("id") int id);

    @DELETE("televisao/{id}")
    Call<Televisao> deletaTelevisao(@Path("id") int id);
}
