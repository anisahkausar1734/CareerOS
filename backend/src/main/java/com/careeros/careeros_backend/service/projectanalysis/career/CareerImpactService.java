package com.careeros.careeros_backend.service.projectanalysis.career;

import com.careeros.careeros_backend.dto.ProfileResponse;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectCareerImpact;
import com.careeros.careeros_backend.dto.projectanalysis.ProjectEngineeringAnalysis;
import com.careeros.careeros_backend.dto.projectanalysis.AnalysisPurpose;



public interface CareerImpactService {

    ProjectCareerImpact evaluate(

            ProjectEngineeringAnalysis engineering,

            ProfileResponse profile,

            AnalysisPurpose purpose

    );

}