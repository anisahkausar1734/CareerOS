package com.careeros.careeros_backend.dto.github.source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositorySourceFile {

    /*
     * Relative path
     */
    private String path;

    /*
     * File name
     */
    private String fileName;

    /*
     * File extension
     */
    private String extension;

    /*
     * GitHub download URL
     */
    private String downloadUrl;

    /*
     * Raw file content
     */
    private String content;

}