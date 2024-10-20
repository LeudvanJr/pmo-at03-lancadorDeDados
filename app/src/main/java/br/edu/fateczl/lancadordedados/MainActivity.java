package br.edu.fateczl.lancadordedados;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner spTiposDados;
    private RadioButton rbUmDado;
    private RadioButton rbDoisDados;
    private RadioButton rbTresDados;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spTiposDados = findViewById(R.id.spTiposDados);
        rbUmDado = findViewById(R.id.rbUmDado);
        rbUmDado.setChecked(true);
        rbDoisDados = findViewById(R.id.rbDoisDados);
        rbTresDados = findViewById(R.id.rbTresDados);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnLancar = findViewById(R.id.btnLancar);

        int[] dados = {4,6,8,10,12,20,100};
        preencherSpinnerTipos(dados);
        btnLancar.setOnClickListener(op -> lancarDados(dados));


    }

    private void lancarDados(int[] dados){
        Random rd = new Random();
        int posicaoDado = spTiposDados.getSelectedItemPosition();
        int dado = dados[posicaoDado];
        StringBuilder saida = new StringBuilder();

        if(rbUmDado.isChecked()){
            saida.append(rd.nextInt(dado)+1);
        }
        else if(rbDoisDados.isChecked()){
            saida.append((rd.nextInt(dado) + 1)).append(", ");
            saida.append(rd.nextInt(dado) + 1);
        }
        else if(rbTresDados.isChecked()){
            saida.append((rd.nextInt(dado) + 1)).append(", ");
            saida.append(rd.nextInt(dado) + 1).append(", ");
            saida.append(rd.nextInt(dado) + 1);
        }
        tvResultado.setText(saida.toString());
    }

    private void preencherSpinnerTipos(int[] dados){
        List<String> listaDados = new ArrayList<>();

        for (int qtdFaces: dados) {
            listaDados.add("D"+qtdFaces);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaDados);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spTiposDados.setAdapter(adapter);
    }
}