package com.careeros.careeros_backend.service.github.cache;

import com.careeros.careeros_backend.dto.github.RepositoryEvidenceResponse;
import com.careeros.careeros_backend.dto.github.RepositoryFingerprint;
import com.careeros.careeros_backend.dto.github.RepositoryRawEvidence;
import com.careeros.careeros_backend.model.RepositoryEvidenceCache;
import com.careeros.careeros_backend.repository.RepositoryEvidenceCacheRepository;
import com.careeros.careeros_backend.service.github.builder.RepositoryEvidenceBuilder;
import com.careeros.careeros_backend.service.github.collector.RepositoryCollectorService;
import com.careeros.careeros_backend.dto.github.intelligence.RepositoryIntelligence;
import com.careeros.careeros_backend.dto.github.snapshot.RepositorySnapshot;
import com.careeros.careeros_backend.service.github.intelligence.RepositoryIntelligenceEngine;
import com.careeros.careeros_backend.service.github.snapshot.RepositorySnapshotBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RepositoryCacheServiceImpl
        implements RepositoryCacheService {

    private final RepositoryEvidenceCacheRepository cacheRepository;

    private final RepositoryCollectorService collectorService;

    private final RepositoryEvidenceBuilder evidenceBuilder;

    private final RepositoryFingerprintService fingerprintService;

    private final RepositorySnapshotBuilder snapshotBuilder;

private final RepositoryIntelligenceEngine intelligenceEngine;

    @Override
    public RepositoryEvidenceResponse getRepositoryEvidence(
            String githubUrl,
            boolean forceRefresh
    ) {

        RepositoryFingerprint latestFingerprint =
                fingerprintService.getFingerprint(
                        githubUrl
                );

        RepositoryEvidenceCache cache =
                cacheRepository.findByRepositoryUrl(
                        githubUrl
                ).orElse(null);

        if (!forceRefresh
                && cache != null
                && cache.getFingerprint() != null
                && latestFingerprint.getLatestCommitSha().equals(
                        cache.getFingerprint().getLatestCommitSha()
                )) {

            return cache.getEvidence();

        }

        RepositoryRawEvidence rawEvidence =
                collectorService.collect(
                        githubUrl
                );

        RepositorySnapshot snapshot =
        snapshotBuilder.build(
                rawEvidence
        );

RepositoryIntelligence intelligence =
        intelligenceEngine.build(
                snapshot
        );

RepositoryEvidenceResponse evidence =
        evidenceBuilder.build(
                rawEvidence,
                intelligence
        );

        RepositoryEvidenceCache updated =
                buildCache(
                        githubUrl,
                        latestFingerprint,
                        evidence,
                        cache
                );

        cacheRepository.save(
                updated
        );

        return evidence;

    }

    private RepositoryEvidenceCache buildCache(

            String githubUrl,

            RepositoryFingerprint fingerprint,

            RepositoryEvidenceResponse evidence,

            RepositoryEvidenceCache existing

    ) {

        RepositoryEvidenceCache cache =
                existing != null
                        ? existing
                        : new RepositoryEvidenceCache();

        cache.setRepositoryUrl(
                githubUrl
        );

        cache.setOwner(
                fingerprint.getOwner()
        );

        cache.setRepositoryName(
                fingerprint.getRepositoryName()
        );

        cache.setFingerprint(
                fingerprint
        );

        cache.setEvidence(
                evidence
        );

        cache.setAnalysisVersion(
                "v1"
        );

        cache.setUpdatedAt(
                LocalDateTime.now()
        );

        cache.setLastCollectedAt(
                LocalDateTime.now()
        );

        if (cache.getCreatedAt() == null) {

            cache.setCreatedAt(
                    LocalDateTime.now()
            );

        }

        return cache;

    }

}