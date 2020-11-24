package com.example.prova_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova_p2.Entidade.Livro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText pagina;


    FirebaseDatabase database;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.campoNome);
        pagina = (EditText) findViewById(R.id.campoPagina);

        FirebaseApp.initializeApp(MainActivity. this);
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();
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