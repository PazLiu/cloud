package com.shty.collect.service.fb;

import com.shty.collect.domain.fb.OverviewEducation;
import com.shty.collect.domain.fb.OverviewEducationDescriptions;

public interface OverviewEducation_ServiceI {
	public int insert_OverviewEducation(OverviewEducation overviewEducation);
	public void insert_OverviewEducationDescriptions(OverviewEducationDescriptions overviewEducationDescriptions);
	public void delete_OverviewEducation(Long FBTarget_id);
}
