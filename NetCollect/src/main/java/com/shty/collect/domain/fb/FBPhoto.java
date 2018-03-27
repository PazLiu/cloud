package com.shty.collect.domain.fb;

public class FBPhoto {
	private Long id;
	private String imgCode;
	private String imgUrl;
	private Long FBTarget_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getFBTarget_id() {
		return FBTarget_id;
	}
	public void setFBTarget_id(Long fBTarget_id) {
		FBTarget_id = fBTarget_id;
	}
	
	
}
