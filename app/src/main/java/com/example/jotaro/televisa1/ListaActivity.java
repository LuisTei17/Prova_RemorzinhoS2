package com.example.jotaro.televisa1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import consumer.ConsumerTelevisao;
import pojo.Televisao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActivity extends ListActivity {

    private List<Televisao> listTelevisoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConsumerTelevisao consumerTelevisao = new ConsumerTelevisao();

        consumerTelevisao.pegaListaTelevisaos().enqueue(new Callback<List<Televisao>>() {
            @Override
            public void onResponse(Call<List<Televisao>> call, Response<List<Televisao>> response) {
                if(response.isSuccessful()) {
                    listTelevisoes = response.body();

                    ArrayAdapter adapter = new ArrayAdapter(ListaActivity.this, android.R.layout.simple_list_item_1, listTelevisoes);
                    setListAdapter(adapter);
                } else {
                    Toast.makeText(ListaActivity.this, "Erro ao carregar a lista", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Televisao>> call, Throwable t) {
                Toast.makeText(ListaActivity.this, "Deu ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intencaoEdita = new Intent(ListaActivity.this, CadastroActivity.class);
        Bundle params = new Bundle();
        params.putSerializable("televisao", listTelevisoes.get(position));
        intencaoEdita.putExtras(params);
        startActivity(intencaoEdita);
    }
}
