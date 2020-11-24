package com.example.prova_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    EditText pagina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.campoNome);
        pagina = (EditText) findViewById(R.id.campoPagina);

    }

}