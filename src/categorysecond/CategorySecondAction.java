package categorysecond;

import java.util.List;

import utils.PageBean;
import category.Category;
import category.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	//接收page当前页
	private Integer page;
	//注入CategorySecondService
	private CategorySecondService categorySecondService;
	//注入CategoryService
	private CategoryService categoryService;
	//接收cid
	private Integer cid;
	//模型驱动
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
	 * 查询所有二级分类
	 */
	public String adminFindAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	/*
	 * 增加二级分类(页面调转)
	 */
	public String addPage(){
		//查询一级分类
		List<Category> categoryList=categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "addPageSuccess";
	}
	/*
	 * 增加二级分类(保存)
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
