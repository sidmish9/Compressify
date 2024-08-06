package util;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class AudioCompressor {

    private static final Logger logger = Logger.getLogger(AudioCompressor.class.getName());

    public static File compressAudio(MultipartFile file, int bitrate, int sampleRate, int channels) throws IOException {
        // Create temporary file for input
        Path inputPath = Files.createTempFile("input-", file.getOriginalFilename());
        file.transferTo(inputPath.toFile());

        // Verify input file size
        if (Files.size(inputPath) == 0) {
            logger.severe("Input file is 0 KB after transfer: " + inputPath.toString());
            throw new IOException("Input file is empty");
        }

        // Create temporary file for output
        Path outputPath = Files.createTempFile("compressed-", file.getOriginalFilename());

        // Determine the operating system
        String os = System.getProperty("os.name").toLowerCase();
        String ffmpegPath;

        if (os.contains("win")) {
            // Windows path
            ffmpegPath = "src/main/resources/ffmpeg/bin/ffmpeg.exe";
        } else {
            // Assume Linux path
            ffmpegPath = "src/main/resources/ffmpeg/bin/ffmpeg";
        }

        // Initialize FFmpeg
        FFmpeg ffmpeg = new FFmpeg(ffmpegPath);
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);

        // Build FFmpeg command
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputPath.toString())
                .addOutput(outputPath.toString())
                .setAudioBitRate(bitrate)
                .setAudioSampleRate(sampleRate)
                .setAudioChannels(channels)
                .done();

        // Execute compression
        executor.createJob(builder).run();

        // Verify output file size
        if (Files.size(outputPath) == 0) {
            logger.severe("Output file is 0 KB after compression: " + outputPath.toString());
            throw new IOException("Output file is empty");
        }

        // Log output file path
        logger.info("Compressed file created at: " + outputPath.toString());

        // Return compressed file
        return outputPath.toFile();
    }
}