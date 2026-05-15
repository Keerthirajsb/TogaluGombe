# 🎭 Togalu Gombe — Digital Shadow-Theater Companion

**Project Title 39** — Android App Development using GenAI  
**MindMatrix VTU Internship Program**

## About

Togalu-Gombe is a "Digital Shadow-Theater" companion app that preserves and promotes Karnataka's ancient art of **Leather Puppetry (Togalu Gombeyaata)**. It bridges the generation gap by providing scene-by-scene story guides in both Kannada and English.

## Features

### 🎪 Live Assist
- Select a play (Ramayana, Mahabharata, Krishna Leela)
- **Swipe through scenes** with horizontal pager
- Bilingual scene summaries (Kannada/English)
- Character highlights per scene

### 🖼️ Puppet Gallery
- Interactive grid of puppet characters
- **Pinch-to-zoom** on puppet images (shows intricate perforations)
- Filter by character type (Hero, Villain, Deity, Support)
- Search puppets by name
- Detailed view with powers, symbolism, and description

### 🤝 Artist Connect
- Master puppeteer profiles
- Browse and book puppet-making workshops
- Workshop booking form with confirmation
- Seat availability tracking

### 📜 History Feed
- Video feed about leather crafting process
- Categories: Leather Curing, Dyeing, Cutting, Assembly
- Duration badges and category filters

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM |
| Database | Room (SQLite) |
| Navigation | Compose Navigation |
| Media | ExoPlayer (Media3) |
| Image Loading | Coil |
| Theme | Dark (Shadow-Theater Aesthetic) |

## Setup

1. Open the `TogaluGombe` folder in **Android Studio**
2. Wait for Gradle sync to complete
3. Connect an Android device or start an emulator (API 26+)
4. Click **Run ▶**

## Project Structure

```
app/src/main/java/com/togalugombe/app/
├── MainActivity.kt
├── TogaluGombeApplication.kt
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt
│   │   ├── DatabaseSeeder.kt
│   │   ├── dao/ (PlayDao, PuppetDao, ArtistDao, HistoryVideoDao)
│   │   └── entity/ (6 entities)
│   └── repository/ (4 repositories)
├── ui/
│   ├── theme/ (Color, Theme, Type)
│   ├── navigation/ (Screen, NavGraph)
│   ├── components/ (BottomNavBar)
│   └── screens/
│       ├── home/ (HomeScreen, HomeViewModel)
│       ├── liveassist/ (LiveAssistScreen, SceneDetailScreen, ViewModel)
│       ├── gallery/ (PuppetGalleryScreen, PuppetDetailScreen, ViewModel)
│       ├── artistconnect/ (ArtistConnectScreen, WorkshopBookingScreen, ViewModel)
│       └── historyfeed/ (HistoryFeedScreen, ViewModel)
└── util/ (Constants)
```

## UI Theme

Dark shadow-theater aesthetic with:
- **Background**: Deep black (#0D0D0D) — mimicking the leather stage
- **Accent**: Warm amber (#F5A623) — oil lamp glow
- **Secondary**: Deep saffron (#E8712B)
- **Text**: Warm white (#F5EDE0) on aged leather tones

## Language Support

- 🇬🇧 English (default)
- 🇮🇳 Kannada (ಕನ್ನಡ) — toggle available on all screens
