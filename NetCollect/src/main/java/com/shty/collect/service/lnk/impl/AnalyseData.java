package com.shty.collect.service.lnk.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shty.collect.domain.lnk.Additionals;
import com.shty.collect.domain.lnk.Additioninfo;
import com.shty.collect.domain.lnk.Authors;
import com.shty.collect.domain.lnk.Certifications;
import com.shty.collect.domain.lnk.Connections;
import com.shty.collect.domain.lnk.Course;
import com.shty.collect.domain.lnk.Educations;
import com.shty.collect.domain.lnk.EducationsActivities;
import com.shty.collect.domain.lnk.Endorsements;
import com.shty.collect.domain.lnk.Experiences;
import com.shty.collect.domain.lnk.Follow_channels;
import com.shty.collect.domain.lnk.Follow_company;
import com.shty.collect.domain.lnk.Follow_people;
import com.shty.collect.domain.lnk.Follow_school;
import com.shty.collect.domain.lnk.Given;
import com.shty.collect.domain.lnk.Groups;
import com.shty.collect.domain.lnk.Honors;
import com.shty.collect.domain.lnk.ImgCode;
import com.shty.collect.domain.lnk.Interests;
import com.shty.collect.domain.lnk.Inventors;
import com.shty.collect.domain.lnk.Languages;
import com.shty.collect.domain.lnk.Organizations;
import com.shty.collect.domain.lnk.Overview;
import com.shty.collect.domain.lnk.Patents;
import com.shty.collect.domain.lnk.People;
import com.shty.collect.domain.lnk.Profile;
import com.shty.collect.domain.lnk.Projects;
import com.shty.collect.domain.lnk.Publications;
import com.shty.collect.domain.lnk.Received;
import com.shty.collect.domain.lnk.Recommenders;
import com.shty.collect.domain.lnk.ResultData;
import com.shty.collect.domain.lnk.Similiars;
import com.shty.collect.domain.lnk.Skills;
import com.shty.collect.domain.lnk.Teamer;
import com.shty.collect.domain.lnk.Testscores;
import com.shty.collect.domain.lnk.Volunteers;
import com.shty.collect.domain.lnk.Websites;
import com.shty.collect.service.lnk.AdditionalinfoService;
import com.shty.collect.service.lnk.AdditionalsService;
import com.shty.collect.service.lnk.AuthorsService;
import com.shty.collect.service.lnk.CertificationsService;
import com.shty.collect.service.lnk.ConnectionsService;
import com.shty.collect.service.lnk.CourseService;
import com.shty.collect.service.lnk.EducationsActivitiesService;
import com.shty.collect.service.lnk.EducationsAttachementsService;
import com.shty.collect.service.lnk.EducationsService;
import com.shty.collect.service.lnk.EndorsementsService;
import com.shty.collect.service.lnk.ExperiencesAttachementsService;
import com.shty.collect.service.lnk.ExperiencesService;
import com.shty.collect.service.lnk.FollowChannelsService;
import com.shty.collect.service.lnk.FollowCompanyService;
import com.shty.collect.service.lnk.FollowPeopleService;
import com.shty.collect.service.lnk.FollowSchoolService;
import com.shty.collect.service.lnk.GivenService;
import com.shty.collect.service.lnk.GroupsService;
import com.shty.collect.service.lnk.HonorsService;
import com.shty.collect.service.lnk.ImgCodeService;
import com.shty.collect.service.lnk.InterestsService;
import com.shty.collect.service.lnk.InventorsService;
import com.shty.collect.service.lnk.LanguagesService;
import com.shty.collect.service.lnk.OrganizationsService;
import com.shty.collect.service.lnk.OverviewService;
import com.shty.collect.service.lnk.PatentsService;
import com.shty.collect.service.lnk.PeopleService;
import com.shty.collect.service.lnk.ProjectsService;
import com.shty.collect.service.lnk.PublicationsService;
import com.shty.collect.service.lnk.ReceivedService;
import com.shty.collect.service.lnk.RecommendersService;
import com.shty.collect.service.lnk.SimiliarsService;
import com.shty.collect.service.lnk.SkillsService;
import com.shty.collect.service.lnk.SummaryAttachementsService;
import com.shty.collect.service.lnk.TeamerService;
import com.shty.collect.service.lnk.TestscoresService;
import com.shty.collect.service.lnk.VolunteersService;
import com.shty.collect.service.lnk.WebsitesService;
import com.shty.collect.util.AnalyseDataTool;
import com.shty.collect.util.MyUtil;

