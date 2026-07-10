package com.careeros.careeros_backend.service.github;

public interface GithubFileDownloaderService {

    String downloadFile(
            String owner,
            String repository,
            String path
    );

}