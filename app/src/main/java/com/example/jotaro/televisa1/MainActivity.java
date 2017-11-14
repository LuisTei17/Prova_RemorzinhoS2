package com.example.jotaro.televisa1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    public String[] opcoes = {"Cadastrar", "Listar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcoes);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position == 0) {
            Intent intencaoCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intencaoCadastrar);
        } else if(position == 1){
            Intent intencaoListar = new Intent(MainActivity.this, ListaActivity.class);
            startActivity(intencaoListar);
        }
    }
}
