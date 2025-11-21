package com.svision.remotecamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSIONS = 10;
    private static final String[] REQUIRED_PERMISSIONS = new String[] {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    private PreviewView previewView;
    private TextView statusText;
    private FloatingActionButton streamButton;
    private ImageButton switchCameraButton;
    private ImageButton captureButton;
    private ImageButton settingsButton;

    private CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
    private boolean isStreaming = false;
    private ProcessCameraProvider cameraProvider;
    private StreamingManager streamingManager;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeManagers();
        setupListeners();

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private void initializeViews() {
        previewView = findViewById(R.id.previewView);
        statusText = findViewById(R.id.statusText);
        streamButton = findViewById(R.id.streamButton);
        switchCameraButton = findViewById(R.id.switchCameraButton);
        captureButton = findViewById(R.id.captureButton);
        settingsButton = findViewById(R.id.settingsButton);
    }

    private void initializeManagers() {
        streamingManager = new StreamingManager(this);
        networkManager = new NetworkManager(this);
        updateStatus();
    }

    private void setupListeners() {
        streamButton.setOnClickListener(v -> toggleStreaming());
        switchCameraButton.setOnClickListener(v -> switchCamera());
        captureButton.setOnClickListener(v -> capturePhoto());
        settingsButton.setOnClickListener(v -> openSettings());
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = 
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera: " + e.getMessage(), 
                        Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview);
    }

    private void toggleStreaming() {
        isStreaming = !isStreaming;
        if (isStreaming) {
            startStreaming();
        } else {
            stopStreaming();
        }
    }

    private void startStreaming() {
        if (streamingManager.startStreaming()) {
            statusText.setText(R.string.status_streaming);
            statusText.setTextColor(ContextCompat.getColor(this, R.color.status_streaming));
            streamButton.setImageResource(android.R.drawable.ic_media_pause);
            Toast.makeText(this, "Streaming started to " + networkManager.getServerAddress(), 
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to start streaming. Check network connection.", 
                    Toast.LENGTH_SHORT).show();
            isStreaming = false;
        }
    }

    private void stopStreaming() {
        streamingManager.stopStreaming();
        statusText.setText(R.string.status_connected);
        statusText.setTextColor(ContextCompat.getColor(this, R.color.status_connected));
        streamButton.setImageResource(android.R.drawable.ic_media_play);
        Toast.makeText(this, "Streaming stopped", Toast.LENGTH_SHORT).show();
    }

    private void updateStatus() {
        if (networkManager.isNetworkAvailable()) {
            statusText.setText(String.format("Ready - %s", networkManager.getNetworkType()));
            statusText.setTextColor(ContextCompat.getColor(this, R.color.status_connected));
        } else {
            statusText.setText(R.string.status_disconnected);
            statusText.setTextColor(ContextCompat.getColor(this, R.color.status_disconnected));
        }
    }

    private void switchCamera() {
        if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
            Toast.makeText(this, "Switched to front camera", Toast.LENGTH_SHORT).show();
        } else {
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
            Toast.makeText(this, "Switched to back camera", Toast.LENGTH_SHORT).show();
        }
        
        if (cameraProvider != null) {
            bindPreview(cameraProvider);
        }
    }

    private void capturePhoto() {
        streamingManager.captureSnapshot();
        Toast.makeText(this, "Photo captured", Toast.LENGTH_SHORT).show();
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) 
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
        }
    }
}
