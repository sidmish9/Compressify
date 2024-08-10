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

Alternatively, you can run the application from IntelliJ IDEA by navigating to the `CompressifyApplication.java` file, right-clicking on it, and selecting `Run 'CompressifyApplication.main()'`.

## Step 5: Using the API
To compress an audio file, you can add the file in the payload via Postman or any tool that supports audio file payloads (e.g., a browser).

### Example using Postman:
1. Open Postman and create a new POST request.
2. Set the URL to `http://localhost:8080/api/audio/compress`.
3. In the `Body` tab, select `form-data`.
4. Add a new key named `file` and set the type to `File`.
5. Choose the audio file you want to compress.
6. Send the request.

## Step 6: Locate the Compressed File
The log will return the location of the compressed file.
```
