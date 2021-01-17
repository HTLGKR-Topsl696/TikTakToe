package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textFreeFields;
    private TextView textPlayer;

    private List<List<Button>> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFreeFields = findViewById(R.id.textFreeFields);
        textPlayer = findViewById(R.id.textPlayer);

        fields = new ArrayList<>();
        fields.add(new ArrayList<>());
        fields.add(new ArrayList<>());
        fields.add(new ArrayList<>());

        fields.get(0).add(findViewById(R.id.field11));
        fields.get(0).add(findViewById(R.id.field12));
        fields.get(0).add(findViewById(R.id.field13));

        fields.get(1).add(findViewById(R.id.field21));
        fields.get(1).add(findViewById(R.id.field22));
        fields.get(1).add(findViewById(R.id.field23));

        fields.get(2).add(findViewById(R.id.field31));
        fields.get(2).add(findViewById(R.id.field32));
        fields.get(2).add(findViewById(R.id.field33));
    }

    public void handleClick(View view) {
        Button clicked = (Button) view;
        if (!clicked.getText().toString().equals("")) {
            Toast.makeText(this, "Dieses Feld wurde bereits beleget!", Toast.LENGTH_LONG).show();
            return;
        }

        String player = textPlayer.getText().toString().split(": ")[1];
        clicked.setText(player);

        textPlayer.setText("Player: " + (player.equals("X") ? "O" : "X"));

        if (!fields.get(0).get(0).getText().toString().equals("") && fields.get(0).get(0).getText().toString().equals(fields.get(1).get(1).getText().toString()) && fields.get(1).get(1).getText().toString().equals(fields.get(2).get(2).getText().toString())) {
            Toast.makeText(this, "Spieler " + fields.get(0).get(0).getText().toString() + " gewinnt!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!fields.get(0).get(2).getText().toString().equals("") && fields.get(0).get(2).getText().toString().equals(fields.get(1).get(1).getText().toString()) && fields.get(1).get(1).getText().toString().equals(fields.get(2).get(0).getText().toString())) {
            Toast.makeText(this, "Spieler " + fields.get(0).get(2).getText().toString() + " gewinnt!", Toast.LENGTH_LONG).show();
            return;
        }

        int emptyFields = 0;
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 3; ++k) {
                if (fields.get(i).get(k).getText().toString().equals("")) {
                    ++emptyFields;
                }
            }
            if (!fields.get(0).get(i).getText().toString().equals("") && fields.get(0).get(i).getText().toString().equals(fields.get(1).get(i).getText().toString()) && fields.get(1).get(i).getText().toString().equals(fields.get(2).get(i).getText().toString())) {
                Toast.makeText(this, "Spieler " + fields.get(0).get(i).getText().toString() + " gewinnt!", Toast.LENGTH_LONG).show();
                return;
            }
            if (!fields.get(i).get(0).getText().toString().equals("") && fields.get(i).get(0).getText().toString().equals(fields.get(i).get(1).getText().toString()) && fields.get(i).get(1).getText().toString().equals(fields.get(i).get(2).getText().toString())) {
                Toast.makeText(this, "Spieler " + fields.get(0).get(i).getText().toString() + " gewinnt!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (emptyFields == 0) {
            Toast.makeText(this, "Das Spiel ist aus - kein Sieger!", Toast.LENGTH_LONG).show();
        }

        textFreeFields.setText(String.valueOf(emptyFields));
    }
}