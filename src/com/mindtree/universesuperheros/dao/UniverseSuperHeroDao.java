package com.mindtree.universesuperheros.dao;
import java.util.List;
import java.util.Set;

import com.mindtree.universesuperheros.entity.SuperHero;
import com.mindtree.universesuperheros.entity.Universe;
import com.mindtree.universesuperheros.exception.daoexception.UniverseSuperHerosDaoException;

public interface UniverseSuperHeroDao {

	int insertuniverse(Universe uni) throws UniverseSuperHerosDaoException;

	boolean ValidateFromDataBase(String universename) throws UniverseSuperHerosDaoException;

	boolean validateSuperHero(String heroname) throws UniverseSuperHerosDaoException;

	String insertSuperHero(SuperHero sh) throws UniverseSuperHerosDaoException;

	String assignSuperHeroToUniverse(String assignuniverse, String assignhero) throws UniverseSuperHerosDaoException;

	Set<Universe> displaySuperHeros(String uniname) throws UniverseSuperHerosDaoException;

	Set<SuperHero> getSuperHeroWithMaxhp(String uname) throws UniverseSuperHerosDaoException;

	String UpdateHp(String upsuperhero, long userupdatehp) throws UniverseSuperHerosDaoException;
}
