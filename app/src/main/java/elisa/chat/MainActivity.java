package elisa.chat;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {

        private EditText nome;
        private EditText cod;
        private Button button;

        Gerenciador dbm;
        Cursor dados;
        ListarDados ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define o Banco
        dbm = new Gerenciador(getApplicationContext());

        //Define elementos
        cod = findViewById(R.id.codigo);
        nome = findViewById(R.id.name);
        button = findViewById(R.id.bt);

        //Evento Click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = cod.getText().toString();
                String n = nome.getText().toString();
                dbm.createRecords(c, n);

                cod.setText("Código");
                nome.setText("Nome");

                atualizarLista();
            }
        });
        //Listando Dados
        atualizarLista();

    }

    //-> Fora/Abaixo do método on create
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Você clicou em: " + ld.getItem(position) + " N: " + position, Toast.LENGTH_SHORT).show();
    }

    public void atualizarLista(){
        // Criando Lista de nomes
        ArrayList<String> lNomes = new ArrayList<>();
        dados = dbm.selectRecords();

        while(!dados.isLast()) {
            String registro = "Cod: "+dados.getString(0);
            registro+= " - Nome: "+dados.getString(1);
            lNomes.add(registro);
            dados.moveToNext();
        }

        // Atualizando RecyclerView
        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ld = new ListarDados(this, lNomes);
        ld.setClickListener(this::onItemClick);
        recyclerView.setAdapter(ld);

    }

}