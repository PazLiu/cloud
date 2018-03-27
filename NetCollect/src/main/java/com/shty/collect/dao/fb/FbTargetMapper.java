package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.FbTarget;

public interface FbTargetMapper {
	public FbTarget getFbTarget_urlpattern(String urlpattern);
	public int update_FbTarget_urlpattern(FbTarget fbTarget);
	public int insert_FbTarget_urlpattern(FbTarget fbTarget);
	public int update_FbTarget_friendsurl(FbTarget fbTarget);
	public int update_FbTarget_photoUrl(FbTarget fbTarget);
}
