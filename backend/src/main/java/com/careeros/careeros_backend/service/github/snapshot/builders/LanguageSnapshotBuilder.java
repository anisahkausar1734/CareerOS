package com.careeros.careeros_backend.service.github.snapshot.builders;

import java.util.Map;

public interface LanguageSnapshotBuilder {

    Map<String, Integer> buildLanguages(
            Map<String, Object> repository
    );

}