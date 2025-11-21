# Changelog

All notable changes to the Android Remote Camera project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Planned
- WebRTC video streaming implementation
- Cloud storage integration
- Motion detection
- Two-factor authentication
- Enhanced security features

## [0.1.0] - 2025-11-21

### Added
- Initial Android project structure with Gradle build system
- MainActivity with live camera preview using CameraX API
- Camera switching functionality (front/rear)
- SettingsActivity for configuration
  - Server address and port configuration
  - Username and password fields
  - Device name configuration
  - Audio enable/disable option
- CameraService for background operation
- NetworkManager for network connectivity and configuration management
- StreamingManager with interface for future streaming implementation
- Comprehensive documentation:
  - README.md with build and usage instructions
  - DEVELOPMENT.md with architecture guide
  - SECURITY.md with security considerations
  - TODO.md with planned features
  - FEATURES.md with complete feature specification
  - CHANGELOG.md for version tracking
- Android manifest with required permissions:
  - Camera access
  - Audio recording
  - Internet connectivity
  - Network state access
  - Wake lock
  - Foreground service
- Material Design UI components
  - Camera preview screen
  - Control buttons (stream, capture, switch camera)
  - Settings form with input validation
  - Status indicators
- Network security configuration (development mode)
- Adaptive launcher icons for all density buckets
- ProGuard rules for release builds
- .gitignore for Android project

### Security
- Added security warnings for plaintext password storage
- Documented cleartext traffic configuration (development only)
- Included TODOs for production security enhancements
- CodeQL security scan passed with no vulnerabilities

### Documentation
- Complete README with requirements and build instructions
- Developer guide with architecture overview
- Security documentation with known issues and mitigations
- TODO list with prioritized feature backlog
- Inline code comments for security concerns

### Technical Details
- **Minimum SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 14 (API 34)
- **Language**: Java 8
- **Architecture**: MVVM-inspired with Manager classes
- **Camera API**: CameraX 1.3.0
- **UI Framework**: Material Design Components 1.9.0
- **Build System**: Gradle 8.0

### Known Limitations
- Streaming is stub implementation only (no actual video transmission)
- Authentication UI exists but no backend integration
- No cloud storage implementation
- No motion detection
- No recording capability
- Security features are development-grade only

## [0.0.1] - 2025-11-21

### Added
- Initial repository setup
- FEATURES.md documenting planned functionality
- Basic README.md

---

## Version Guidelines

### Version Number Format: MAJOR.MINOR.PATCH

- **MAJOR**: Incompatible API changes or major architectural changes
- **MINOR**: New functionality in a backwards-compatible manner
- **PATCH**: Backwards-compatible bug fixes

### Changelog Categories

- **Added**: New features
- **Changed**: Changes in existing functionality
- **Deprecated**: Soon-to-be removed features
- **Removed**: Removed features
- **Fixed**: Bug fixes
- **Security**: Security fixes or improvements
