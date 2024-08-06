package Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.AudioCompressor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CompressifyService {

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("mp3", "wav", "flac", "aac", "ogg", "m4a");

    public File compressAudio(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !isSupportedExtension(originalFilename)) {
            throw new IOException("Unsupported file extension");
        }

        // Define compression parameters
        int bitrate = 128000; // 128 kbps
        int sampleRate = 44100; // 44.1 kHz
        int channels = 2; // Stereo

        // Compress audio
        return AudioCompressor.compressAudio(file, bitrate, sampleRate, channels);
    }

    private boolean isSupportedExtension(String filename) {
        String extension = getFileExtension(filename);
        return SUPPORTED_EXTENSIONS.contains(extension.toLowerCase());
    }

    private String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // empty extension
        }
        return filename.substring(lastIndexOfDot + 1);
    }
}