# Android Remote Camera

SVision Security-Line (S-Line) Remote Camera application that transforms an Android smartphone into a fully-featured surveillance camera.

## Overview

Android Remote Camera is a security application part of the SVision S-Line system that enables remote access to Android device cameras for surveillance and monitoring purposes. See [FEATURES.md](FEATURES.md) for a complete list of planned and implemented features.

## Requirements

- **Android SDK**: API Level 26 (Android 8.0 Oreo) or higher
- **Target SDK**: API Level 34 (Android 14)
- **Gradle**: 8.0 or higher
- **Java**: JDK 8 or higher

### Device Requirements
- Android device running Android 8.0 (Oreo) or higher
- At least 2 GB RAM
- Functional camera with at least 2 MP
- Wi-Fi or mobile data connection

## Building the Application

### Prerequisites

1. Install Android SDK and Android Studio (recommended)
2. Set the `ANDROID_HOME` environment variable to your Android SDK location

### Build Instructions

1. Clone the repository:
```bash
git clone https://github.com/UnknownEngineOfficial/android_remote_camera.git
cd android_remote_camera
```

2. Build the project using Gradle:
```bash
./gradlew build
```

3. Build and install debug APK:
```bash
./gradlew installDebug
```

4. Build release APK:
```bash
./gradlew assembleRelease
```

The APK will be generated in `app/build/outputs/apk/`

## Features Implemented

### Core Features ✅
- **Camera Preview**: Real-time camera preview using CameraX
- **Camera Switching**: Toggle between front and rear cameras
- **Settings Management**: Configure server connection and device settings
- **Permissions Handling**: Runtime permission requests for camera and audio

### Basic UI ✅
- Main camera interface with preview
- Control buttons for streaming, camera switch, and photo capture
- Settings activity for configuration
- Status indicators

### Planned Features 🚧
See [FEATURES.md](FEATURES.md) for the complete list of planned features including:
- Live video streaming to SVision server
- Motion detection and automated recording
- Cloud storage integration
- Multi-camera management
- Advanced security features

## Usage

1. **Launch the app**: The app will request camera and audio permissions on first launch
2. **Camera Preview**: Once permissions are granted, the live camera preview will be displayed
3. **Switch Camera**: Tap the camera switch button to toggle between front and rear cameras
4. **Settings**: Tap the settings icon to configure:
   - Server address and port
   - Authentication credentials
   - Device name
   - Audio settings
5. **Start Streaming**: Tap the play button to start streaming (basic UI state toggle)

## Permissions

The app requires the following permissions:
- **CAMERA**: For accessing device cameras
- **RECORD_AUDIO**: For recording audio with video
- **INTERNET**: For network communication
- **ACCESS_NETWORK_STATE**: For checking network connectivity
- **WAKE_LOCK**: To keep the device awake during streaming
- **FOREGROUND_SERVICE**: For background camera operation

## Project Structure

```
android_remote_camera/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/svision/remotecamera/
│   │       │   ├── MainActivity.java          # Main camera interface
│   │       │   ├── SettingsActivity.java      # Settings configuration
│   │       │   └── CameraService.java         # Foreground camera service
│   │       ├── res/
│   │       │   ├── layout/                    # UI layouts
│   │       │   ├── values/                    # Strings, colors, themes
│   │       │   └── xml/                       # Network security config
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── FEATURES.md                                # Detailed feature documentation
└── README.md
```

## Architecture

The application follows Android best practices:
- **CameraX**: Modern camera API for preview and capture
- **Material Design**: UI components from Material Design library
- **SharedPreferences**: Local storage for settings
- **Foreground Service**: For background camera operation

## Contributing

This is part of the SVision Security-Line (S-Line) system. For contributions or feature requests, please refer to the project repository.

## License

Copyright © 2025 SVision Security-Line (S-Line)

## Support

For issues and questions, please use the GitHub issue tracker.