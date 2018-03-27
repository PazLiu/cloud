package com.shty.collect.service.lnk.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.EndorsementsDAO;
import com.shty.collect.dao.lnk.PeopleDAO;
import com.shty.collect.dao.lnk.SkillsDAO;
import com.shty.collect.domain.lnk.Endorsements;
import com.shty.collect.domain.lnk.People;
import com.shty.collect.domain.lnk.Skills;
import com.shty.collect.domain.lnk.SomebodyEndor;
import com.shty.collect.service.lnk.SkillsService;
import com.shty.collect.util.MyUtil;

@Service
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsDAO skillsDao;
	@Autowired
	private PeopleDAO peopleDao;
	@Autowired
	private EndorsementsDAO endorsementsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addSkills(Skills skills) {
		// TODO Auto-generated method stub
		return skillsDao.addSkills(skills);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Skills getSkillBySkillId(Long skillId) {

		Skills skills = new Skills();
		PageHelper.startPage(1, 1);
		skills = skillsDao.getSkillBySkillId(skillId);

		PageHelper.startPage(1, 0);
		List<Endorsements> endorsements = endorsementsDao.getEndorsementsBySkillsId(skillId);// 含imgname

		if (endorsements.size() > 0) {

			for (Endorsements item : endorsements) {
				if (null != item.getImgname()) {
					item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
				}
			}

			// ����endorsements�ڵ�imgcode
			// for(Endorsements item : endorsements){
			// if( !"".equals(item.getImgcode()) && item.getImgcode() != null ){
			// item.setDecodeimgcode(MyUtil.imgDecode(item.getImgcode()));
			// }
			//
			// }

			skills.setEndorsements(endorsements);

		}

		return skills;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Skills> getPeopleSkillById(Long peopleId) {

		PageHelper.startPage(1, 1);
		People p = peopleDao.getPeopleById(peopleId);

		List<Skills> skills = null;
		if (null != p && null != p.getId()) {

			PageHelper.startPage(1, 0);
			skills = skillsDao.getSkillsByPeopleId(p.getId());
			if (null != skills && skills.size() > 0) {

				for (Skills skill : skills) {
					PageHelper.startPage(1, 0);
					List<Endorsements> endorsements = endorsementsDao.getEndorsementsBySkillsIdLimitTen(skill.getId());// 含imgname

					if (endorsements.size() > 0) {

						for (Endorsements item : endorsements) {
							if (null != item.getImgname()) {
								item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
							}
						}
						// 解码endorsements内的imgcode
						// for(Endorsements item : endorsements){
						// if( !"".equals(item.getImgcode()) &&
						// item.getImgcode() != null ){
						// item.setDecodeimgcode(MyUtil.imgDecode(item.getImgcode()));
						//// System.out.println("BBB:
						// "+item.getDecodeimgcode());
						// item.setImgcode(null);
						// }
						//
						// }

						skill.setEndorsements(endorsements);

					}
				}

			}
			System.out.println(skills.size());
		}

		return skills;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<SomebodyEndor> getSomebodyEndor(Long peopleId, int type) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 0);
		List<SomebodyEndor> data = skillsDao.getSomebodyEndor(peopleId); // 数据库的数据
		List<SomebodyEndor> showData = new ArrayList<SomebodyEndor>();// 返回前端的包装数据

		Set<String> lnkIds = new HashSet<String>();// 存放已保存的lnkid
		if (null != data && data.size() > 0) {

			for (SomebodyEndor d : data) {
				if (lnkIds.contains(d.getLnkid())) {// lnkid保存过
					for (SomebodyEndor s : showData) {
						if (s.getLnkid().equals(d.getLnkid())) {
							List<String> l = s.getSkills();
							l.add(d.getSkillname());
							s.setSkills(l);
							break;
						}
					}
				} else {
					lnkIds.add(d.getLnkid());// lnkid暂未存过
					SomebodyEndor e = new SomebodyEndor();
					e.setLnkid(d.getLnkid());
					e.setImgname(d.getImgname());
					e.setPeoplename(d.getPeoplename());
					List<String> a = new ArrayList<String>();
					a.add(d.getSkillname());
					e.setSkills(a);
					// if(e.getLnkid().equals("244457503")){
					// System.out.println(d.getSkillname());
					// System.out.println(e.getPeoplename());
					// System.out.println(e.getSkills().get(0));
					// }
					showData.add(e);
				}

			}

			for (SomebodyEndor d : data) {
				if (d.getPeoplename().equals("John Iona")) {
					System.out.println(d.getSkillname());
				}
			}

		}
		return showData;
	}

}
