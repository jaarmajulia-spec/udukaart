# Loeng 1 — näide: Udukaardi algus

> **Enne alustamist: paki projekt lahti LÜHIKESSE teesse**, näiteks
> `C:\Udukaart` või `C:\Users\<sina>\AndroidStudioProjects\Udukaart`.
>
> Ehitus tekitab kuni **175 märgi** pikkuseid failiteid (Compose'i
> genereeritud nimed on pikad) ja Windowsi vaikepiir on **260**. Töölaual
> või `AndroidStudioProjects`-is on varu piisav; sügav OneDrive'i kaust
> koos pika projektinimega läheb piirist üle — ja veateade räägib siis
> failist, mitte teepikkusest, nii et põhjust on raske ära tunda.

See on töötav Android-projekt, mis sisaldab kõike, mida loengu 1 slaididel
näidati: manifest, Gradle'i ehitusfailid, versioonikataloog, ressursid
kvalifikaatoritega, esimene Compose'i ekraan ja elutsükli logimine.

**See projekt on ka läbiva projekti algus.** Udukaart kasvab sellest samast
kaustast kuni loenguni 16 — sa ei alusta kordagi otsast.

## Mida sul on vaja

| Tööriist       | Versioon                     | Kust                            |
| -------------- | ---------------------------- | ------------------------------- |
| Android Studio | uusim stabiilne              | developer.android.com/studio    |
| JDK            | see, mis tuleb Studioga      | tuleb Android Studioga kaasa    |
| Android SDK    | API 36                       | Android Studio → SDK Manager    |
| Seade          | Android 10 (API 29) või uuem | füüsiline telefon või emulaator |

> **Kasuta Android Studio kaasasolevat JDK-d.** Ta on juba vaikimisi valitud
> ja projekt ehitub temaga. Eraldi JDK-d paigaldada ega `JAVA_HOME`-i seada ei
> ole vaja.

> **Miks siin ei ole Studio koodnime.** Projekt käsib Android Gradle pluginat
> **8.13.2** (`gradle/libs.versions.toml`) ja `compileSdk = 36`
> (`app/build.gradle.kts`). Neid toetab ainult piisavalt uus Studio — vanem
> kurdab sünkroniseerimisel pluginaversiooni üle ja ei ava projekti. Koodnimed
> vahetuvad mitu korda aastas ja vananevad kiiremini kui aine, seega on siin
> nimetatud nõue, mitte nimi: võta uusim stabiilne.

## Kuidas käivitada

1. **File → Open** ja vali **see kaust** (mitte `app` kaust, vaid see, kus
   on `settings.gradle.kts`).
2. Oota, kuni Gradle sünkroniseerib. Esimene kord võtab mõne minuti, sest
   teegid laetakse alla.
3. Vali ülariba rippmenüüst seade või emulaator.
4. Vajuta **Run** (roheline kolmnurk) või `Shift+F10`.
5. Ava all **Logcat** ja kirjuta filtriks:

```
package:mine tag:Udukaart
```

Sa peaksid nägema kaht rida: `onCreate` ja `onStart`.

## Mida siit vaadata

Ava failid selles järjekorras — nad ehitavad üksteise peale.

| Fail                                           | Mida siit otsida                                                 |
| ---------------------------------------------- | ---------------------------------------------------------------- |
| `settings.gradle.kts`                          | mis moodulid projektis on ja kust teegid tulevad                 |
| `gradle/libs.versions.toml`                    | **versioonikataloog**: iga versioon on kirjas TÄPSELT ühes kohas |
| `app/build.gradle.kts`                         | `namespace`, kolm SDK-numbrit, sõltuvused alias-viidetena        |
| `app/src/main/AndroidManifest.xml`             | komponendid, õigus, `exported`, käivitusfilter                   |
| `MainActivity.kt`                              | `setContent`, `@Composable`, `@Preview`, elutsükli logid         |
| `res/values/strings.xml` ja `res/values-et/`   | sama string kahes keeles                                         |
| `res/values/themes.xml` ja `res/values-night/` | öörežiim ilma ühegi `if`-lauseta                                 |
| `gradle.properties`                            | `android.useAndroidX` ja Gradle'i mälu                           |

