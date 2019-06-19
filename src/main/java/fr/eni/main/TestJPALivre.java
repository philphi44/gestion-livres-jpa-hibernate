
	package fr.eni.main;
	
	import java.util.List;
	import fr.eni.bean.Livre;
	import fr.eni.dao.DAOUtil;
	import fr.eni.dao.LivreDAO;
	import fr.eni.exception.DAOException;
	
	public class TestJPALivre {
	
		public static void main(String[] args) {
			Livre l1 = new Livre("La bête humaine", "Emile Zola", 450);
			Livre l2 = new Livre("Huis clos", "JP Sartre", 350);
			Livre l3 = new Livre("L'étranger", "Albert Camus", 476);
			Livre l4 = new Livre("La Peste", "Albert Camus", 276);
			
			LivreDAO livreDao = new LivreDAO();
			
			System.out.println("\nCréation de 4 livres ... ");
			try {
				livreDao.add(l1);
				livreDao.add(l2);
				livreDao.add(l3);
				livreDao.add(l4);
			} catch (DAOException e) {
				System.out.println(e.getMessage());
			}
			
			List<Livre> liste = livreDao.findAll();
		
			affiche("Liste des livres :", liste);
			affiche("Liste des livres triés par titre ascendant : ", livreDao.findAllOrderByTitreAsc());
			affiche("Liste des livres triés par titre descendant : ", livreDao.findAllOrderByTitreDesc());
			affiche("Liste des livres de Camus : ", livreDao.findByAuteurLike("Camus"));
			System.out.println("\npremier id : " + livreDao.getMinId());
			System.out.println("\ndernier id : " + livreDao.getMaxId());
			
			System.out.println("\nlivre id = 2 : " + livreDao.findById(2));
			System.out.println("\nlivre id = 22 : " + livreDao.findById(22));
			
			//suppression de l1
			try {
				livreDao.delete(l1);
			} catch (DAOException e) {
				System.out.println(e.getMessage());
			}
			//suppression de id = 3
			try {
				livreDao.delete(3);
			} catch (DAOException e) {
				System.out.println(e.getMessage());
			}
			// mise à jour de l2
			l2.setAuteur("Jean-Paul Sartre");
			l2.setNbPages(354);
			
			
			try {
				livreDao.update(l2);
			} catch (DAOException e) {
				System.out.println(e.getMessage());
			}
			
			affiche("Liste des livres apres suppression et modification :", livreDao.findAll());
		
			DAOUtil.close();
			
		}
	
		private static void affiche(String msg, List<Livre> liste) {
			System.out.println();
			System.out.println(msg);
			for (Livre livre : liste) {
				System.out.println(livre);
			}
		}
	
	}