@Component
@Scope("prototype")
public class AnalyseData {

	// 设置
	private String data;

	private Map<String, String> webSitesData;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Map<String, String> getWebSitesData() {
		return webSitesData;
	}

	public void setWebSitesData(Map<String, String> webSitesData) {
		this.webSitesData = webSitesData;
	}

	public AnalyseData(String data) {
		this.data = data;
	}

	public AnalyseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ImgCodeService imgCodeService;
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private SummaryAttachementsService summaryAttachementsService;
	@Autowired
	private ExperiencesAttachementsService experiencesAttachementsService;
	@Autowired
	private SimiliarsService similiarsService;
	@Autowired
	private EducationsService educationsService;
	@Autowired
	private EducationsAttachementsService educationsAttachementsService;
	@Autowired
	private EducationsActivitiesService educationsActivitiesService;
	@Autowired
	private ExperiencesService experiencesService;
	@Autowired
	private ConnectionsService connectionsService;
	@Autowired
	private OverviewService overviewService;
	@Autowired
	private WebsitesService websitesService;
	@Autowired
	private GivenService givenService;
	@Autowired
	private ReceivedService receivedService;
	@Autowired
	private RecommendersService recommendersService;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private EndorsementsService endorsementsService;
	@Autowired
	private AdditionalinfoService additionalinfoService;
	@Autowired
	private AdditionalsService additionalsService;
	@Autowired
	private CertificationsService certificationsService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FollowChannelsService followChannelsService;
	@Autowired
	private FollowCompanyService followCompanyService;
	@Autowired
	private FollowSchoolService followSchoolService;
	@Autowired
	private FollowPeopleService followPeopleService;
	@Autowired
	private HonorsService honorsService;
	@Autowired
	private InterestsService interestsService;
	@Autowired
	private InventorsService inventorsService;
	@Autowired
	private GroupsService groupsService;
	@Autowired
	private LanguagesService languagesService;
	@Autowired
	private OrganizationsService organizationsService;
	@Autowired
	private PatentsService patentsService;
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private PublicationsService publicationsService;
	@Autowired
	private AuthorsService authorsService;
	@Autowired
	private TeamerService teamerService;
	@Autowired
	private TestscoresService testscoresService;
	@Autowired
	private VolunteersService volunteersService;

