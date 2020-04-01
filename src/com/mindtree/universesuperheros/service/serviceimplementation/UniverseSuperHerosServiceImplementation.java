package com.mindtree.universesuperheros.service.serviceimplementation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mindtree.universesuperheros.dao.UniverseSuperHeroDao;
import com.mindtree.universesuperheros.dao.daoimplementation.*;
import com.mindtree.universesuperheros.service.UniverseSuperHerosService;
import com.mindtree.universesuperheros.entity.SuperHero;
import com.mindtree.universesuperheros.entity.Universe;
import com.mindtree.universesuperheros.exception.daoexception.UniverseSuperHerosDaoException;
import com.mindtree.universesuperheros.exception.serviceexception.UniverseSuperHerosServiceException;
import com.mindtree.universesuperheros.exception.serviceexception.customexception.SuperHeroAlreadyExistsException;
import com.mindtree.universesuperheros.exception.serviceexception.customexception.SuperHeroDoesnotExistsException;
import com.mindtree.universesuperheros.exception.serviceexception.customexception.UniverseAlreadyExistsException;
import com.mindtree.universesuperheros.exception.serviceexception.customexception.UniverseNameNotFoundException;

public class UniverseSuperHerosServiceImplementation implements UniverseSuperHerosService {
	UniverseSuperHeroDao database = new UniverSuperHeroDaoImplementation();

	@Override
	public int addUniverse(String universename) throws UniverseSuperHerosServiceException {
		boolean flag = false;
		int result = 0;
		try {
			flag = database.ValidateFromDataBase(universename);
		} catch (UniverseSuperHerosDaoException e1) {
			throw new UniverseSuperHerosServiceException(e1);
		}
		if (flag == false)
			try {
				Universe uni = new Universe(universename);
				result = database.insertuniverse(uni);
			} catch (UniverseSuperHerosDaoException e) {
				throw new UniverseSuperHerosServiceException(e);
			}
		else {
			throw new UniverseAlreadyExistsException("universe already exists");
		}
		return result;
	}

	@Override
	public String addSuperHero(String heroname, long hp) throws UniverseSuperHerosServiceException {
		boolean flag = false;
		String result = "";
		try {
			flag = database.validateSuperHero(heroname);
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosServiceException(e);
		}
		if (flag == false) {
			SuperHero sh = new SuperHero(heroname, hp);
			try {
				result = database.insertSuperHero(sh);
			} catch (UniverseSuperHerosDaoException e) {
				throw new UniverseSuperHerosServiceException(e);
			}
		} else {
			throw new SuperHeroAlreadyExistsException("super hero already exists ");
		}
		return result;
	}

	@Override
	public String assignSuperHeroToUniverse(String assignuniverse, String assignhero)
			throws UniverseSuperHerosServiceException {
		boolean flag = false;
		String result = "";
		try {
			flag = database.ValidateFromDataBase(assignuniverse);

		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosServiceException(e);
		}
		if (flag == true) {
			boolean flag1 = false;
			try {
				flag1 = database.validateSuperHero(assignhero);
				if (flag1 == true) {
					result = database.assignSuperHeroToUniverse(assignuniverse, assignhero);
				} else {
					throw new SuperHeroDoesnotExistsException("super hero not found");
				}
			} catch (UniverseSuperHerosDaoException e) {
				throw new UniverseSuperHerosServiceException(e);
			}
		} else {
			throw new UniverseNameNotFoundException("universe not found");
		}
		return result;
	}

	@Override
	public Set<Universe> getSuperHeros(String uniname) throws UniverseSuperHerosServiceException {
		boolean flag = false;
		Set<Universe> result = null;
		try {
			flag = database.ValidateFromDataBase(uniname);
			if (flag == false) {
				throw new UniverseNameNotFoundException("universe not found");
			}

			if (flag == true) {

				result = database.displaySuperHeros(uniname);

			}
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosServiceException(e);
		}

		return result;
	}

	@Override
	public Set<SuperHero> getMaxHpSuperHero(String uname) throws UniverseSuperHerosServiceException {
		boolean flag = false;
		Set<SuperHero> result = null;
		try {
			flag = database.ValidateFromDataBase(uname);
			if (flag == true) {
				result = database.getSuperHeroWithMaxhp(uname);
			} else {
				throw new UniverseNameNotFoundException("universe not found");
			}
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosServiceException(e);
		}
		return result;
	}

	@Override
	public String UpdateHpOfSuperHero(String upsuperhero, long userupdatehp) throws UniverseSuperHerosServiceException {
		boolean flag = false;
		String result = "";
		try {
			flag = database.validateSuperHero(upsuperhero);
			if (flag == true) {
				result = database.UpdateHp(upsuperhero, userupdatehp);
			} else {
				throw new SuperHeroDoesnotExistsException("super hero not found");
			}
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosServiceException(e);
		}
		return result;
	}


}
