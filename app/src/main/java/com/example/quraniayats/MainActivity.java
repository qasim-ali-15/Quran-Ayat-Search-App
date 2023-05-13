package com.example.quraniayats;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView paraNUm;
    public TextView ayatNum;
    public Button btnSearch,btnRepos;
    public TextView parahName;
    public TextView EnglishParahName;
    public TextView ayats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRepos = findViewById(R.id.btnRepos);
        paraNUm = findViewById(R.id.ParahNum);
        ayatNum = findViewById(R.id.AyatNum);
        btnSearch = findViewById(R.id.btnSearch);
        parahName = findViewById(R.id.ParahDisplayView);
        ayats = findViewById(R.id.AyatDisplayView);
        EnglishParahName = findViewById(R.id.englishParahName);

        btnRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://github.com/HassanJaami/");
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paraNUm.getText().equals("") && ayatNum.getText().equals(""))
                {
                    paraNUm.setText("!!");
                }
                else if(paraNUm.getText() != "" && ayatNum.getText() == "")
                {
                    paraNUm.setText("!!!!!");
                }
                else
                {
                    int valP = Integer.parseInt(paraNUm.getText().toString());
                    int valA = Integer.parseInt(ayatNum.getText().toString())-1;

                    if (valP < 1 || valP > 30 ){
                        Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        QuranArabicText qat = new QuranArabicText();
                        int startP = new QDH().getParahStart(valP-1)-1 + valA;
                        if(valP == 30)
                        {
                            int limitEnd = 6348;

                            String AS = "";
                            for(int i = startP; i <= startP; i++)
                            {
                                AS += qat.QuranArabicText[i];
                            }

                            parahName.setText(new QDH().ParahName[valP-1]);
                            EnglishParahName.setText(new QDH().englishParahName[valP-1]);
                            ayats.setText(AS);
                        }
                        else {
                            int countOfAyatsInParah = new QDH().getParahStart(valP)-new QDH().getParahStart(valP-1);

                            String AS = "";
                            for(int i = startP; i <= startP; i++)
                            {
                                AS += qat.QuranArabicText[i] + " ";
                            }

                            parahName.setText(new QDH().ParahName[valP-1]);
                            EnglishParahName.setText(new QDH().englishParahName[valP-1]);
                            ayats.setText(AS);
                        }
                    }
                }
            }
        });
    }

    private void goLink(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}