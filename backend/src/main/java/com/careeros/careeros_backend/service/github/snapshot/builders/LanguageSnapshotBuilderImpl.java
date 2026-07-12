package com.careeros.careeros_backend.service.github.snapshot.builders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LanguageSnapshotBuilderImpl
        implements LanguageSnapshotBuilder {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> buildLanguages(
            Map<String, Object> repository
    ) {

        Map<String,Integer> languages =
                new HashMap<>();

        Map<String,Object> languageMap =
                (Map<String,Object>)
                        repository.get("languages");

        if(languageMap==null)
            return languages;

        List<Map<String,Object>> edges =
                (List<Map<String,Object>>)
                        languageMap.get("edges");

        if(edges==null)
            return languages;

        for(Map<String,Object> edge : edges){

            Map<String,Object> node =
                    (Map<String,Object>)
                            edge.get("node");

            languages.put(

                    (String) node.get("name"),

                    (Integer) edge.get("size")

            );

        }

        return languages;

    }

}