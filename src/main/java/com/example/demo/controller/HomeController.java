package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

//@Controller
@RestController
public class HomeController {

	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String view() {
		return "index";
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "index";
	}

//	For CrudRepository
	/*
	 * @RequestMapping("/aliens")
	 * 
	 * @ResponseBody public String getAliens() { return repo.findAll().toString();
	 * 
	 * }
	 * 
	 * @RequestMapping("/alien/{aid}")
	 * 
	 * @ResponseBody public String getAlien(@PathVariable("aid") int aid) { return
	 * repo.findById(aid).toString(); }
	 */

//	 For JPARepository

	/*
	 * @RequestMapping(path="/aliens" , produces= {"application/xml"})
	 * 
	 * @ResponseBody public List<Alien> getAliens() { return repo.findAll(); }
	 * 
	 * @RequestMapping("/alien/{aid}")
	 * 
	 * @ResponseBody public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
	 * return repo.findById(aid); }
	 */

//	For RestController Only
	@GetMapping(path = "/aliens", produces = { "application/xml", "application/json" })
	public List<Alien> getAliens() {
		return repo.findAll();
	}

	@GetMapping(path = "/alien/{aid}")
	public Optional<Alien> getAliens(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}

	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable("aid") int aid) {
		Alien alien = repo.getOne(aid);
		repo.delete(alien);
		return "Deleted";
	}
	
	@PostMapping("/alien")
	public Alien save(@RequestBody Alien alien) {
		return repo.save(alien);
	}
	
	@PutMapping("/alien")
	public Alien saveOrUpdate(@RequestBody Alien alien) {
		return repo.save(alien);
	}

}
