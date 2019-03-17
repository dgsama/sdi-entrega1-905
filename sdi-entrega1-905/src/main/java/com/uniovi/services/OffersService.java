package com.uniovi.services;

import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;

	@PostConstruct
	public void init() {
	}

	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer offer) {
		offer.setDate(new Date());
		offersRepository.save(offer);

	}

	public Page<Offer> getOwnOffers(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.findAllByUser(pageable, user);
		return offers;
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

	public Page<Offer> getBuyOffers(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.findBoughtByUser(pageable, user);
		return offers;
	}

	public Page<Offer> searchOffersByTitleForUser(Pageable pageable, String searchText, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%"; // Esta linea hace que la busqueda no tenga porque ser exacta

		offers = offersRepository.searchByTitleAndUser(pageable, searchText, user);

		return offers;
	}

	public Page<Offer> getOffersForUser(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.getOthersOffers(pageable, user);
		return offers;
	}

	public void updateOfferWhenBuy(Offer offer) {
		offersRepository.save(offer);
	}

}
