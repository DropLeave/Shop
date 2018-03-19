package category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private CategoryService categoryService;
	private Category category=new Category();
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/*
	 * 后台查询所有一级分类
	 */
	public String adminFindAll(){
		List<Category> adminCategoryList=categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("adminCategoryList", adminCategoryList);
		return "adminFindAllSuccess";
	}

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	/*
	 * 后台保存一级分类
	 */
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	/*
	 *后台删除一级分类
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	/*
	 * 后台修改一级分类(查询)
	 * 
	 */
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "findByCidSuccess";
	}
	/*
	 * 后台修改一级分类(修改)
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
