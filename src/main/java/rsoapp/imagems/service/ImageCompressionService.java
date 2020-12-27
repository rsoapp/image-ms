package rsoapp.imagems.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageCompressionService {

    public byte[] compressBytes(byte[] imageBytes) {
        Deflater deflater = new Deflater();
        deflater.setInput(imageBytes);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
        byte[] buffer = new byte[1024];

        while(!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        } catch (Exception ignored) {}

//        System.out.println("Original Image Byte Size - " + imageBytes.length);
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }


    public byte[] decompressBytes(byte[] imageBytes) {
        Inflater inflater = new Inflater();
        inflater.setInput(imageBytes);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
        byte[] buffer = new byte[1024];


        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {}

        return outputStream.toByteArray();
    }
}
