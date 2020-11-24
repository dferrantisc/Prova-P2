package com.example.prova_p2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prova_p2.Entidade.Livro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Livro> listaLivro = new ArrayList<Livro>();
    private ArrayAdapter<Livro> arrayadapterpessoal;
    EditText nome;
    EditText pagina;
    ListView lista_livro;

    FirebaseDatabase database;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.campoNome);
        pagina = (EditText) findViewById(R.id.campoPagina);
        lista_livro = (ListView) findViewById(R.id.list_dados);

        FirebaseApp.initializeApp(MainActivity.this);
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();

        dbReference.child("Livro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaLivro.clear();
                for (DataSnapshot objSnapShot:snapshot.getChildren()){
                    Livro p = objSnapShot.getValue(Livro.class);
                    listaLivro.add(p);
                }
                arrayadapterpessoal = new ArrayAdapter<Livro>(MainActivity.this,
                        android.R.layout.simple_list_item_1, listaLivro);
                lista_livro.setAdapter(arrayadapterpessoal);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void cadastrar(View view) {
        Livro livro = new Livro();
        livro.setUuid(UUID.randomUUID().toString());
        livro.setNome(nome.getText().toString());
        livro.setPaginas(Integer.parseInt( pagina.getText().toString() ));

        dbReference.child("Livro").child(livro.getUuid()).setValue(livro);

        Toast.makeText(MainActivity.this, "Livro cadastrado", Toast.LENGTH_LONG).show();
    }

}