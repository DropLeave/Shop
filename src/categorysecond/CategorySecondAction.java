package categorysecond;

import java.util.List;

import utils.PageBean;
import category.Category;
import category.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	//����page��ǰҳ
	private Integer page;
	//ע��CategorySecondService
	private CategorySecondService categorySecondService;
	//ע��CategoryService
	private CategoryService categoryService;
	//����cid
	private Integer cid;
	//ģ������
	private CategorySecond categorySecond=new CategorySecond();
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/*
	 * ��ѯ���ж�������
	 */
	public String adminFindAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	/*
	 * ���Ӷ�������(ҳ���ת)
	 */
	public String addPage(){
		//��ѯһ������
		List<Category> categoryList=categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "addPageSuccess";
	}
	/*
	 * ���Ӷ�������(����)
	 */
	public String save(){
		Category category=new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
}
