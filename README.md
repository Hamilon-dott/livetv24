# LiveTV24 - Android TV App

**livetv24.vercel.app** ওয়েবসাইটের জন্য Android TV অ্যাপ।

## Features
- ✅ Android TV সম্পূর্ণ সাপোর্ট
- ✅ Remote control নেভিগেশন
- ✅ Full-screen WebView
- ✅ JavaScript ও Media সাপোর্ট
- ✅ Auto-play ভিডিও
- ✅ Leanback Launcher (TV home screen এ দেখাবে)

---

## 🚀 APK বানানোর সবচেয়ে সহজ উপায় (GitHub Actions)

### ধাপ ১: GitHub এ Upload করো
1. [github.com](https://github.com) এ নতুন repository তৈরি করো
2. এই সব ফাইল upload করো
3. কয়েক মিনিট অপেক্ষা করো

### ধাপ ২: APK Download করো
1. GitHub repo → **Actions** tab এ যাও
2. সবুজ ✅ build দেখলে click করো
3. **Artifacts** section থেকে `LiveTV24-debug` download করো
4. `.apk` ফাইল পেয়ে যাবে!

---

## 💻 Android Studio দিয়ে Build করো

### Prerequisites
- [Android Studio](https://developer.android.com/studio) install করো
- JDK 17 লাগবে

### Steps
```bash
# Terminal এ:
cd LiveTV24-AndroidTV
./gradlew assembleDebug

# APK পাবে:
# app/build/outputs/apk/debug/app-debug.apk
```

---

## 📱 TV তে Install করো

### Method 1: ADB দিয়ে
```bash
# TV ও PC একই WiFi তে থাকতে হবে
# TV তে Developer Options → Network Debugging চালু করো

adb connect <TV_IP_ADDRESS>:5555
adb install app-debug.apk
```

### Method 2: USB দিয়ে
```bash
adb install app-debug.apk
```

### Method 3: File Manager
1. APK ফাইলটি USB drive তে রাখো
2. TV তে file manager দিয়ে install করো
3. "Unknown sources" allow করতে হবে

---

## ⚙️ TV Settings

TV তে install করার আগে:
- **Settings → Device Preferences → Security → Unknown Sources** → চালু করো
- অথবা **Settings → Developer Options → Install via USB** → চালু করো

---

## 📋 Technical Info

| Property | Value |
|----------|-------|
| Package | `com.livetv24.app` |
| Min Android | 5.0 (API 21) |
| Target Android | 14 (API 34) |
| Orientation | Landscape |
| Website | https://livetv24.vercel.app/ |

---

## 🔧 Customization

`MainActivity.java` তে URL পরিবর্তন করতে পারো:
```java
private static final String WEBSITE_URL = "https://livetv24.vercel.app/";
```
