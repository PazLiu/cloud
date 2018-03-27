package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.AdditionalinfoDAO;
import com.shty.collect.dao.lnk.AdditionalsDAO;
import com.shty.collect.dao.lnk.AuthorsDAO;
import com.shty.collect.dao.lnk.CertificationsDAO;
import com.shty.collect.dao.lnk.ConnectionsDAO;
import com.shty.collect.dao.lnk.CourseDAO;
import com.shty.collect.dao.lnk.EducationsActivitiesDAO;
import com.shty.collect.dao.lnk.EducationsAttachementsDAO;
import com.shty.collect.dao.lnk.EducationsDAO;
import com.shty.collect.dao.lnk.EndorsementsDAO;
import com.shty.collect.dao.lnk.ExperiencesAttachementsDAO;
import com.shty.collect.dao.lnk.ExperiencesDAO;
import com.shty.collect.dao.lnk.FollowChannelsDAO;
import com.shty.collect.dao.lnk.FollowCompanyDAO;
import com.shty.collect.dao.lnk.FollowPeopleDAO;
import com.shty.collect.dao.lnk.FollowSchoolDAO;
import com.shty.collect.dao.lnk.GivenDAO;
import com.shty.collect.dao.lnk.GroupsDAO;
import com.shty.collect.dao.lnk.HonorsDAO;
import com.shty.collect.dao.lnk.ImgCodeDAO;
import com.shty.collect.dao.lnk.InterestsDAO;
import com.shty.collect.dao.lnk.InventorsDAO;
import com.shty.collect.dao.lnk.LanguagesDAO;
import com.shty.collect.dao.lnk.OrganizationsDAO;
import com.shty.collect.dao.lnk.OverviewDAO;
import com.shty.collect.dao.lnk.PatentsDAO;
import com.shty.collect.dao.lnk.PeopleDAO;
import com.shty.collect.dao.lnk.ProjectsDAO;
import com.shty.collect.dao.lnk.PublicationsDAO;
import com.shty.collect.dao.lnk.ReceivedDAO;
import com.shty.collect.dao.lnk.RecommendersDAO;
import com.shty.collect.dao.lnk.SimiliarsDAO;
import com.shty.collect.dao.lnk.SkillsDAO;
import com.shty.collect.dao.lnk.SummaryAttachementsDAO;
import com.shty.collect.dao.lnk.TeamerDAO;
import com.shty.collect.dao.lnk.TestscoresDAO;
import com.shty.collect.dao.lnk.VolunteersDAO;
import com.shty.collect.dao.lnk.WebsitesDAO;
import com.shty.collect.domain.lnk.Additionalinfo;
import com.shty.collect.domain.lnk.Additioninfo;
import com.shty.collect.domain.lnk.Authors;
import com.shty.collect.domain.lnk.Certifications;
import com.shty.collect.domain.lnk.Connections;
import com.shty.collect.domain.lnk.Course;
import com.shty.collect.domain.lnk.Educations;
import com.shty.collect.domain.lnk.Experiences;
import com.shty.collect.domain.lnk.Follow_channels;
import com.shty.collect.domain.lnk.Follow_company;
import com.shty.collect.domain.lnk.Follow_people;
import com.shty.collect.domain.lnk.Follow_school;
import com.shty.collect.domain.lnk.Follows;
import com.shty.collect.domain.lnk.Given;
import com.shty.collect.domain.lnk.Groups;
import com.shty.collect.domain.lnk.Honors;
import com.shty.collect.domain.lnk.HonorsOut;
import com.shty.collect.domain.lnk.ImgCode;
import com.shty.collect.domain.lnk.Interests;
import com.shty.collect.domain.lnk.Inventors;
import com.shty.collect.domain.lnk.Languages;
import com.shty.collect.domain.lnk.Organizations;
import com.shty.collect.domain.lnk.OrganizationsOut;
import com.shty.collect.domain.lnk.Others;
import com.shty.collect.domain.lnk.Overview;
import com.shty.collect.domain.lnk.Patents;
import com.shty.collect.domain.lnk.People;
import com.shty.collect.domain.lnk.Projects;
import com.shty.collect.domain.lnk.Publications;
import com.shty.collect.domain.lnk.Received;
import com.shty.collect.domain.lnk.Recommenders;
import com.shty.collect.domain.lnk.Similiars;
import com.shty.collect.domain.lnk.Teamer;
import com.shty.collect.domain.lnk.Testscores;
import com.shty.collect.domain.lnk.Volunteering;
import com.shty.collect.domain.lnk.Volunteers;
import com.shty.collect.domain.lnk.Websites;
import com.shty.collect.domain.lnk.vo.PeopleShow;
import com.shty.collect.service.lnk.PeopleService;
import com.shty.collect.util.MyUtil;
import com.shty.collect.web.rest.dto.LinkedCountDayDto;
import com.shty.collect.web.rest.dto.LnkPeopleDto;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleDAO peopleDao;
	@Autowired
	private ImgCodeDAO imgCodeDao;
	@Autowired
	private OverviewDAO overviewDao;
	@Autowired
	private ExperiencesDAO experiencesDao;
	@Autowired
	private EducationsDAO educationsDao;
	@Autowired
	private SimiliarsDAO similiarsDao;
	@Autowired
	private SkillsDAO skillsDao;
	@Autowired
	private EndorsementsDAO endorsementsDao;
	@Autowired
	private ConnectionsDAO connectionsDao;
	@Autowired
	private GivenDAO givenDao;
	@Autowired
	private ReceivedDAO receivedDao;
	@Autowired
	private RecommendersDAO recommendersDao;
	@Autowired
	private WebsitesDAO websitesDao;
	@Autowired
	private AdditionalinfoDAO additionalinfoDAO;
	@Autowired
	private CertificationsDAO certificationsDAO;
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private FollowChannelsDAO followChannelsDAO;
	@Autowired
	private FollowCompanyDAO followCompanyDAO;
	@Autowired
	private FollowSchoolDAO followSchoolDAO;
	@Autowired
	private FollowPeopleDAO followPeopleDAO;
	@Autowired
	private HonorsDAO honorsDAO;
	@Autowired
	private InterestsDAO interestsDAO;
	@Autowired
	private LanguagesDAO languagesDAO;
	@Autowired
	private GroupsDAO groupsDAO;
	@Autowired
	private OrganizationsDAO organizationsDAO;
	@Autowired
	private PatentsDAO patentsDAO;
	@Autowired
	private InventorsDAO inventorsDAO;
	@Autowired
	private ProjectsDAO projectsDAO;
	@Autowired
	private AuthorsDAO authorsDAO;
	@Autowired
	private PublicationsDAO publicationsDAO;
	@Autowired
	private TeamerDAO teamerDAO;
	@Autowired
	private TestscoresDAO testscoresDAO;
	@Autowired
	private VolunteersDAO volunteersDAO;
	@Autowired
	private SummaryAttachementsDAO summaryAttachementsDAO;
	@Autowired
	private EducationsAttachementsDAO educationsAttachementsDAO;
	@Autowired
	private EducationsActivitiesDAO educationsActivitiesDAO;
	@Autowired
	private ExperiencesAttachementsDAO experiencesAttachementsDAO;
	@Autowired
	private AdditionalsDAO additionalsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addPeople(People people) {
		// TODO Auto-generated method stub

		return peopleDao.addPeople(people);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int countLnkid(String lnkid) {
		PageHelper.startPage(1, 1);
		return peopleDao.countLnkid(lnkid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PeopleShow getPeopleById(Long id) {

		PeopleShow peopleShow = new PeopleShow();
		People people = new People();

		PageHelper.startPage(1, 1);
		people = peopleDao.getPeopleById(id);

		if (people != null) {
			Long peopleId = people.getId();

			// 组装成前台显示的对象
			peopleShow.setLnkid(people.getLnkid());
			peopleShow.setSummary(people.getSummary());
			peopleShow.setCollectionTime(people.getCollectionTime().substring(0, 19));

			// TODO Auto-generated catch block
			// summary_attachements的DAO已完成

			if (people.getImgcode_id() != null) {
				//

				ImgCode imgCode = new ImgCode();

				PageHelper.startPage(1, 1);
				imgCodeDao.getImgCodeById(people.getImgcode_id());

				String imgname = MyUtil.BASE_IMG_URL + imgCode.getImgname();
				// byte[] decodeImage = MyUtil.imgDecode(imgcode);
				//// System.out.println(Util.byteToString(decodeImage));
				peopleShow.setImgname(imgname);
			}
			PageHelper.startPage(1, 1);
			Overview overview = overviewDao.getOverviewByPeopleId(peopleId);

			if (overview != null) {

				PageHelper.startPage(1, 1);
				Websites web = websitesDao.getWebsites(overview.getId());

				if (web != null) {
					overview.setWebsites(web);
				}
				if (null != overview.getImim()) {
					overview.setImim(overview.getImim().replace("[", "").replace("]", "").replace("\"", ""));
				}
				peopleShow.setOverview(overview);
			}

			// TODO Auto-generated catch block
			// 增加educations_activities的DAO已完成
			// educations_attachements的DAO已完成
			// experiences_attachements的DAO已完成
			PageHelper.startPage(1, 0);
			List<Experiences> experiences = experiencesDao.getExperiencesByPeopleId(peopleId);

			if (experiences.size() > 0) {
				peopleShow.setExperiences(experiences);
			}
			PageHelper.startPage(1, 0);
			List<Educations> educations = educationsDao.getEducationsByPeopleId(peopleId);
			if (educations.size() > 0) {

				for (Educations e : educations) {
					System.err.println(e.getId());
					PageHelper.startPage(1, 0);
					e.setActivities(educationsActivitiesDAO.getEducationsActivitiesByPeopleId(e.getId()));
				}
				peopleShow.setEducations(educations);
			}
			PageHelper.startPage(1, 0);
			List<Similiars> similiars = similiarsDao.getSimiliarsByPeopleId(peopleId);
			if (similiars.size() > 0) {
				peopleShow.setSimiliars(similiars);
			}
			PageHelper.startPage(1, 0);
			List<Connections> connections = connectionsDao.getConnectionsByPeopleId(peopleId);// 含imgname
			if (connections.size() > 0) {

				for (Connections item : connections) {
					if (null != item.getImgname()) {
						item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
					}
				}

				peopleShow.setConnections(connections);
			}

			/*
			 * 
			 * 未完成
			 * 
			 */
			/*
			 * PageHelper.startPage(1, 1); List<Skills> skills =
			 * skillsDao.getSkillsByPeopleId(peopleId); if (skills.size() > 0) {
			 * 
			 * for (Skills skill : skills) { PageHelper.startPage(1, 0);
			 * List<Endorsements> endorsements =
			 * endorsementsDao.getEndorsementsBySkillsIdLimitTen(skill.getId());
			 * // 含imgname
			 * 
			 * if (endorsements.size() > 0) {
			 * 
			 * for (Endorsements item : endorsements) { if (null !=
			 * item.getImgname()) { item.setImgname(MyUtil.BASE_IMG_URL +
			 * item.getImgname()); } }
			 * 
			 * skill.setEndorsements(endorsements);
			 * 
			 * } }
			 * 
			 * peopleShow.setSkills(skills); }
			 */

			PageHelper.startPage(1, 0);
			List<Given> given = givenDao.getGivenByPeopleId(peopleId);// 含imgname
			if (given.size() > 0) {

				for (Given item : given) {
					if (null != item.getImgname()) {
						item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
					}
				}

				peopleShow.setGiven(given);
			}
			PageHelper.startPage(1, 0);
			List<Received> received = receivedDao.getReceivedByPeopleId(peopleId);
			if (received.size() > 0) {

				for (Received re : received) {
					PageHelper.startPage(1, 0);
					List<Recommenders> recommenders = recommendersDao.getRecommendersByReceivedId(re.getId());// 含imgname

					if (recommenders.size() > 0) {

						for (Recommenders item : recommenders) {
							if (null != item.getImgname()) {
								item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
							}
						}
						re.setRecommenders(recommenders);
					}

				}
				peopleShow.setReceived(received);
			}

			// news

			/*
			 * follows follows follows follows follows follows follows follows
			 * follows
			 */
			Others other = new Others();
			Follows follows = new Follows();

			PageHelper.startPage(1, 0);
			List<Follow_school> follow_school_list = followSchoolDAO.getFollowSchoolByPeopleId(peopleId);

			PageHelper.startPage(1, 0);
			List<Follow_channels> follow_channels_list = followChannelsDAO.getFollowChannelsByPeopleId(peopleId);

			PageHelper.startPage(1, 0);
			List<Follow_company> follow_company_list = followCompanyDAO.getFollowCompanyByPeopleId(peopleId);

			PageHelper.startPage(1, 0);
			List<Follow_people> follow_people_list = followPeopleDAO.getFollowPeopleByPeopleId(peopleId);// 含imgname

			if (null != follow_school_list && follow_school_list.size() > 0) {
				follows.setFollow_school(follow_school_list);
			}

			if (null != follow_channels_list && follow_channels_list.size() > 0) {
				follows.setFollow_channels(follow_channels_list);
			}

			if (null != follow_company_list && follow_company_list.size() > 0) {
				follows.setFollow_company(follow_company_list);
			}

			if (null != follow_people_list && follow_people_list.size() > 0) {

				for (Follow_people item : follow_people_list) {
					if (null != item.getImgname()) {
						item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
					}
				}

				follows.setFollow_people(follow_people_list);
			}

			other.setFollows(follows);

			/*
			 * volunteering volunteering volunteering volunteering volunteering
			 * volunteering volunteering volunteering volunteering
			 */
			Volunteering volunteering = new Volunteering();

			PageHelper.startPage(1, 0);
			List<Volunteers> volunteers_list = volunteersDAO.getVolunteersByPeopleId(peopleId);

			if (null != volunteers_list && volunteers_list.size() > 0) {
				volunteering.setVolunteers(volunteers_list);
			}

			PageHelper.startPage(1, 1);
			List<Interests> interests_list = interestsDAO.getInterestsByPeopleId(peopleId);

			if (null != interests_list && interests_list.size() > 0) {
				volunteering.setInterests(interests_list);
			}

			other.setVolunteering(volunteering);

			/*
			 * languages languages languages languages languages languages
			 * languages languages languages
			 */
			PageHelper.startPage(1, 0);
			List<Languages> languages_list = languagesDAO.getLanguagesByPeopleId(peopleId);

			if (null != languages_list && languages_list.size() > 0) {
				other.setLanguages(languages_list);
			}

			/*
			 * groups groups groups groups groups groups groups groups groups
			 */
			PageHelper.startPage(1, 0);
			List<Groups> groups_list = groupsDAO.getGroupsByPeopleId(peopleId);

			if (null != groups_list && groups_list.size() > 0) {
				other.setGroups(groups_list);
			}

			/*
			 * certifications certifications certifications certifications
			 * certifications certifications certifications certifications
			 * certifications
			 */
			PageHelper.startPage(1, 0);
			List<Certifications> certifications_list = certificationsDAO.getCertificationsByPeopleId(peopleId);

			if (null != certifications_list && certifications_list.size() > 0) {
				other.setCertifications(certifications_list);
			}

			/*
			 * organizations organizations organizations organizations
			 * organizations organizations organizations organizations
			 * organizations
			 */
			OrganizationsOut oo = new OrganizationsOut();

			PageHelper.startPage(1, 0);
			List<Organizations> organizations_list = organizationsDAO.getOrganizationsByPeopleId(peopleId);

			if (null != organizations_list && organizations_list.size() > 0) {
				oo.setOrganizations(organizations_list);
			}
			other.setOrganizations(oo);

			// TODO Auto-generated catch block
			// 增加Additionals的DAO完成

			/*
			 * honors honors honors honors honors honors honors honors honors
			 */
			HonorsOut honors = new HonorsOut();
			PageHelper.startPage(1, 0);
			List<Honors> honors_list = honorsDAO.getHonorsByPeopleId(peopleId);

			if (null != honors_list && honors_list.size() > 0) {
				honors.setHonors(honors_list);
			}

			other.setHonors(honors);

			/*
			 * projects projects projects projects projects projects projects
			 * projects projects
			 */
			PageHelper.startPage(1, 0);
			List<Projects> projects_list = projectsDAO.getProjectsByPeopleId(peopleId);

			if (null != projects_list && projects_list.size() > 0) {
				for (Projects projects : projects_list) {
					PageHelper.startPage(1, 0);
					List<Teamer> teamer = teamerDAO.getTeamerByProjectId(projects.getId());// 含imgname
					if (null != teamer && teamer.size() > 0) {

						for (Teamer item : teamer) {
							if (null != item.getImgname()) {
								item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
							}
						}

						projects.setTeamer(teamer);
					}
				}
				other.setProjects(projects_list);
			}

			/*
			 * additionalinfo
			 */
			Additionalinfo additionalinfo = new Additionalinfo();
			PageHelper.startPage(1, 0);
			List<Additioninfo> additioninfo_list = additionalinfoDAO.getAdditionalinfoByPeopleId(peopleId);

			if (null != honors_list && honors_list.size() > 0) {
				additionalinfo.setAdditioninfo(additioninfo_list);
			}

			other.setAdditionalinfo(additionalinfo);

			/*
			 * testscores testscores testscores testscores testscores testscores
			 * testscores testscores testscores
			 */
			PageHelper.startPage(1, 0);
			List<Testscores> testscores_list = testscoresDAO.getTestscoresByPeopleId(peopleId);

			if (null != testscores_list && testscores_list.size() > 0) {
				other.setTestscores(testscores_list);
			}

			/*
			 * publications publications publications publications publications
			 * publications publications publications publications
			 */
			PageHelper.startPage(1, 0);
			List<Publications> publications_list = publicationsDAO.getPublicationsByPeopleId(peopleId);

			if (null != publications_list && publications_list.size() > 0) {
				for (Publications publications : publications_list) {
					PageHelper.startPage(1, 0);
					List<Authors> authors = authorsDAO.getAuthorsByPublicationId(publications.getId());// 含imgname
					if (null != authors && authors.size() > 0) {

						for (Authors item : authors) {
							if (null != item.getImgname()) {
								item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
							}
						}

						publications.setAuthors(authors);
					}
				}
				other.setPublications(publications_list);
			}

			/*
			 * patents patents patents patents patents patents patents patents
			 * patents
			 */
			PageHelper.startPage(1, 0);
			List<Patents> patents_list = patentsDAO.getPatentsByPeopleId(peopleId);

			if (null != patents_list && patents_list.size() > 0) {
				for (Patents patents : patents_list) {
					PageHelper.startPage(1, 0);
					List<Inventors> inventors = inventorsDAO.getInventorsByPatentId(patents.getId());// 含imgname
					if (null != inventors && inventors.size() > 0) {

						for (Inventors item : inventors) {
							if (null != item.getImgname()) {
								item.setImgname(MyUtil.BASE_IMG_URL + item.getImgname());
							}
						}

						// 解码inventors内的imgcode
						// for(Inventors invs : inventors){
						// if( !"".equals(invs.getImgcode()) &&
						// invs.getImgcode() != null ){
						// invs.setDecodeimgcode(MyUtil.imgDecode(invs.getImgcode()));
						//// System.out.println(item.getImgcode());
						// invs.setImgcode(null);
						//// System.out.println(item.getDecodeimgcode());
						// }
						//
						// }
						patents.setInventors(inventors);
					}
				}
				other.setPatents(patents_list);
			}

			/*
			 * course course course course course course course course course
			 */
			PageHelper.startPage(1, 0);
			List<Course> course_list = courseDAO.getCourseByPeopleId(peopleId);

			if (null != course_list && course_list.size() > 0) {
				other.setCourse(course_list);
			}

			peopleShow.setOthers(other);

		}

		return peopleShow;
	}

	@Override
	public List<LnkPeopleDto> getAllPeople() {
		PageHelper.startPage(1, 0);
		return peopleDao.getAllPeople();
	}

	@Override
	public int getIdByLnkid(String lnkid) {
		PageHelper.startPage(1, 1);
		return peopleDao.getIdByLnkid(lnkid);
	}

	@Override
	public LinkedCountDayDto getCountByDay(String number) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return peopleDao.getCountByDay(number);
	}

}
