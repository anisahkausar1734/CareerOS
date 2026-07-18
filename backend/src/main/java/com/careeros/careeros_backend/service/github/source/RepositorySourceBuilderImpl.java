package com.careeros.careeros_backend.service.github.source;

import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.dto.github.source.RepositorySourceFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositorySourceBuilderImpl
        implements RepositorySourceBuilder {

    @Override
    public List<RepositorySourceFile> build(
            RepositorySnapshot snapshot
    ) {

        List<RepositorySourceFile> files =
                new ArrayList<>();

        if (snapshot.getImportantFiles() == null) {
            return files;
        }

        snapshot.getImportantFiles()

                .forEach((path, content) -> {

                    String fileName =
                            path.substring(
                                    path.lastIndexOf("/") + 1
                            );

                    String extension = "";

                    int index =
                            fileName.lastIndexOf('.');

                    if (index != -1) {

                        extension =
                                fileName.substring(
                                        index + 1
                                );

                    }

                    files.add(

                            RepositorySourceFile.builder()

                                    .path(path)

                                    .fileName(fileName)

                                    .extension(extension)

                                    .content(content)

                                    .build()

                    );

                             });

        System.out.println("\n========== SOURCE FILES ==========");

        files.forEach(file -> {

            System.out.println("--------------------------------");

            System.out.println("Path: " + file.getPath());

            System.out.println("File: " + file.getFileName());

            System.out.println("Extension: " + file.getExtension());

            System.out.println("Content Length: " +
                    (file.getContent() == null ? 0 : file.getContent().length()));

        });

        return files;

    }

}