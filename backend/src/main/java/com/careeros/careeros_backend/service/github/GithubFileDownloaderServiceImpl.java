package com.careeros.careeros_backend.service.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubFileDownloaderServiceImpl
        implements GithubFileDownloaderService {

    private final GithubRestService githubRestService;

    @Override
    public String downloadFile(
            String owner,
            String repository,
            String path
    ) {

        try {

            Map<String, Object> file =

                    githubRestService.getFile(
                            owner,
                            repository,
                            path
                    );

            String encoded =

                    (String) file.get(
                            "content"
                    );

            if (encoded == null) {
                return null;
            }

            encoded =

                    encoded.replace(
                            "\n",
                            ""
                    );

            byte[] decoded =

                    Base64.getDecoder()
                            .decode(
                                    encoded
                            );

            return new String(
                    decoded,
                    StandardCharsets.UTF_8
            );

        }

        catch (Exception e) {

            return null;

        }

    }

}