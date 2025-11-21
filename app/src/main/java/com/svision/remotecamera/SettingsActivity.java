package com.svision.remotecamera;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "RemoteCameraPrefs";
    
    private TextInputEditText serverAddressInput;
    private TextInputEditText serverPortInput;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private TextInputEditText deviceNameInput;
    private SwitchMaterial audioSwitch;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initializeViews();
        loadSettings();
        setupListeners();
    }

    private void initializeViews() {
        serverAddressInput = findViewById(R.id.serverAddressInput);
        serverPortInput = findViewById(R.id.serverPortInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        deviceNameInput = findViewById(R.id.deviceNameInput);
        audioSwitch = findViewById(R.id.audioSwitch);
        saveButton = findViewById(R.id.saveButton);
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        
        serverAddressInput.setText(prefs.getString("server_address", "svision.example.com"));
        serverPortInput.setText(String.valueOf(prefs.getInt("server_port", 8080)));
        usernameInput.setText(prefs.getString("username", ""));
        passwordInput.setText(prefs.getString("password", ""));
        deviceNameInput.setText(prefs.getString("device_name", android.os.Build.MODEL));
        audioSwitch.setChecked(prefs.getBoolean("audio_enabled", true));
    }

    private void setupListeners() {
        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void saveSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String serverAddress = serverAddressInput.getText() != null ? 
                serverAddressInput.getText().toString() : "";
        String serverPortStr = serverPortInput.getText() != null ? 
                serverPortInput.getText().toString() : "8080";
        String username = usernameInput.getText() != null ? 
                usernameInput.getText().toString() : "";
        String password = passwordInput.getText() != null ? 
                passwordInput.getText().toString() : "";
        String deviceName = deviceNameInput.getText() != null ? 
                deviceNameInput.getText().toString() : android.os.Build.MODEL;

        int serverPort;
        try {
            serverPort = Integer.parseInt(serverPortStr);
        } catch (NumberFormatException e) {
            serverPort = 8080;
        }

        editor.putString("server_address", serverAddress);
        editor.putInt("server_port", serverPort);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("device_name", deviceName);
        editor.putBoolean("audio_enabled", audioSwitch.isChecked());
        editor.apply();

        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
