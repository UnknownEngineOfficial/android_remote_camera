# Development Guide

This document provides technical information for developers working on the Android Remote Camera application.

## Architecture Overview

The application follows a modular architecture with clear separation of concerns:

### Core Components

#### Activities
- **MainActivity**: Main camera interface with live preview and controls
- **SettingsActivity**: Configuration interface for server and device settings

#### Services
- **CameraService**: Foreground service for background camera operation and streaming

#### Managers
- **NetworkManager**: Handles network connectivity, server configuration, and preferences
- **StreamingManager**: Manages video streaming lifecycle (stub implementation)

### Technology Stack

- **Language**: Java 8
- **Minimum SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 14 (API 34)
- **Camera API**: CameraX
- **UI Framework**: Material Design Components
- **Storage**: SharedPreferences for configuration

## Project Structure

```
app/
├── src/main/
│   ├── java/com/svision/remotecamera/
│   │   ├── MainActivity.java           # Main camera UI and controller
│   │   ├── SettingsActivity.java       # Settings UI and persistence
│   │   ├── CameraService.java          # Background service
│   │   ├── NetworkManager.java         # Network and config management
│   │   └── StreamingManager.java       # Streaming logic (stub)
│   ├── res/
│   │   ├── layout/                     # UI layouts
│   │   ├── values/                     # Resources (strings, colors, themes)
│   │   ├── drawable/                   # Vector drawables
│   │   ├── mipmap-*/                   # App icons
│   │   └── xml/                        # Network security config
│   └── AndroidManifest.xml             # App manifest with permissions
└── build.gradle                        # App-level build configuration
```

## Key Features Implementation Status

### ✅ Implemented
1. **Camera Preview**: Live preview using CameraX with hardware acceleration
2. **Camera Switching**: Toggle between front and rear cameras
3. **Settings Management**: Persistent configuration storage
4. **Network Detection**: Real-time network status monitoring
5. **Permission Handling**: Runtime permissions for camera, audio, and storage
6. **Foreground Service**: Background operation support

### 🚧 Stub/Placeholder
1. **Video Streaming**: StreamingManager provides interface but needs WebRTC/RTSP implementation
2. **Server Communication**: Network calls need to be implemented
3. **Authentication**: UI exists but actual auth flow needs implementation
4. **Recording**: Capture functionality needs implementation
5. **Motion Detection**: Not yet implemented

## Building and Testing

### Prerequisites
```bash
# Install Android SDK
export ANDROID_HOME=/path/to/android-sdk

# Install Java 8 or higher
java -version
```

### Build Commands
```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug

# Run lint checks
./gradlew lint
```

### Debug Build
```bash
./gradlew installDebug
adb logcat | grep SVision
```

## Adding Features

### Adding a New Activity

1. Create activity class in `app/src/main/java/com/svision/remotecamera/`
2. Create layout in `app/src/main/res/layout/`
3. Add to `AndroidManifest.xml`:
```xml
<activity
    android:name=".YourActivity"
    android:exported="false" />
```

### Adding New Permissions

1. Add to `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.YOUR_PERMISSION" />
```

2. Request at runtime in activity:
```java
if (ContextCompat.checkSelfPermission(this, Manifest.permission.YOUR_PERMISSION)
        != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.YOUR_PERMISSION},
            REQUEST_CODE);
}
```

## Network and Streaming

### Implementing Real Streaming

The current `StreamingManager` is a stub. To implement real streaming:

#### Option 1: WebRTC
```java
// Add dependency in app/build.gradle
implementation 'org.webrtc:google-webrtc:1.0.+'

// Implement in StreamingManager
// 1. Create PeerConnectionFactory
// 2. Set up video source from camera
// 3. Create offer/answer for signaling
// 4. Stream to server
```

#### Option 2: RTSP
```java
// Use MediaRecorder with RTSP output
// Or implement custom RTSP client
```

#### Option 3: HTTP Live Streaming
```java
// Segment video and upload chunks
// Requires server-side HLS support
```

## Security Considerations

### Current Security Features
- Network security config allows cleartext traffic (for development)
- Permissions are requested at runtime
- Settings stored in private SharedPreferences

### TODO: Security Enhancements
1. Implement TLS/SSL for all network communication
2. Add certificate pinning for server connections
3. Encrypt stored credentials
4. Implement secure authentication (OAuth2/JWT)
5. Add two-factor authentication
6. Implement secure video encryption

## Performance Optimization

### Current Optimizations
- CameraX handles lifecycle automatically
- Foreground service for background operation
- Efficient UI updates

### Recommended Optimizations
1. Implement adaptive bitrate streaming
2. Add frame rate throttling based on network
3. Implement video quality presets (low/medium/high)
4. Add bandwidth monitoring
5. Implement frame skipping for poor connections

## Testing

### Manual Testing Checklist
- [ ] Camera preview displays correctly
- [ ] Front/rear camera switching works
- [ ] Settings save and load correctly
- [ ] Permissions are requested appropriately
- [ ] Network status updates correctly
- [ ] App handles background/foreground transitions
- [ ] Service notification appears when streaming

### Automated Testing (TODO)
```bash
# Unit tests
./gradlew test

# Instrumentation tests
./gradlew connectedAndroidTest
```

## Common Issues

### Camera Not Starting
- Check camera permissions
- Verify CameraX dependencies are correct
- Check device has available camera

### Network Issues
- Verify INTERNET permission in manifest
- Check network security config allows server domain
- Test network connectivity separately

### Build Failures
- Clean and rebuild: `./gradlew clean build`
- Update Gradle wrapper: `./gradlew wrapper --gradle-version 8.0`
- Verify ANDROID_HOME is set correctly

## Contributing

When contributing:
1. Follow existing code style
2. Add JavaDoc comments for public methods
3. Update this document for architectural changes
4. Test on multiple Android versions (minimum API 26)
5. Ensure all permissions are necessary and documented

## Resources

- [Android CameraX Documentation](https://developer.android.com/training/camerax)
- [Material Design Components](https://material.io/develop/android)
- [Android Foreground Services](https://developer.android.com/guide/components/foreground-services)
- [WebRTC for Android](https://webrtc.org/native-code/android/)

## License

Copyright © 2025 SVision Security-Line (S-Line)
