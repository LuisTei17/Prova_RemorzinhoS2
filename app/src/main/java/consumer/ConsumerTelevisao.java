package consumer;

import android.util.Log;

import java.util.List;

import pojo.Televisao;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;

import static android.content.ContentValues.TAG;

/**
 * Created by Jotaro on 10/31/2017.
 */

public class ConsumerTelevisao implements IUtelevisao {

    private IUtelevisao televisaoService;
    private Retrofit retrofit;

    public ConsumerTelevisao(){
        this.retrofit = new Retrofit.
                Builder().
                baseUrl(IUtelevisao.baseURL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        this.televisaoService= retrofit.create(IUtelevisao.class);
    }

    @Override
    public Call<Televisao> cadastraTelevisao(@Body Televisao televisao) {

        return this.televisaoService.cadastraTelevisao(televisao);
    }

    @Override
    public Call<Televisao> editaTelevisao(@Body Televisao televisao) {
        return this.televisaoService.editaTelevisao(televisao);
    }

    @Override
    public Call<List<Televisao>> pegaListaTelevisaos() {
        Log.d(TAG, "pegaListaTelevisaos: " + IUtelevisao.baseURL);
        return this.televisaoService.pegaListaTelevisaos();
    }

    @Override
    public Call<Televisao> pegaTelevisaoPorId(@Path("id") int id) {
        return this.televisaoService.pegaTelevisaoPorId(id);
    }

    @Override
    public Call<Televisao> deletaTelevisao(@Path("id") int id) {
        return this.televisaoService.deletaTelevisao(id);
    }
}
