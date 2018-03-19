package index;

import java.util.List;

import product.Product;
import product.ProductService;
import category.Category;
import category.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 首页访问的action
 */
public class IndexAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//注入一级分类Service
	private CategoryService categoryService;
	//注入商品Service
	private ProductService productService;
	//定义热门商品集合
	private List<Product> hotList;
	//定义最新商品集合
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

	//执行首页访问的方法
	@Override
	public String execute() throws Exception {
		//查询所有一级分类
		List<Category> categoryList= categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//查询热门商品
		hotList= productService.findHot();
		//查询最新商品
		newList=productService.findNew();
		return "indexSuccess";
	}
	
}
