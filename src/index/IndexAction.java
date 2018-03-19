package index;

import java.util.List;

import product.Product;
import product.ProductService;
import category.Category;
import category.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * ��ҳ���ʵ�action
 */
public class IndexAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ע��һ������Service
	private CategoryService categoryService;
	//ע����ƷService
	private ProductService productService;
	//����������Ʒ����
	private List<Product> hotList;
	//����������Ʒ����
	private List<Product>newList;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public List<Product> getHotList() {
		return hotList;
	}
	
	public List<Product> getNewList() {
		return newList;
	}

	//ִ����ҳ���ʵķ���
	@Override
	public String execute() throws Exception {
		//��ѯ����һ������
		List<Category> categoryList= categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//��ѯ������Ʒ
		hotList= productService.findHot();
		//��ѯ������Ʒ
		newList=productService.findNew();
		return "indexSuccess";
	}
	
}
