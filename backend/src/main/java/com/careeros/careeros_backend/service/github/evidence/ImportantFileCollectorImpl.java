package com.careeros.careeros_backend.service.github.evidence;

import com.careeros.careeros_backend.config.EngineeringEvidenceProperties;
import com.careeros.careeros_backend.service.github.GithubFileDownloaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImportantFileCollectorImpl
        implements ImportantFileCollector {

    private final GithubFileDownloaderService
            githubFileDownloaderService;

    private final EngineeringEvidenceProperties
            engineeringEvidenceProperties;

    @Override
    public Map<String, String> collect(
            String owner,
            String repository,
            List<Map<String, Object>> repositoryTree
    ) {

        System.out.println("\n========== EVIDENCE CATEGORIES ==========");
System.out.println(engineeringEvidenceProperties.getCategories().keySet());
System.out.println("Category Count = " +
        engineeringEvidenceProperties.getCategories().size());

        Map<String, String> importantFiles =
                new LinkedHashMap<>();

        for (Map<String, Object> node : repositoryTree) {

            if (!"blob".equals(node.get("type"))) {
                continue;
            }

            String path = (String) node.get("path");

            System.out.println(path);

            if (!isImportant(path)) {
                continue;
            }

            try {

                String content =
                        githubFileDownloaderService
                                .downloadFile(
                                        owner,
                                        repository,
                                        path
                                );

                if (content != null && !content.isBlank()) {

                    importantFiles.put(
                            path,
                            content
                    );

                }

            }
            catch (Exception ignored) {

            }

        }

        return importantFiles;

    }

    

    private boolean isImportant(
            String path
    ) {
System.out.println("Checking : " + path);

        String lowerPath =
                path.toLowerCase(Locale.ROOT);

        String fileName =
                path.substring(
                        path.lastIndexOf("/") + 1
                ).toLowerCase(Locale.ROOT);

        for (EngineeringEvidenceProperties.EvidenceCategory category :
                engineeringEvidenceProperties
                        .getCategories()
                        .values()) {

            /*
             * Ignore directories
             */

            if (category.getIgnoredDirectories() != null) {

                boolean ignored = category
                        .getIgnoredDirectories()
                        .stream()
                        .map(dir -> dir.toLowerCase(Locale.ROOT))
                        .anyMatch(dir ->
                                lowerPath.startsWith(dir + "/"));

                if (ignored) {
                    continue;
                }

            }

            /*
             * Exact filenames
             */

            if (category.getExactNames() != null) {

                boolean match = category
                        .getExactNames()
                        .stream()
                        .map(name -> name.toLowerCase(Locale.ROOT))
                        .anyMatch(fileName::equals);

                if (match) {


                    System.out.println("Matched : " + path);

                    return true;
                }

            }

            /*
             * Contains keywords
             */

            if (category.getContains() != null) {

                boolean match = category
                        .getContains()
                        .stream()
                        .map(keyword -> keyword.toLowerCase(Locale.ROOT))
                        .anyMatch(lowerPath::contains);

                if (match) {

                    System.out.println("Matched : " + path);

                    return true;
                }

            }

            /*
             * Extensions
             */

            if (category.getExtensions() != null) {

                int index = fileName.lastIndexOf('.');

                if (index != -1) {

                    String extension =
                            fileName.substring(index + 1);

                    boolean match = category
                            .getExtensions()
                            .stream()
                            .map(ext -> ext.toLowerCase(Locale.ROOT))
                            .anyMatch(extension::equals);

                    if (match) {

                        System.out.println("Matched : " + path);

                        return true;
                    }

                }

            }

        }

        return false;

    }

}