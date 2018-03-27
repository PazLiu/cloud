package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewContactsMapper;
import com.shty.collect.domain.fb.OverviewContacts;
import com.shty.collect.service.fb.OverviewContacts_ServiceI;

@Service
public class OverviewContacts_ServiceImpl implements OverviewContacts_ServiceI {
	
	@Autowired	
	OverviewContactsMapper overviewContactsMapper;
	
	@Override
	public void insert_OverviewContacts(OverviewContacts overviewContacts) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewContacts(overviewContacts);
	}

	@Override
	public void delete_OverviewContacts(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewContacts(FBTarget_id);
	}

	@Override
	public void insert_OverviewBasicinfos(OverviewContacts overviewContacts) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewBasicinfos(overviewContacts);
	}

	@Override
	public void delete_OverviewBasicinfos(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewBasicinfos(FBTarget_id);
	}

	@Override
	public void insert_OverviewNicknames(OverviewContacts overviewContacts) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewNicknames(overviewContacts);
	}

	@Override
	public void delete_OverviewNicknames(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewNicknames(FBTarget_id);
	}

	@Override
	public void insert_OverviewRelationship(OverviewContacts overviewContacts) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewRelationship(overviewContacts);
	}

	@Override
	public void delete_OverviewRelationship(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewRelationship(FBTarget_id);
	}

	@Override
	public void insert_OverviewBio(OverviewContacts overviewBio) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewBio(overviewBio);
	}

	@Override
	public void delete_OverviewBio(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewBio(FBTarget_id);
	}

	@Override
	public void insert_OverviewQuote(OverviewContacts overviewQuote) {
		// TODO Auto-generated method stub
		overviewContactsMapper.insert_OverviewQuote(overviewQuote);
	}

	@Override
	public void delete_OverviewQuote(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewContactsMapper.delete_OverviewQuote(FBTarget_id);
	}

}
