package utils;

import java.util.List;

/**
 * �洢��ҳ�������
 * @author Administrator
 *
 */
public class PageBean<T> {
	private Integer page;//��ǰҳ��
	private Integer limit;//ÿҳ��ʾ�ļ�¼��
	private Integer totalCount;//�ܼ�¼��
	private Integer totalPage;//��ҳ��
	private List<T>list;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
