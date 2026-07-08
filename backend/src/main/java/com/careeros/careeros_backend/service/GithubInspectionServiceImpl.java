package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.GithubInspectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubInspectionServiceImpl
        implements GithubInspectionService {

            

    private final RestTemplate restTemplate;
    
private boolean containsFile(
        List<Map<String,Object>> files,
        String filename
) {

    return files.stream()
            .anyMatch(
                    file ->
                            filename.equalsIgnoreCase(
                                    (String) file.get("name")
                            )
            );
}


private boolean containsFolder(
        List<Map<String, Object>> contents,
        String folderName
) {

    return contents.stream()
            .anyMatch(
                    item ->
                            folderName.equalsIgnoreCase(
                                    (String) item.get("name")
                            )
            );
}

private int countOccurrences(
        List<Map<String,Object>> tree,
        String keyword
) {

    return (int)
            tree.stream()
                    .filter(
                            item -> {

                                String path =
                                        (String)
                                                item.get(
                                                        "path"
                                                );

                                return path
                                        .toLowerCase()
                                        .contains(
                                                keyword
                                                        .toLowerCase()
                                        );
                            }
                    )
                    .count();
}

private int countConfigFiles(
        List<Map<String,Object>> tree
)
{

    return (int)

            tree.stream()

                    .filter(
                            item -> {

                                String path =
                                        ((String)
                                                item.get(
                                                        "path"
                                                ))
                                                .toLowerCase();

                                return

                                        path.endsWith(
                                                "config.java"
                                        )

                                        ||

                                        path.contains(
                                                "/config/"
                                        )

                                        ||

                                        path.endsWith(
                                                ".yml"
                                        )

                                        ||

                                        path.endsWith(
                                                ".yaml"
                                        )

                                        ||

                                        path.endsWith(
                                                ".properties"
                                        );

                            }
                    )

                    .count();
}



private int countByKeywords(
        List<Map<String,Object>> tree,
        String... keywords
) {

    return (int)
            tree.stream()
                    .filter(item -> {

                        String path =
                                ((String)
                                        item.get("path"))
                                        .toLowerCase();

                        for(String keyword : keywords) {

                            if(path.contains(
                                    keyword.toLowerCase()
                            )) {

                                return true;
                            }
                        }

                        return false;
                    })
                    .count();
}
    
    @Override
    public GithubInspectionResponse inspectRepository(
            String githubUrl
    ) {

        

        String[] parts =
                githubUrl.replace(
                        "https://github.com/",
                        ""
                ).split("/");

        String owner = parts[0];
        String repo = parts[1];

        String apiUrl =
                "https://api.github.com/repos/"
                        + owner
                        + "/"
                        + repo;

        ResponseEntity<Map> response =
                restTemplate.getForEntity(
                        apiUrl,
                        Map.class
                );

        Map<String, Object> data =
                response.getBody();


String branch =
        (String) data.get(
                "default_branch"
        );


String treeUrl =
        "https://api.github.com/repos/"
                + owner
                + "/"
                + repo
                + "/git/trees/"
                + branch
                + "?recursive=1";

ResponseEntity<Map> treeResponse =
        restTemplate.getForEntity(
                treeUrl,
                Map.class
        );

Map<String,Object> treeData =
        treeResponse.getBody();

List<Map<String,Object>> tree =
        (List<Map<String,Object>>)
                treeData.get(
                        "tree"
                );    
                
                int frontendFileCount =
        countByKeywords(
                tree,
                ".jsx",
                ".tsx",
                ".vue",
                ".svelte",
                ".html",
                ".css"
        );

boolean hasFrontend =
        frontendFileCount > 0;

int backendFileCount =
        countByKeywords(
                tree,
                "controller",
                "service",
                "api",
                "handler",
                "route",
                ".java",
                ".py",
                ".go",
                ".cs"
        );

boolean hasBackend =
        backendFileCount > 0;
        
int databaseFileCount =
        countByKeywords(
                tree,
                "mongodb",
                "mongoose",
                "jpa",
                "hibernate",
                "repository",
                "prisma",
                "sequelize",
                "typeorm",
                "sqlalchemy"
        );

boolean hasDatabase =
        databaseFileCount > 0;
        
int authFileCount =
        countByKeywords(
                tree,
                "jwt",
                "auth",
                "oauth",
                "security",
                "passport",
                "firebase"
        );

boolean hasAuthentication =
        authFileCount > 0;
        
int testFileCount =
        countByKeywords(
                tree,
                "test",
                "spec",
                "junit",
                "mockito",
                "jest",
                "cypress"
        );

boolean hasTesting =
        testFileCount > 0;
        
int deploymentFileCount =
        countByKeywords(
                tree,
                "docker",
                ".github",
                "jenkins",
                "kubernetes",
                "helm"
        );

boolean hasDeployment =
        deploymentFileCount > 0;
        
      

                String contentsApi =
        "https://api.github.com/repos/"
                + owner
                + "/"
                + repo
                + "/contents";

ResponseEntity<List> contentsResponse =
        restTemplate.getForEntity(
                contentsApi,
                List.class
        );


int controllerCount =
        countOccurrences(
                tree,
                "controller"
        );

int serviceCount =
        countOccurrences(
                tree,
                "service"
        );

int repositoryCount =
        countOccurrences(
                tree,
                "repository"
        );

int dtoCount =
        countOccurrences(
                tree,
                "dto"
        );

int configCount =
        countConfigFiles(
                tree
        );

int securityCount =
        countOccurrences(
                tree,
                "security"
        );

int componentCount =
        countOccurrences(
                tree,
                "component"
        );

int pageCount =
        countOccurrences(
                tree,
                "page"
        );        



List<Map<String,Object>> contents =
        contentsResponse.getBody();

boolean hasSecurity =
        securityCount > 0;



        boolean hasReadme =
        contents.stream()
                .anyMatch(
                        file ->
                                ((String) file.get("name"))
                                        .toLowerCase()
                                        .startsWith("readme")
                );

          boolean hasDocumentation =
        hasReadme;        

        boolean hasDocker =
        containsFile(
                contents,
                "Dockerfile"
        );

        boolean hasJwt =
        tree.stream()
                .anyMatch(
                        item -> {

                            String path =
                                    ((String)
                                            item.get(
                                                    "path"
                                            ))
                                            .toLowerCase();

                            return path.contains(
                                    "jwt"
                            );
                        }
                );

        boolean hasCICD =
        contents.stream()
                .anyMatch(
                        file ->
                                ".github".equals(
                                        file.get("name")
                                )
                );




        return GithubInspectionResponse
        .builder()
        .repositoryName(
                (String) data.get("name")
        )
        .owner(owner)
        .primaryLanguage(
                (String) data.get("language")
        )
        .stars(
                ((Number) data.get(
                        "stargazers_count"
                )).intValue()
        )
        .forks(
                ((Number) data.get(
                        "forks_count"
                )).intValue()
        )
        .openIssues(
                ((Number) data.get(
                        "open_issues_count"
                )).intValue()
        )
        .hasWiki(
                (Boolean) data.get(
                        "has_wiki"
                )
        )
        .hasProjects(
                (Boolean) data.get(
                        "has_projects"
                )
        )
        .defaultBranch(
                (String) data.get(
                        "default_branch"
                )
        )
        .repositoryUrl(
                (String) data.get(
                        "html_url"
                )
        )
        .description(
                (String) data.get(
                        "description"
                )
        )
        .hasReadme(
                hasReadme
        )
        .hasDocker(
                hasDocker
        )
        .hasCICD(
                hasCICD
        )
.controllerCount(
        controllerCount
)

.serviceCount(
        serviceCount
)

.repositoryCount(
        repositoryCount
)

.componentCount(
        componentCount
)

.pageCount(
        pageCount
)

.dtoCount(
        dtoCount
)

.configCount(
        configCount
)

.securityCount(
        securityCount
)

.hasSecurity(
        hasSecurity
)

.hasJwt(
        hasJwt
)
 
.hasFrontend(
        hasFrontend
)

.hasBackend(
        hasBackend
)

.hasDatabase(
        hasDatabase
)

.hasAuthentication(
        hasAuthentication
)

.hasTesting(
        hasTesting
)

.hasDeployment(
        hasDeployment
)

.hasDocumentation(
        hasDocumentation
)

.frontendFileCount(
        frontendFileCount
)

.backendFileCount(
        backendFileCount
)

.databaseFileCount(
        databaseFileCount
)

.authFileCount(
        authFileCount
)

.testFileCount(
        testFileCount
)

.deploymentFileCount(
        deploymentFileCount
)

       
        .build();

        
    }
}