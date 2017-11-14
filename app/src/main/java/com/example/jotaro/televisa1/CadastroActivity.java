package com.example.jotaro.televisa1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import consumer.ConsumerTelevisao;
import pojo.Televisao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {
    private AutoCompleteTextView etMarca;
    private EditText etModelo, etPeso;
    private CheckBox cbcaption, cbdigital, cbsap;
    private RadioButton rbHD, rbFullHD, rb4K;
    private Button btEnvia, btExcluir;
    private Televisao televisao;

    Intent intencaoEditar;

    private String[] marcas = {"LG", "Sony", "Samsung", "Phillips"};

    private Televisao televisaoIntent;

    private String marca, modelo, peso, resolucao;
    private boolean caption, digital, sap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final ConsumerTelevisao consumerTelevisao = new ConsumerTelevisao();

        inicializaComponentes();
        btExcluir.setVisibility(btExcluir.GONE);

        intencaoEditar = getIntent();
        if(intencaoEditar.getSerializableExtra("televisao") != null) {
            btExcluir.setVisibility(btExcluir.VISIBLE);
            televisaoIntent = (Televisao) intencaoEditar.getSerializableExtra("televisao");

            etMarca.setText(televisaoIntent.getMarca());
            etModelo.setText(televisaoIntent.getModelo());
            etPeso.setText(televisaoIntent.getPeso());

            if(rb4K.getText().toString().equals(televisaoIntent.getResolucao())) { rb4K.setChecked(true); };
            if(rbFullHD.getText().toString().equals(televisaoIntent.getResolucao())) { rbFullHD.setChecked(true); };
            if(rbHD.getText().toString().equals(televisaoIntent.getResolucao())) { rbHD.setChecked(true); };

            if(televisaoIntent.iscaption()) { cbcaption.setChecked(true);};
            if(televisaoIntent.isdigital()) { cbdigital.setChecked(true);};
            if(televisaoIntent.issap()) { cbsap.setChecked(true);};
            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    consumerTelevisao.deletaTelevisao(televisaoIntent.getId()).enqueue(new Callback<Televisao>() {
                        @Override
                        public void onResponse(Call<Televisao> call, Response<Televisao> response) {

                                Intent intencaoMain = new Intent(CadastroActivity.this, MainActivity.class);
                                startActivity(intencaoMain);

                        }

                        @Override
                        public void onFailure(Call<Televisao> call, Throwable t) {
                            Toast.makeText(CadastroActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }


        btEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marca = etMarca.getText().toString();
                modelo = etModelo.getText().toString();
                peso = etPeso.getText().toString();

                if (rb4K.isChecked()) { resolucao = rb4K.getText().toString(); }
                if (rbFullHD.isChecked()) { resolucao = rbFullHD.getText().toString(); }
                if (rbHD.isChecked()) { resolucao = rbHD.getText().toString(); };

                caption = cbcaption.isChecked();
                digital = cbdigital.isChecked();
                sap = cbsap.isChecked();

                televisao = new Televisao(marca, modelo, peso, caption, digital, sap, resolucao);
                if(intencaoEditar.getSerializableExtra("televisao") != null) {
                    televisao.setId(televisaoIntent.getId());

                    consumerTelevisao.editaTelevisao(televisao).enqueue(new Callback<Televisao>() {
                        @Override
                        public void onResponse(Call<Televisao> call, Response<Televisao> response) {
                            Intent intencaoMain = new Intent(CadastroActivity.this, MainActivity.class);
                            startActivity(intencaoMain);
                        }

                        @Override
                        public void onFailure(Call<Televisao> call, Throwable t) {

                        }
                    });
                }


                consumerTelevisao.cadastraTelevisao(televisao).enqueue(new Callback<Televisao>() {
                    @Override
                    public void onResponse(Call<Televisao> call, Response<Televisao> response) {
                        if(response.isSuccessful()) {
                            Intent intencaoMain = new Intent(CadastroActivity.this, ListaActivity.class);
                            startActivity(intencaoMain);
                        }
                        Toast.makeText(CadastroActivity.this, "Não cadastrado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Televisao> call, Throwable t) {
                        Toast.makeText(CadastroActivity.this, "Não conectou", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }

    public void inicializaComponentes() {
        this.etMarca = (AutoCompleteTextView) findViewById(R.id.etMarca);
        this.etModelo = (EditText) findViewById(R.id.etModelo);
        this.etPeso = (EditText) findViewById(R.id.etPeso);
        this.cbcaption = (CheckBox) findViewById(R.id.cbcaption);
        this.cbdigital = (CheckBox) findViewById(R.id.cbdigital);
        this.cbsap = (CheckBox) findViewById(R.id.cbsap);
        this.rb4K = (RadioButton) findViewById(R.id.rb4K);
        this.rbFullHD = (RadioButton) findViewById(R.id.rbFullHD);
        this.rbHD = (RadioButton) findViewById(R.id.rbHD);
        this.btEnvia = (Button) findViewById(R.id.btEnviar);
        this.btExcluir = (Button) findViewById(R.id.btExcluir);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.marcas);

        this.etMarca.setAdapter(adapter);

    }
}
