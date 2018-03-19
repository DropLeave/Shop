package product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import utils.PageBean;
import category.Category;
import category.CategoryService;
import categorysecond.CategorySecond;
import categorysecond.CategorySecondService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//文件上传的三个属性
	private File upload;//上传的文件
	private String uploadContentType;//上传文件的类型
	private String uploadFileName;//上传文件的名称
	//接收cid
	private Integer cid;
	//接收csid
	private Integer csid;
	//接收当前页数
	private Integer page;
	//注入一级分类Service
	private CategoryService categoryService;
	//注入productService
	private ProductService productService;
	//注入二级分类Service
	private CategorySecondService categorySecondService;
	//模型驱动
	private Product product=new Product();
	
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	public Integer getCsid() {
		return csid;
	}
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	//通过cid查询商品
	public String findByCid(){
		//查询一级分类下的二级分类：即查询一级分类，一级分类与二级分类已关联
		List<Category>categoryList= categoryService.findAll();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		//查询商品
		PageBean<Product>pageBean=productService.findProductByPage(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCidSuccess";
	}
	public String findByPid(){
		//查询一级分类下的二级分类：即查询一级分类，一级分类与二级分类已关联
		List<Category>categoryList= categoryService.findAll();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product=productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	public String findByCsid(){
		//查询一级分类下的二级分类：即查询一级分类，一级分类与二级分类已关联
		List<Category>categoryList= categoryService.findAll();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		//查询商品
		PageBean<Product>pageBean=productService.findByCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsidSuccess";
	}
	//后台查询所有商品
	public String adminFindAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	/*
	 *后台 添加商品功能(跳转页面)
	 */
	public String addPage(){
		//查询所有的二级分类
		List<CategorySecond> categorySecondList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "addPageSuccess";
		
	}
	/*
	 * 后台：保存商品
	 */
	public String save() throws IOException{
		//文件操作
		//获取上传路径
		String path=ServletActionContext.getServletContext().getRealPath("/products");
		System.out.println(path);
		String realPath=path+"/"+csid+"/"+uploadFileName;
		
		File diskFile=new File(realPath);
		//上传文件
		FileUtils.copyFile(upload, diskFile);
		CategorySecond categorySecond=new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		product.setPdate(new Date());
		product.setImage("products"+"/"+csid+"/"+uploadFileName);
		
		//保存商品
		productService.save(product);
		return "saveSuccess";
	}
}
