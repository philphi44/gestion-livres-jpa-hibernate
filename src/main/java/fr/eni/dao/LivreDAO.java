package fr.eni.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import fr.eni.bean.Livre;
import fr.eni.exception.DAOException;

public class LivreDAO {

	public void add(Livre livre) throws DAOException {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(livre);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw new DAOException("Erreur lors de l'ajout du livre " + livre + " : " + e.getMessage());
		}
	}

	

	public void delete(Livre livre) throws DAOException {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		livre = em.find(Livre.class, livre.getId());
		try {
			em.remove(livre);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw new DAOException("Erreur lors de la suppression du livre " + livre + " : " + e.getMessage());
		}
	}

	public void update(Livre livre) throws DAOException {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(livre);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw new DAOException("Erreur lors de la modification du livre " + livre + " : " + e.getMessage());
		}
	}

	public void delete(int id) throws DAOException {
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Livre livre = em.find(Livre.class, id);
		try {
			em.remove(livre);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw new DAOException("Erreur lors de la suppression du livre " + livre + " : " + e.getMessage());
		}
	}

	public Livre findById(int val) {
		return DAOUtil.getEntityManager().find(Livre.class, val);
	}

	public List<Livre> findAll() {
		String req = "Select Object(l) from Livre l";
		return DAOUtil.getEntityManager().createQuery(req, Livre.class).getResultList();
	}

	public List<Livre> findAllOrderByTitreAsc() {
		String req = "Select Object(l) from Livre l Order By l.titre ASC";
		return DAOUtil.getEntityManager().createQuery(req, Livre.class).getResultList();
	}

	public List<Livre> findAllOrderByTitreDesc() {
		String req = "Select Object(l) from Livre l Order By l.titre DESC";
		return DAOUtil.getEntityManager().createQuery(req, Livre.class).getResultList();
	}

	public List<Livre> findByAuteurLike(String auteur) {
		String req = "Select Object(l) from Livre l WHERE l.auteur LIKE :aut";
		return DAOUtil.getEntityManager().createQuery(req, Livre.class).setParameter("aut", "%" + auteur + "%")
				.getResultList();
	}

	public int getMaxId() {
		String req = "Select max(l.id) from Livre l";
		return DAOUtil.getEntityManager().createQuery(req, Integer.class).getSingleResult();
	}

	public int getMinId() {
		String req = "Select min(l.id) from Livre l";
		return DAOUtil.getEntityManager().createQuery(req, Integer.class).getSingleResult();
	}

}
