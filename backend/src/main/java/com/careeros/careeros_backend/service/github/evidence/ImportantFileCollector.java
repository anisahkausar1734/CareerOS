package com.careeros.careeros_backend.service.github.evidence;

import java.util.List;
import java.util.Map;

public interface ImportantFileCollector {

    Map<String, String> collect(

            String owner,

            String repository,

            List<Map<String, Object>> repositoryTree

    );

}