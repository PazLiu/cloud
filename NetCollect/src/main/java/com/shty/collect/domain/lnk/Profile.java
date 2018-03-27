package com.shty.collect.domain.lnk;

import java.util.List;

public class Profile {

	private Long id;

	private String lnkid;

	private Recommendations recommendations;

	private List<Similiars> similiars;

	private List<Connections> connections;

	private List<Skills> skills;

	private Overview overview;

	private Summary summary;

	private List<Experiences> experiences;

	private List<Educations> educations;

	private String imgcode;

	private String imgname;

	private List<Groups> groups;

	private Follows follows;

	private Volunteering volunteering;

	private List<Languages> languages;

	private List<Certifications> certifications;

	private OrganizationsOut organizations;

	private HonorsOut honors;

	private List<Projects> projects;

	private Additionalinfo additionalinfo;

	private List<Testscores> testscores;

	private List<Publications> publications;

	private List<Patents> patents;

	private List<Course> course;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the lnkid
	 */
	public String getLnkid() {
		return lnkid;
	}

	/**
	 * @param lnkid
	 *            the lnkid to set
	 */
	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	/**
	 * @return the recommendations
	 */
	public Recommendations getRecommendations() {
		return recommendations;
	}

	/**
	 * @param recommendations
	 *            the recommendations to set
	 */
	public void setRecommendations(Recommendations recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @return the similiars
	 */
	public List<Similiars> getSimiliars() {
		return similiars;
	}

	/**
	 * @param similiars
	 *            the similiars to set
	 */
	public void setSimiliars(List<Similiars> similiars) {
		this.similiars = similiars;
	}

	/**
	 * @return the connections
	 */
	public List<Connections> getConnections() {
		return connections;
	}

	/**
	 * @param connections
	 *            the connections to set
	 */
	public void setConnections(List<Connections> connections) {
		this.connections = connections;
	}

	/**
	 * @return the skills
	 */
	public List<Skills> getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            the skills to set
	 */
	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	/**
	 * @return the overview
	 */
	public Overview getOverview() {
		return overview;
	}

	/**
	 * @param overview
	 *            the overview to set
	 */
	public void setOverview(Overview overview) {
		this.overview = overview;
	}

	/**
	 * @return the summary
	 */
	public Summary getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	/**
	 * @return the experiences
	 */
	public List<Experiences> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences
	 *            the experiences to set
	 */
	public void setExperiences(List<Experiences> experiences) {
		this.experiences = experiences;
	}

	/**
	 * @return the educations
	 */
	public List<Educations> getEducations() {
		return educations;
	}

	/**
	 * @param educations
	 *            the educations to set
	 */
	public void setEducations(List<Educations> educations) {
		this.educations = educations;
	}

	/**
	 * @return the imgcode
	 */
	public String getImgcode() {
		return imgcode;
	}

	/**
	 * @param imgcode
	 *            the imgcode to set
	 */
	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

	/**
	 * @return the imgname
	 */
	public String getImgname() {
		return imgname;
	}

	/**
	 * @param imgname
	 *            the imgname to set
	 */
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	/**
	 * @return the groups
	 */
	public List<Groups> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}

	/**
	 * @return the follows
	 */
	public Follows getFollows() {
		return follows;
	}

	/**
	 * @param follows
	 *            the follows to set
	 */
	public void setFollows(Follows follows) {
		this.follows = follows;
	}

	/**
	 * @return the volunteering
	 */
	public Volunteering getVolunteering() {
		return volunteering;
	}

	/**
	 * @param volunteering
	 *            the volunteering to set
	 */
	public void setVolunteering(Volunteering volunteering) {
		this.volunteering = volunteering;
	}

	/**
	 * @return the languages
	 */
	public List<Languages> getLanguages() {
		return languages;
	}

	/**
	 * @param languages
	 *            the languages to set
	 */
	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}

	/**
	 * @return the certifications
	 */
	public List<Certifications> getCertifications() {
		return certifications;
	}

	/**
	 * @param certifications
	 *            the certifications to set
	 */
	public void setCertifications(List<Certifications> certifications) {
		this.certifications = certifications;
	}

	/**
	 * @return the organizations
	 */
	public OrganizationsOut getOrganizations() {
		return organizations;
	}

	/**
	 * @param organizations
	 *            the organizations to set
	 */
	public void setOrganizations(OrganizationsOut organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the honors
	 */
	public HonorsOut getHonors() {
		return honors;
	}

	/**
	 * @param honors
	 *            the honors to set
	 */
	public void setHonors(HonorsOut honors) {
		this.honors = honors;
	}

	/**
	 * @return the projects
	 */
	public List<Projects> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}

	/**
	 * @return the additionalinfo
	 */
	public Additionalinfo getAdditionalinfo() {
		return additionalinfo;
	}

	/**
	 * @param additionalinfo
	 *            the additionalinfo to set
	 */
	public void setAdditionalinfo(Additionalinfo additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	/**
	 * @return the testscores
	 */
	public List<Testscores> getTestscores() {
		return testscores;
	}

	/**
	 * @param testscores
	 *            the testscores to set
	 */
	public void setTestscores(List<Testscores> testscores) {
		this.testscores = testscores;
	}

	/**
	 * @return the publications
	 */
	public List<Publications> getPublications() {
		return publications;
	}

	/**
	 * @param publications
	 *            the publications to set
	 */
	public void setPublications(List<Publications> publications) {
		this.publications = publications;
	}

	/**
	 * @return the patents
	 */
	public List<Patents> getPatents() {
		return patents;
	}

	/**
	 * @param patents
	 *            the patents to set
	 */
	public void setPatents(List<Patents> patents) {
		this.patents = patents;
	}

	/**
	 * @return the course
	 */
	public List<Course> getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(List<Course> course) {
		this.course = course;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [id=");
		builder.append(id);
		builder.append(", lnkid=");
		builder.append(lnkid);
		builder.append(", recommendations=");
		builder.append(recommendations);
		builder.append(", similiars=");
		builder.append(similiars);
		builder.append(", connections=");
		builder.append(connections);
		builder.append(", skills=");
		builder.append(skills);
		builder.append(", overview=");
		builder.append(overview);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", experiences=");
		builder.append(experiences);
		builder.append(", educations=");
		builder.append(educations);
		builder.append(", imgcode=");
		builder.append(imgcode);
		builder.append(", imgname=");
		builder.append(imgname);
		builder.append(", groups=");
		builder.append(groups);
		builder.append(", follows=");
		builder.append(follows);
		builder.append(", volunteering=");
		builder.append(volunteering);
		builder.append(", languages=");
		builder.append(languages);
		builder.append(", certifications=");
		builder.append(certifications);
		builder.append(", organizations=");
		builder.append(organizations);
		builder.append(", honors=");
		builder.append(honors);
		builder.append(", projects=");
		builder.append(projects);
		builder.append(", additionalinfo=");
		builder.append(additionalinfo);
		builder.append(", testscores=");
		builder.append(testscores);
		builder.append(", publications=");
		builder.append(publications);
		builder.append(", patents=");
		builder.append(patents);
		builder.append(", course=");
		builder.append(course);
		builder.append("]");
		return builder.toString();
	}

}
