package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
   public User userCar(String model, int series) {
      String hql = "SELECT user FROM User user JOIN user.car car WHERE car.model = :model AND car.series = :series";
      try (Session session = sessionFactory.openSession()) {
         Query query = session.createQuery(hql);
         query.setParameter("model", model);
         query.setParameter("series", series);

         return (User) query.getSingleResult();
      }
   }
}


