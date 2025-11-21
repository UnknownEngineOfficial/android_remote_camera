package com.svision.remotecamera;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/**
 * Manages network connectivity and server connections
 */
public class NetworkManager {
    private static final String PREFS_NAME = "RemoteCameraPrefs";
    private final Context context;
    private final SharedPreferences prefs;

    public NetworkManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Check if device has active network connection
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = 
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = 
                        connectivityManager.getNetworkCapabilities(network);
                return capabilities != null && (
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
            }
        }
        return false;
    }

    /**
     * Get configured server address
     */
    public String getServerAddress() {
        return prefs.getString("server_address", "svision.example.com");
    }

    /**
     * Get configured server port
     */
    public int getServerPort() {
        return prefs.getInt("server_port", 8080);
    }

    /**
     * Get device name
     */
    public String getDeviceName() {
        return prefs.getString("device_name", android.os.Build.MODEL);
    }

    /**
     * Get username for authentication
     */
    public String getUsername() {
        return prefs.getString("username", "");
    }

    /**
     * Check if audio is enabled
     */
    public boolean isAudioEnabled() {
        return prefs.getBoolean("audio_enabled", true);
    }

    /**
     * Get network type as string
     */
    public String getNetworkType() {
        ConnectivityManager connectivityManager = 
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = 
                        connectivityManager.getNetworkCapabilities(network);
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return "WiFi";
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return "Mobile";
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return "Ethernet";
                    }
                }
            }
        }
        return "None";
    }
}
