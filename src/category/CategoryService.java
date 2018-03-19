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
		//��ѯ����һ������
		return categoryDao.findAll();
	}
	//����һ������
	public void save(Category category) {
		
		categoryDao.save(category);
	}
//ɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
//��ѯһ������
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
		
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
