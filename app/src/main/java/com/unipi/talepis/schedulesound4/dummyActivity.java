package com.unipi.talepis.schedulesound4;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class dummyActivity extends AppCompatActivity {
    public static final  int VOICE_REC_RESULT=22342;
    public static String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        startvoicerecognitionactivity();
    }

    private void startvoicerecognitionactivity(){
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"I am listening...");
        startActivityForResult(intent,VOICE_REC_RESULT);
    }

    public void onActivityResult(int Requestcode,int Resultcode,Intent data){
        if (Requestcode==VOICE_REC_RESULT && Resultcode==RESULT_OK){
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String str="";
            for (String s : matches)
                str+=s+"\t";
            result = str;
        }
    }
}
