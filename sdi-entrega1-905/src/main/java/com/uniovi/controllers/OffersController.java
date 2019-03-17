package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class OffersController {
	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	/** Añadir Ofertas **/
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Offer offer, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		offer.setCreator(user);
		offersService.addOffer(offer);
		return "redirect:/user/listOffers";
	}

	@RequestMapping(value = "/offer/add")
	public String getMark(Model model) {

		return "offer/add";
	}

	/** Lista de ofertas propias **/

	@RequestMapping("/user/listOffers")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.getOwnOffers(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "/user/listOffers";
	}

	@RequestMapping("/offer/delete/{id}")
	public String delete(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/user/listOffers";
	}

	/** Lista de ofertas compradas **/

	@RequestMapping("/user/listBuy")
	public String getBuyList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.getBuyOffers(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "/user/listBuy";
	}

	/** Lista de ofertas principal **/

	@RequestMapping("/home/list")
	public String getHomeList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText, HttpSession session) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByTitleForUser(pageable, searchText, user);
		} else {
			offers = offersService.getOffersForUser(pageable, user);
		}
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "home";
	}

	@RequestMapping("/offer/buy/{id}")
	public String buyOffer(@PathVariable Long id, HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Offer offer = offersService.getOffer(id);
		if (offer.getPrice() > user.getMoney()) {
			session.setAttribute("errorMoney", true);
			return "redirect:/home/list";
		} else {
			user.setMoney(user.getMoney() - offer.getPrice());
			offer.setBuyer(user);
			offer.setSold(true);
			usersService.updateUserWhenBuy(user);
			offersService.updateOfferWhenBuy(offer);
			session.setAttribute("errorMoney", false);
		}
		session.setAttribute("money", user.getMoney());

		return "redirect:/home/list";
	}

}
