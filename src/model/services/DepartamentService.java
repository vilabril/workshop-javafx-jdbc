package model.services;


import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import model.entities.Department;

public class DepartamentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();	
	
	public List<Department> findall(){
		return dao.findAll();
		/*	Dados mocados:
	 * List<Departament> list = new ArrayList<>();
		list.add(new Departament(1,"Books"));
		list.add(new Departament(2,"Computers"));
		list.add(new Departament(3,"Eletronics"));
		return list; */
	}
	public void saveOrUpdate(Department obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}

}
