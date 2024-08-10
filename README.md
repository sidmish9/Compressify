# Compressify
A standalone, audio compression tool, built on JAVA and SPRING for audio files upto 10mb (supported extensions: "mp3", "wav", "flac", "aac", "ogg", "m4a")

Uses FFmpeg for bitrate setting and channel definition

```markdown
# Compressify Application Setup and Usage Guide

## Step 1: Clone the Repository
First, clone the repository to your local machine:
```sh
git clone <repository-url>
cd <repository-directory>
```

## Step 2: Download FFmpeg
Download FFmpeg from the [official website](https://ffmpeg.org/download.html) and add the binaries to `src/main/resources/ffmpeg/bin`.

## Step 3: Add Audio Files
Place your audio file(s) (under 10 MB) in the `src/main/resources/testfiles` directory.

## Step 4: Running the Application
Open a terminal in the root directory of the project and run the following command to start the Spring Boot application:
```sh
./mvnw spring-boot:run
```

## Step 5: Using the API
To compress an audio file, use the following `curl` command:
```sh
curl -X POST -F "file=@src/main/resources/testfiles/<your-audio-file>" http://localhost:8080/api/audio/compress -o compressed_audio_file
```

## Step 6: Locate the Compressed File
The compressed audio file will be saved in the current directory with the name `compressed_audio_file`.
```sh
ls -lh compressed_audio_file
```
```
