package co.com.ias.deved.workshop.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileUtils {

    public static File readFileFromResources(String filename) throws URISyntaxException {
        Class<FileUtils> fileUtilsClass = FileUtils.class;
        ClassLoader classLoader = fileUtilsClass.getClassLoader();
        URL resource = classLoader.getResource(filename);
        return Paths.get(resource.toURI()).toFile();
    }

}