package model.services;


import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;

import model.entities.Seller;

public class SellerService {
	
	private SellerDao dao = DaoFactory.createSellerDao();	
	
	public List<Seller> findall(){
		return dao.findAll();
		/*	Dados mocados:
	 * List<Departament> list = new ArrayList<>();
		list.add(new Departament(1,"Books"));
		list.add(new Departament(2,"Computers"));
		list.add(new Departament(3,"Eletronics"));
		return list; */
	}
	public void saveOrUpdate(Seller obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}

}
