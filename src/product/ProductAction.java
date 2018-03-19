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
	//�ļ��ϴ�����������
	private File upload;//�ϴ����ļ�
	private String uploadContentType;//�ϴ��ļ�������
	private String uploadFileName;//�ϴ��ļ�������
	//����cid
	private Integer cid;
	//����csid
	private Integer csid;
	//���յ�ǰҳ��
	private Integer page;
	//ע��һ������Service
	private CategoryService categoryService;
	//ע��productService
	private ProductService productService;
	//ע���������Service
	private CategorySecondService categorySecondService;
	//ģ������
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
	//ͨ��cid��ѯ��Ʒ
	public String findByCid(){
		//��ѯһ�������µĶ������ࣺ����ѯһ�����࣬һ����������������ѹ���
		List<Category>categoryList= categoryService.findAll();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		//��ѯ��Ʒ
		PageBean<Product>pageBean=productService.findProductByPage(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCidSuccess";
	}
	public String findByPid(){
		//��ѯһ�������µĶ������ࣺ����ѯһ�����࣬һ����������������ѹ���
		List<Category>categoryList= categoryService.findAll();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product=productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	public String findByCsid(){
		//��ѯһ�������µĶ������ࣺ����ѯһ�����࣬һ����������������ѹ���
		List<Category>categoryList= categoryService.findAll();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		//��ѯ��Ʒ
		PageBean<Product>pageBean=productService.findByCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsidSuccess";
	}
	//��̨��ѯ������Ʒ
	public String adminFindAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	/*
	 *��̨ �����Ʒ����(��תҳ��)
	 */
	public String addPage(){
		//��ѯ���еĶ�������
		List<CategorySecond> categorySecondList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "addPageSuccess";
		
	}
	/*
	 * ��̨��������Ʒ
	 */
	public String save() throws IOException{
		//�ļ�����
		//��ȡ�ϴ�·��
		String path=ServletActionContext.getServletContext().getRealPath("/products");
		System.out.println(path);
		String realPath=path+"/"+csid+"/"+uploadFileName;
		
		File diskFile=new File(realPath);
		//�ϴ��ļ�
		FileUtils.copyFile(upload, diskFile);
		CategorySecond categorySecond=new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		product.setPdate(new Date());
		product.setImage("products"+"/"+csid+"/"+uploadFileName);
		
		//������Ʒ
		productService.save(product);
		return "saveSuccess";
	}
}