	public void analyse() {

		System.out.println("分析开始...");
		// System.out.println("待分析数据:" + data);
		AnalyseDataTool tool = new AnalyseDataTool();
		ResultData result = new ResultData();

		try {
			data = MyUtil.byteToString(data.getBytes());
			result = tool.analyse(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// PeopleShow show = peopleService.getPeopleById(Long.parseLong("15"));
		// System.out.println(show.getLnkid());

		System.out.println("Statuscode : " + result.getStatuscode());

		if (null != result && result.getStatuscode() == 200) {
			System.out.println("正在保存数据...");

			// 定义局部变量
			Long peopleId = null;
			String lnkId = null;
			Long imgCodeId = null;

			if (null != result.getProfile()) {
				Profile profile = result.getProfile();
				lnkId = profile.getLnkid();

				// 查询已采集的副本数
				int peopleCount = peopleService.countLnkid(profile.getLnkid()) + 1;

				if (peopleCount > 1) {
					System.err.println("已存在，跳过解析!");
					return;
				}

				// 保存imgCode
				if (null != profile.getImgcode()) {
					ImgCode imgCode = new ImgCode();
					try {
						imgCode.setImgname(MyUtil.imgDecodeAndSave(profile.getImgcode(), lnkId, peopleCount));
						imgCode.setImgcode(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (null != imgCode) {
						imgCodeService.addImgCode(imgCode);
						System.out.print("保存imgCode..");
						imgCodeId = imgCode.getId();
						System.out.println("imgCodeId:" + imgCodeId);
					}
				} else {
					System.out.print("imgCode为空");
				}

				// 保存people
				People people = new People();
				people.setDateCreatedTime(new Timestamp(System.currentTimeMillis()));
				people.setCollectionTime(people.getDateCreatedTime().toString());
				people.setLnkid(lnkId);
				if (null != imgCodeId) {
					people.setImgcode_id(imgCodeId);
				}
				if (null != profile.getSummary() && null != profile.getSummary().getSummary()) {
					people.setSummary(profile.getSummary().getSummary());
				}
				peopleService.addPeople(people);
				System.out.print("保存people和summary..");
				peopleId = people.getId();
				System.out.println("peopleId:" + peopleId);

				// if( null != profile.getSummary() && null !=
				// profile.getSummary().getAttachements() &&
				// profile.getSummary().getAttachements().size()>0 ){
				// System.out.print("保存summaryAttachements..");
				// summaryAttachementsService.addSumAttache(profile.getSummary().getAttachements());
				// }

				// 保存skills,endorsements
				if (null != profile.getSkills() && profile.getSkills().size() > 0) {
					List<Skills> skill_list = profile.getSkills();
					int count = 1;
					for (Skills skill : skill_list) {
						skill.setPeople_id(peopleId);
						skillsService.addSkills(skill);
						Long skillId = skill.getId();
						if (null != skill.getEndorsements() && skill.getEndorsements().size() > 0) {
							List<Endorsements> endorsements_list = skill.getEndorsements();
							for (Endorsements endorsement : endorsements_list) {
								endorsement.setSkills_id(skillId);
								endorsement.setPeople_id(peopleId);
								if (null != endorsement.getImgcode()) {
									endorsement.setImgname(MyUtil.imgDecodeAndSave(endorsement.getImgcode(), lnkId,
											count++, "endor", peopleCount));
									endorsement.setImgcode(null);
									endorsement.setFmt_headline(
											MyUtil.byteToString(endorsement.getFmt_headline().getBytes()));
								}
							}
							endorsementsService.addEndorsements(endorsements_list);
						}
					}

				} else {
					System.out.println("skills为空");
				}

				// 保存similiars
				if (null != profile.getSimiliars() && profile.getSimiliars().size() > 0) {
					List<Similiars> similiars = profile.getSimiliars();
					for (Similiars si : similiars) {
						si.setPeople_id(peopleId);
					}
					similiarsService.addSimiliars(similiars);
					System.out.print("保存similiars..");
				}

				// 保存experiences和experiencesAttachements
				if (null != profile.getExperiences() && profile.getExperiences().size() > 0) {
					List<Experiences> experiences = profile.getExperiences();
					for (Experiences exp : experiences) {
						exp.setPeople_id(peopleId);
						experiencesService.addExperiences(exp);
					}
					System.out.println("保存experiences..");
				}

				// 保存connections
				if (null != profile.getConnections() && profile.getConnections().size() > 0) {
					List<Connections> connections = profile.getConnections();
					int count = 1;
					for (Connections con : connections) {
						con.setPeople_id(peopleId);
						if (null != con.getImgcode()) {
							con.setImgname(
									MyUtil.imgDecodeAndSave(con.getImgcode(), lnkId, count++, "conn", peopleCount));
							con.setImgcode(null);
						}
					}
					connectionsService.addConnections(connections);
					System.out.println("保存connections..");
				}

				// 保存overview
				if (null != profile.getOverview()) {
					Overview overview = profile.getOverview();
					overview.setPeople_id(peopleId);
					overview.setDateCreatedTime(new Timestamp(System.currentTimeMillis()));
					overviewService.addOverview(overview);
					System.out.println("保存overview..");
					Long overviewId = overview.getId();
					if (null != webSitesData && !webSitesData.isEmpty()) {
						// List<Websites> websites_lsit = new ArrayList<>();
						Iterator<Map.Entry<String, String>> it = webSitesData.entrySet().iterator();
						Websites w = new Websites();
						while (it.hasNext()) {
							Entry<String, String> e = it.next();
							w.setWebKey(e.getKey());
							w.setWebValue(e.getValue());
							w.setOverview_id(overviewId);
							w.setPeople_id(peopleId);
							// websites_lsit.add(w);
							websitesService.addWebsites(w);
						}

						System.out.println("保存websites..");
					}
				}

				// 保存educations
				if (null != profile.getEducations() && profile.getEducations().size() > 0) {
					List<Educations> educations = profile.getEducations();
					for (Educations edu : educations) {
						edu.setPeople_id(peopleId);
						edu.setDateCreatedTime(new Timestamp(System.currentTimeMillis()));

						int eduId = educationsService.addEducations(edu);
						List<EducationsActivities> list = edu.getActivities();
						if (null != list && list.size() > 0) {
							for (EducationsActivities educationsActivities : list) {
								educationsActivities.setDateCreatedTime(new Timestamp(System.currentTimeMillis()));
								educationsActivities.setDateUpdatedTime(new Timestamp(System.currentTimeMillis()));
								educationsActivities.setEducations_id(edu.getId());
							}
							educationsActivitiesService.addEducationsActivities(list);
						}
					}
					System.out.println("保存educations..");
				}

				// 保存given
				if (null != profile.getRecommendations() && null != profile.getRecommendations().getGiven()
						&& profile.getRecommendations().getGiven().size() > 0) {
					List<Given> given = profile.getRecommendations().getGiven();
					int count = 1;
					for (Given g : given) {
						g.setPeople_id(peopleId);
						if (null != g.getImgcode()) {
							g.setImgname(MyUtil.imgDecodeAndSave(g.getImgcode(), lnkId, count++, "given", peopleCount));
							g.setImgcode(null);
						}
					}
					givenService.addGiven(given);
					System.out.println("保存given..");
				}

				// 保存receive
				if (null != profile.getRecommendations() && null != profile.getRecommendations().getRecieved()
						&& profile.getRecommendations().getRecieved().size() > 0) {
					List<Received> receive = profile.getRecommendations().getRecieved();
					int count = 1;
					System.out.println("保存receive..");
					for (Received r : receive) {
						r.setPeople_id(peopleId);
						receivedService.addReceived(r);
						Long reveicedId = r.getId();
						if (null != r.getRecommenders() && r.getRecommenders().size() > 0) {
							List<Recommenders> recommenders = r.getRecommenders();
							// System.out.println("recommenders.size:"+recommenders.size());
							for (Recommenders re : recommenders) {
								re.setRecommendationreceived_id(reveicedId);
								re.setPeople_id(peopleId);
								if (null != re.getImgcode()) {
									re.setImgname(MyUtil.imgDecodeAndSave(re.getImgcode(), lnkId, count++,
											"recommender", peopleCount));
									re.setImgcode(null);
								}
							}
							recommendersService.addRecommenders(recommenders);
						}
					}
				}

				// 保存follow
				if (null != profile.getFollows() && null != profile.getFollows().getFollow_people()
						&& profile.getFollows().getFollow_people().size() > 0) {
					List<Follow_people> follow_people = profile.getFollows().getFollow_people();
					int count = 1;
					for (Follow_people fp : follow_people) {
						fp.setPeople_id(peopleId);
						if (null != fp.getImgcode()) {
							fp.setImgname(MyUtil.imgDecodeAndSave(fp.getImgcode(), lnkId, count++, "follow_people",
									peopleCount));
							fp.setImgcode(null);
						}
					}
					followPeopleService.addFollowPeople(follow_people);
					System.out.println("保存follow_people");
				}

				if (null != profile.getFollows() && null != profile.getFollows().getFollow_school()
						&& profile.getFollows().getFollow_school().size() > 0) {
					List<Follow_school> follow_school = profile.getFollows().getFollow_school();
					for (Follow_school fs : follow_school) {
						fs.setPeople_id(peopleId);
					}
					followSchoolService.addFollowSchool(follow_school);
					System.out.println("保存follow_school");
				}

				if (null != profile.getFollows() && null != profile.getFollows().getFollow_channels()
						&& profile.getFollows().getFollow_channels().size() > 0) {
					List<Follow_channels> follow_channels = profile.getFollows().getFollow_channels();
					for (Follow_channels fc : follow_channels) {
						fc.setPeople_id(peopleId);
					}
					followChannelsService.addFollowChannels(follow_channels);
					System.out.println("保存follow_channels");
				}

				if (null != profile.getFollows() && null != profile.getFollows().getFollow_company()
						&& profile.getFollows().getFollow_company().size() > 0) {
					List<Follow_company> follow_company = profile.getFollows().getFollow_company();
					for (Follow_company fcp : follow_company) {
						fcp.setPeople_id(peopleId);
					}
					followCompanyService.addFollowCompany(follow_company);
					System.out.println("保存follow_company");
				}

				// 保存Volunteers && interests
				if (null != profile.getVolunteering() && null != profile.getVolunteering().getVolunteers()
						&& profile.getVolunteering().getVolunteers().size() > 0) {
					List<Volunteers> volunteers = profile.getVolunteering().getVolunteers();
					for (Volunteers v : volunteers) {
						v.setPeople_id(peopleId);
					}
					volunteersService.addVolunteers(volunteers);
					System.out.println("保存volunteers");
				}
				if (null != profile.getVolunteering() && null != profile.getVolunteering().getInterests()
						&& profile.getVolunteering().getInterests().size() > 0) {
					List<Interests> interests = profile.getVolunteering().getInterests();
					for (Interests i : interests) {
						i.setPeople_id(peopleId);
					}
					interestsService.addInterests(interests);
					System.out.println("保存interests");
				}

				// 保存languages
				if (null != profile.getLanguages() && profile.getLanguages().size() > 0) {
					List<Languages> languages = profile.getLanguages();
					for (Languages l : languages) {
						l.setPeople_id(peopleId);
					}
					languagesService.addLanguages(languages);
					System.out.println("保存languages");
				}

				// 保存groups
				if (null != profile.getGroups() && profile.getGroups().size() > 0) {
					List<Groups> groups = profile.getGroups();
					for (Groups g : groups) {
						g.setPeople_id(peopleId);
					}
					groupsService.addGroups(groups);
					System.out.println("保存groups");
				}

				// 保存certifications
				if (null != profile.getCertifications() && profile.getCertifications().size() > 0) {
					List<Certifications> certifications = profile.getCertifications();
					for (Certifications c : certifications) {
						c.setPeople_id(peopleId);
					}
					certificationsService.addCertifications(certifications);
					System.out.println("保存certifications");
				}

				// 保存organizations,additionals
				if (null != profile.getOrganizations() && null != profile.getOrganizations().getOrganizations()
						&& profile.getOrganizations().getOrganizations().size() > 0) {
					List<Organizations> organizations = profile.getOrganizations().getOrganizations();
					for (Organizations o : organizations) {
						o.setPeople_id(peopleId);
					}
					organizationsService.addOrganizations(organizations);
					System.out.println("保存organizations");
				}
				if (null != profile.getOrganizations() && null != profile.getOrganizations().getAdditionals()) {
					Additionals additionals = profile.getOrganizations().getAdditionals();
					additionals.setPeople_id(peopleId);
					additionalsService.addAdditionals(additionals);
					System.out.println("保存additionals");
				}

				// 保存honors
				if (null != profile.getHonors() && null != profile.getHonors().getHonors()
						&& profile.getHonors().getHonors().size() > 0) {
					List<Honors> honors = profile.getHonors().getHonors();
					for (Honors h : honors) {
						h.setPeople_id(peopleId);
					}
					honorsService.addHonors(honors);
					System.out.println("保存honors");
				}

				// 保存projects
				if (null != profile.getProjects() && profile.getProjects().size() > 0) {
					List<Projects> projects = profile.getProjects();
					int count = 1;
					System.out.println("保存projects");
					for (Projects p : projects) {
						p.setPeople_id(peopleId);
						projectsService.addProjects(p);
						Long projectId = p.getId();
						if (null != p.getTeamer() && p.getTeamer().size() > 0) {
							List<Teamer> teamer = p.getTeamer();
							for (Teamer t : teamer) {
								t.setProjects_id(projectId);
								t.setPeople_id(peopleId);
								if (null != t.getImgcode()) {
									t.setImgname(MyUtil.imgDecodeAndSave(t.getImgcode(), lnkId, count++, "teamer",
											peopleCount));
									t.setImgcode(null);
								}
							}
							teamerService.addTeamer(teamer);
						}
					}
				}

				// 保存additionalinfo
				if (null != profile.getAdditionalinfo() && null != profile.getAdditionalinfo().getAdditioninfo()
						&& profile.getAdditionalinfo().getAdditioninfo().size() > 0) {
					List<Additioninfo> additioninfo = profile.getAdditionalinfo().getAdditioninfo();
					for (Additioninfo a : additioninfo) {
						a.setPeople_id(peopleId);
					}
					additionalinfoService.addAdditionalinfo(additioninfo);
					System.out.println("保存additionalinfo");
				}

				// 保存testscores
				if (null != profile.getTestscores() && profile.getTestscores().size() > 0) {
					List<Testscores> testscores = profile.getTestscores();
					for (Testscores t : testscores) {
						t.setPeople_id(peopleId);
					}
					testscoresService.addTestscores(testscores);
					System.out.println("保存testscores");
				}

				// 保存publications
				if (null != profile.getPublications() && profile.getPublications().size() > 0) {
					List<Publications> publications = profile.getPublications();
					int count = 1;
					System.out.println("保存publications");
					for (Publications p : publications) {
						p.setPeople_id(peopleId);
						publicationsService.addPublications(p);
						Long pubId = p.getId();
						if (null != p.getAuthors() && p.getAuthors().size() > 0) {
							List<Authors> authors = p.getAuthors();
							for (Authors a : authors) {
								a.setPublications_id(pubId);
								a.setPeople_id(peopleId);
								if (null != a.getImgcode()) {
									a.setImgname(MyUtil.imgDecodeAndSave(a.getImgcode(), lnkId, count++, "authors",
											peopleCount));
									a.setImgcode(null);
								}
							}
							authorsService.addAuthors(authors);
						}
					}
				}

				// 保存patents
				if (null != profile.getPatents() && profile.getPatents().size() > 0) {
					List<Patents> patents = profile.getPatents();
					int count = 1;
					System.out.println("保存patents");
					for (Patents p : patents) {
						p.setPeople_id(peopleId);
						patentsService.addPatents(p);
						Long patId = p.getId();
						if (null != p.getInventors() && p.getInventors().size() > 0) {
							List<Inventors> inventors = p.getInventors();
							for (Inventors i : inventors) {
								i.setPatents_id(patId);
								i.setPeople_id(peopleId);
								if (null != i.getImgcode()) {
									i.setImgname(MyUtil.imgDecodeAndSave(i.getImgcode(), lnkId, count++, "inventors",
											peopleCount));
									i.setImgcode(null);
								}
							}
							inventorsService.addInventors(inventors);
						}
					}
				}

				// 保存course
				if (null != profile.getCourse() && profile.getCourse().size() > 0) {
					List<Course> course = profile.getCourse();
					for (Course c : course) {
						c.setPeople_id(peopleId);
					}
					courseService.addCourse(course);
					System.out.println("保存course");
				}

				System.out.println("保存解析数据结束！！！");

			}

		} else {
			System.out.println("result为空或者状态码不为200");
		}
	}
}