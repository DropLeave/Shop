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
	 * ��̨��ѯ����һ������
	 */
	public String adminFindAll(){
		List<Category> adminCategoryList=categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("adminCategoryList", adminCategoryList);
		return "adminFindAllSuccess";
	}

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	/*
	 * ��̨����һ������
	 */
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	/*
	 *��̨ɾ��һ������
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	/*
	 * ��̨�޸�һ������(��ѯ)
	 * 
	 */
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "findByCidSuccess";
	}
	/*
	 * ��̨�޸�һ������(�޸�)
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
