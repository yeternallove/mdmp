/**
 * 版权所有：美创科技
 * 项目名称:mdmp
 * 创建者: hzmc
 * 创建日期: 2017年2月15日
 * 文件说明:
 * 最近修改者：hzmc
 * 最近修改日期：2017年2月15日
 */
package com.eternallove.mdmp.model.test.viewright;


/**
 * 视图权限表
 * 表达用户和视图的关联关系
 * @author hzmc
 *
 */
public class ViewRightQuery {
	//容器
	private String mdCluster;
	//模型
	private String mdModel;
	//视图
	private String mdView;
	
	public ViewRightQuery() {
		super();
	}

	/**
	 * @param mdCluster
	 * @param mdModel
	 * @param mdView
	 */
	public ViewRightQuery(String mdCluster, String mdModel, String mdView) {
		super();
		this.mdCluster = mdCluster;
		this.mdModel = mdModel;
		this.mdView = mdView;
	}

	public String getMdCluster() {
		return mdCluster;
	}
	
	public void setMdCluster(String mdCluster) {
		this.mdCluster = mdCluster;
	}
	
	public String getMdModel() {
		return mdModel;
	}
	
	public void setMdModel(String mdModel) {
		this.mdModel = mdModel;
	}
	
	public String getMdView() {
		return mdView;
	}
	
	public void setMdView(String mdView) {
		this.mdView = mdView;
	}

}
