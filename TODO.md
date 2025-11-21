# TODO List

This document tracks planned features and improvements for the Android Remote Camera application.

## High Priority 🔴

### Security Enhancements
- [ ] Implement EncryptedSharedPreferences for credential storage
- [ ] Add TLS/SSL for all network communications
- [ ] Implement certificate pinning for server connections
- [ ] Add OAuth2 or JWT-based authentication
- [ ] Implement two-factor authentication support
- [ ] Add secure token management

### Core Streaming Features
- [ ] Implement WebRTC for live video streaming
  - [ ] Set up PeerConnection
  - [ ] Implement signaling protocol
  - [ ] Add ICE candidate handling
  - [ ] Implement video codec selection
- [ ] Alternative: Implement RTSP streaming
  - [ ] RTSP client implementation
  - [ ] RTP packet handling
  - [ ] RTCP for quality monitoring
- [ ] Add adaptive bitrate streaming
- [ ] Implement audio streaming alongside video
- [ ] Add stream quality presets (low/medium/high/auto)

## Medium Priority 🟡

### Recording and Storage
- [ ] Implement local video recording
  - [ ] Use MediaRecorder for recording
  - [ ] Save to device storage
  - [ ] Add recording duration limits
- [ ] Add snapshot capture functionality
  - [ ] Save single frames as images
  - [ ] Implement burst mode
- [ ] Implement cloud storage integration
  - [ ] Upload recordings to SVision server
  - [ ] Add automatic backup
  - [ ] Implement storage quota management
- [ ] Add scheduled recording feature
- [ ] Implement circular buffer recording

### Motion Detection
- [ ] Implement basic motion detection algorithm
  - [ ] Frame differencing
  - [ ] Threshold configuration
  - [ ] Sensitivity adjustment
- [ ] Add motion event triggers
  - [ ] Start recording on motion
  - [ ] Send notifications
  - [ ] Upload to cloud
- [ ] Add motion zones configuration
- [ ] Implement ML-based motion detection (optional)

### Camera Controls
- [ ] Add digital zoom controls
  - [ ] Pinch-to-zoom gesture
  - [ ] Zoom slider in UI
- [ ] Implement exposure control
  - [ ] Manual exposure adjustment
  - [ ] Auto-exposure modes
- [ ] Add white balance control
- [ ] Implement focus modes
  - [ ] Auto-focus
  - [ ] Manual focus
  - [ ] Touch-to-focus
- [ ] Add flash/torch control
- [ ] Implement night mode optimization

### UI/UX Improvements
- [ ] Add splash screen
- [ ] Implement onboarding tutorial
- [ ] Add connection status indicators
  - [ ] Network signal strength
  - [ ] Server connection status
  - [ ] Stream quality indicator
- [ ] Implement landscape mode support
- [ ] Add picture-in-picture mode
- [ ] Create widget for quick camera access
- [ ] Add dark theme support
- [ ] Implement multi-language support (i18n)

## Low Priority 🟢

### Device Management
- [ ] Add battery level monitoring in UI
- [ ] Implement storage space monitoring
- [ ] Add temperature monitoring (prevent overheating)
- [ ] Implement auto-shutdown on low battery
- [ ] Add bandwidth usage statistics
- [ ] Implement data usage controls

### Advanced Features
- [ ] Multi-camera simultaneous streaming
- [ ] Add time-lapse recording
- [ ] Implement video filters
- [ ] Add watermark/timestamp overlay
- [ ] Implement grid overlay for composition
- [ ] Add privacy zones (blur specific areas)
- [ ] Implement geofencing triggers
- [ ] Add QR code scanning for quick setup

### Server Integration
- [ ] Implement full SVision server API
  - [ ] Device registration
  - [ ] Authentication endpoints
  - [ ] Streaming endpoints
  - [ ] Storage management
- [ ] Add server-side configuration push
- [ ] Implement remote device management
  - [ ] Remote settings update
  - [ ] Remote restart
  - [ ] Remote diagnostics
- [ ] Add webhook support for events
- [ ] Implement server-side motion detection

### Performance Optimization
- [ ] Optimize camera preview rendering
- [ ] Implement hardware acceleration for encoding
- [ ] Add frame skipping for poor connections
- [ ] Optimize battery usage
  - [ ] Reduce CPU usage
  - [ ] Implement power-saving modes
- [ ] Add memory leak detection and fixes
- [ ] Implement app size optimization

### Testing and Quality
- [ ] Add unit tests for all manager classes
- [ ] Implement UI tests with Espresso
- [ ] Add integration tests
- [ ] Set up continuous integration (CI)
- [ ] Add crash reporting (Firebase Crashlytics)
- [ ] Implement analytics
- [ ] Add performance monitoring

### Documentation
- [ ] Add API documentation (JavaDoc)
- [ ] Create user manual with screenshots
- [ ] Add troubleshooting guide
- [ ] Create video tutorials
- [ ] Add contributing guidelines
- [ ] Document server API specification
- [ ] Create architecture diagrams

## Future Enhancements 🔮

### Machine Learning Features
- [ ] Implement person detection
- [ ] Add facial recognition (with privacy controls)
- [ ] Implement object detection
- [ ] Add activity recognition
- [ ] Implement anomaly detection

### Integration Features
- [ ] Smart home integration (Google Home, Alexa)
- [ ] IFTTT integration
- [ ] Webhook support for custom integrations
- [ ] API for third-party apps
- [ ] Integration with other security systems

### Enterprise Features
- [ ] Multi-user support with role-based access
- [ ] Audit logging
- [ ] Compliance features (GDPR, etc.)
- [ ] Enterprise device management (MDM integration)
- [ ] Advanced analytics and reporting

## Completed ✅

- [x] Basic Android project structure
- [x] Camera preview with CameraX
- [x] Front/rear camera switching
- [x] Settings activity
- [x] Permission handling
- [x] Foreground service
- [x] Network manager
- [x] Streaming manager (stub)
- [x] Basic UI with controls
- [x] Documentation (README, DEVELOPMENT, SECURITY)
- [x] Security warnings and TODOs
- [x] Code review completed
- [x] CodeQL security scan passed

## Notes

- Priorities may change based on user feedback and requirements
- Security items should be addressed before production deployment
- Some features may require additional third-party libraries
- Consider performance impact of each feature on battery and network usage

---

Last Updated: 2025-11-21
