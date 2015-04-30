package com.just.lollipop.bbs.web.base;


public abstract class AbstractAjaxAction extends AbstractAction{

	private static final long serialVersionUID = -1849125108105910060L;
	private String result;
	
	@Override
	public String execute() throws Exception {
		this.result = setResult();
		return Constants.JSON;
	}
	
	protected abstract String setResult();
	
	public String getResult(){
		return result;
	}
}
