package product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		
		return productDao.findHot();
	}

	public List<Product> findNew() {
		
		return productDao.findNew();
	}

	public PageBean<Product> findProductByPage(Integer cid, Integer page) {
		
		
		
		return productDao.findProductByPage(cid,page);
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
		
	}

	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		
		return productDao.findByCsid(csid,page);
	}

	public PageBean<Product> findByPage(Integer page) {
		
		return productDao.findByPage(page);
	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	
}