## Kolm asja, mida ise proovida

Loe kood läbi ja **muuda teda** — see kinnistab rohkem kui lugemine.

### 1. Keelekvalifikaator

Muuda seadme keel eesti keelest inglise keelde (**Settings → System →
Languages**) ja käivita rakendus uuesti. Tekst tuleb nüüd
`res/values/strings.xml` failist, mitte `values-et/` omast.

**Ühtki `if`-lauset selleks kirjutada ei ole vaja.** Kvalifikaator on
kausta nimes ja valiku teeb süsteem.

### 2. Öörežiim

Lülita seadmes tume teema sisse. **Akna raam** tuleb nüüd
`values-night/themes.xml` failist — sama mehhanism, teine kvalifikaator.

Aga pane tähele: **rakenduse enda värvid ei muutu.** Compose ei loe XML-
teemat; tema värvid tulevad skeemist, mille sa `MaterialTheme`-le annad, ja
praegu on see vaikimisi hele. Vaata `HomeScreenDarkPreview` funktsiooni —
seal antakse `darkColorScheme()` ette ja eelvaade ONGI tume. Terve rakenduse
teeme süsteemi järgi tumedaks loengus 5.

### 3. Elutsükkel Logcatis

Vajuta koduekraani nuppu ja tule tagasi. Vaata, millised read lisanduvad
ja millised **ei** lisandu. See on kontrolltöö esimene ülesanne — proovi
juba praegu.

## Kui midagi ei tööta

| Sümptom                            | Põhjus                           | Mida teha                                                       |
| ---------------------------------- | -------------------------------- | --------------------------------------------------------------- |
| „What went wrong: 25"              | JAVA_HOME osutab JDK 25-le       | **File → Settings → Build → Gradle**, Gradle JDK = 21           |
| Studio kurdab AGP versiooni üle    | Studio on vanem kui AGP 8.13.2   | uuenda Studiot: **Help → Check for Updates**                    |
| Sünkroniseerimine ripub            | esimene allalaadimine            | oota; vajadusel **File → Sync Project with Gradle Files**       |
| „SDK location not found"           | `local.properties` puudub        | Android Studio loob ta ise; sulge ja ava projekt uuesti         |
| Seadet ei ole nimekirjas           | USB-silumine välja lülitatud     | seadmes **Settings → Developer options → USB debugging**        |
| Emulaator ei käivitu               | virtualiseerimine BIOS-is väljas | kasuta füüsilist seadet või lülita BIOS-is VT-x/SVM sisse       |
| Logcat on tühi                     | vale filter                      | kontrolli, et filtris on `package:mine`, ja et rakendus jookseb |
| Rakendus paigaldub, aga kukub kohe | manifestis vale klassinimi       | vaata Logcatist punast `Caused by` rida                         |

## Mida see projekt EI tee

Siin ei ole veel andmebaasi, asukohta ega kaarti — need tulevad
loengutes 8, 10 ja 11 (samas järjekorras). Loeng 1 näitab **projekti kuju**: kust ehitus
algab, kust ressursid tulevad ja kuidas süsteem sinu rakendust käivitab.

`local.properties` ja `build/` kaustad ei kuulu versioonihoidlasse — vt
`.gitignore`. Miks täpselt, seda küsib kontrolltöö kolmas ülesanne.

## Kui tahad ehitada käsurealt

Projektis on Gradle'i mähis (`gradlew`), seega Gradle'it eraldi paigaldada ei
ole vaja. **Aga Windowsis on üks lõks:** mähis ei suuda oma järge avada, kui
kausta teel on täpitähed. Selle aine kaustanimed on eestikeelsed, seega
`gradlew.bat` annab siit veateate kujul `N?ited` või `V?rk`.

**Android Studios seda probleemi ei ole** — ava projekt ja vajuta Run. Nii on
loeng ka mõeldud, ja nii teeb ka kontrolltöö.

Kui sul on käsurida siiski vaja, kopeeri projekt täpitähtedeta teele:

```
robocopy "." "C:\Temp\udukaart" /E /XD build .gradle .kotlin
cd C:\Temp\udukaart
gradlew.bat assembleDebug
```
