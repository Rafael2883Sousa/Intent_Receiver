package com.example.ficha04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receive_intent);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleReceivedText(intent);
            } else if (type.startsWith("image/")) {
                handleReceivedImage(intent);
            }
        }
    }

    private void handleReceivedText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            TextView receivedText = findViewById(R.id.receivedText);
            receivedText.setText(sharedText);
        }
    }

    private void handleReceivedImage(Intent intent) {
        Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            ImageView receivedImage = findViewById(R.id.receivedImage);
            receivedImage.setImageURI(imageUri);
        }
    }
}