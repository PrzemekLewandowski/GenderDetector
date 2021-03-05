package com.myapp.detector.service;

import com.myapp.detector.service.exception.FileServiceException;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Getter
public class FileService {

    @Value("${file.names.male}")
    private Resource maleResource;

    @Value("${file.names.female}")
    private Resource femaleResource;

    public long countMaleNames(List<String> names) {
        return countNames(maleResource, names);
    }

    public long countFemaleNames(List<String> names) {
        return countNames(femaleResource, names);
    }

    public byte[] createZipByteArrayFromResources() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            List<Resource> resources = List.of(maleResource, femaleResource);
            for (Resource resource : resources) {
                byte[] bytes = IOUtils.toByteArray(resource.getInputStream());
                ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(resource.getFilename()));
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(bytes);
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            throw new FileServiceException("Can't convert files to ByteArrayOutputStream.", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private long countNames(Resource resource, List<String> names) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return reader.lines().parallel().filter(names::contains).count();
        } catch (IOException e) {
            throw new FileServiceException("Couldn't read file", e);
        }
    }
}
