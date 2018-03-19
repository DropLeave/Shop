package category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {
	CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		//查询所有一级分类
		return categoryDao.findAll();
	}
	//保存一级分类
	public void save(Category category) {
		
		categoryDao.save(category);
	}
//删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
//查询一级分类
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
		
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
