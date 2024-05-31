package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.projection.Report2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {
    @Query(value = "select * from card order by card.order_id  ",nativeQuery = true)
    List<Card> findAll();
    @Query(value = "select  c.name, count(tasks_id) as amount from card c join public.card_tasks ct on c.id = ct.card_id\n" +
            "group by c.id",nativeQuery = true)
    List<Report2> findAllTaskAmount();
    @Query(value = "select c.*from card c join members_cards mc on c.id = mc.cards_id  where c.owner=1 and mc.member_id=c.owner" ,nativeQuery = true)
    List<Card> findAllByMember(Integer member1);
}
