package org.example.imtihon_8_modul.service;

import lombok.RequiredArgsConstructor;
import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.Member;
import org.example.imtihon_8_modul.projection.Report2;
import org.example.imtihon_8_modul.repo.CardRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepo cardRepo;

    public List<Card> findAll() {
        return cardRepo.findAll();
    }

    public List<Report2> findAllTaskAMount() {
        return cardRepo.findAllTaskAmount();
    }

    public void save(Card card) {
        cardRepo.save(card);
    }

    public Card findById(Integer id) {
        return cardRepo.findById(id).get();
    }

    public void deleteById(Integer id) {
        cardRepo.deleteById(id);
    }

    public List<Card> findByMemberId(Integer id) {
        return cardRepo.findAllByMember(id);
    }


    public void change(Integer cardId, Integer orderNumberOfCard) {

        Card card = cardRepo.findById(cardId).get();
        List<Card> cards = cardRepo.findAll();
        cards.remove(card);
        cards.add(orderNumberOfCard,card);
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setOrderId(i);
            cardRepo.save(cards.get(i));
        }
    }
}