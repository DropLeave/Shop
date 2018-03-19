package categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import utils.PageBean;

@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		
		return categorySecondDao.findBypage(page);
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		
		return categorySecondDao.findAll();
	}
	
}
