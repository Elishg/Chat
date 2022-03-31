package elisa.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    private TextView text;

    Gerenciador dbm;
    Cursor dados;
    ListarDados ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        dbm = new Gerenciador(getApplicationContext());

        atualizarLista();
    }

    public void onItemClick(View view, int position) {
        text = findViewById(R.id.tv);
        text.setText(ld.getItem(position));
        //Toast.makeText(this, "Você clicou em: " + ld.getItem(position) + " N: " + position, Toast.LENGTH_SHORT).show();
    }


    public void atualizarLista () {
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
        RecyclerView recyclerView = findViewById(R.id.listar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ld = new ListarDados(this, lNomes);
        ld.setClickListener(this::onItemClick);
        recyclerView.setAdapter(ld);

    }

}

