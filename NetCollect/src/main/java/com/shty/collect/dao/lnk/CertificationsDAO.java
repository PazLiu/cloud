package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Certifications;

@Repository
public interface CertificationsDAO {

	public int addCertifications(List<Certifications> certifications);

	public List<Certifications> getCertificationsByPeopleId(Long peopleId);

	// 删除
	public int deleteCertifications(Long peopleId);

}
