package pl.rabowski.jaxrs.dao;


import pl.rabowski.jaxrs.model.Friend;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

//@ApplicationScoped
public class FriendDao{

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Transactional
    public List<Friend> getFriends(){
        List<Friend> friendList;
        Query query = entityManager.createNativeQuery("SELECT * FROM friend");
        friendList  = query.getResultList();
        return friendList;
    }

    public Friend getFriend(long id){
        Friend friend = entityManager.find(Friend.class, id);
        return friend;
    }

}
