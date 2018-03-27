package com.shty.collect.service.fb;

import com.shty.collect.domain.fb.OverviewContacts;

public interface OverviewContacts_ServiceI {
	public void insert_OverviewContacts(OverviewContacts overviewContacts);
	public void delete_OverviewContacts(Long FBTarget_id);
	
	
	public void insert_OverviewBasicinfos(OverviewContacts overviewBasicInfos);
	public void delete_OverviewBasicinfos(Long FBTarget_id);
	
	
	public void insert_OverviewNicknames(OverviewContacts overviewNicknames);
	public void delete_OverviewNicknames(Long FBTarget_id);
	
	public void insert_OverviewRelationship(OverviewContacts overviewRelationship);
	public void delete_OverviewRelationship(Long FBTarget_id);
	
	public void insert_OverviewBio(OverviewContacts overviewBio);
	public void delete_OverviewBio(Long FBTarget_id);
	
	public void insert_OverviewQuote(OverviewContacts overviewQuote);
	public void delete_OverviewQuote(Long FBTarget_id);
	
}
