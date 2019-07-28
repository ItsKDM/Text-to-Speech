package com.example.kdm.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSubmit;
    private EditText inputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        inputText = (EditText)findViewById(R.id.textInput);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceOutput();
            }
        });
    }

    private void voiceOutput(){

        CharSequence text = inputText.getText();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1");
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS){

            int result = tts.setLanguage(Locale.US);
            float i = 50;

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(MainActivity.this, "Language not Supported!", Toast.LENGTH_SHORT).show();
            }else{
                btnSubmit.setEnabled(true);
                voiceOutput();
            }
        }else{
            Toast.makeText(MainActivity.this, "Initialization failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
