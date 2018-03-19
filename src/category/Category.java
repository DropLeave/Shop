package category;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import categorysecond.CategorySecond;



/**
 * 一级分类的实体类
 * @author Administrator
 *
 */
public class Category {
	private Integer cid;
	private String cname;
	private String ishot;
	private Date createtime;
	private String attribute1;
	private String attribute2;
	private Integer attribute3;
	private Date attribute4;
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getIshot() {
		return ishot;
	}
	public void setIshot(String ishot) {
		this.ishot = ishot;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public Integer getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}
	public Date getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(Date attribute4) {
		this.attribute4 = attribute4;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
