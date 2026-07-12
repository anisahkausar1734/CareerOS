package com.careeros.careeros_backend.service.github.intelligence.facts;

import com.careeros.careeros_backend.dto.github.intelligence.facts.RepositoryFact;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.service.github.intelligence.facts.providers.RepositoryFactProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryFactExtractorImpl
        implements RepositoryFactExtractor {

    private final List<RepositoryFactProvider> providers;

    @Override
    public List<RepositoryFact> extract(
            RepositorySnapshot snapshot
    ) {

        List<RepositoryFact> facts =
                new ArrayList<>();

        for (RepositoryFactProvider provider : providers) {

            facts.addAll(
                    provider.extract(snapshot)
            );

        }

        return facts;

    }

}