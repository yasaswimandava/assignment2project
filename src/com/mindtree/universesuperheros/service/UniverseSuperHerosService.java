package com.mindtree.universesuperheros.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mindtree.universesuperheros.entity.SuperHero;
import com.mindtree.universesuperheros.entity.Universe;
import com.mindtree.universesuperheros.exception.serviceexception.UniverseSuperHerosServiceException;

public interface UniverseSuperHerosService {

	int addUniverse(String universename) throws UniverseSuperHerosServiceException;

	String addSuperHero(String heroname, long hp) throws UniverseSuperHerosServiceException;

	String assignSuperHeroToUniverse(String assignuniverse, String assignhero) throws UniverseSuperHerosServiceException;

	Set<Universe> getSuperHeros(String uNiname) throws UniverseSuperHerosServiceException;

	Set<SuperHero> getMaxHpSuperHero(String uname) throws UniverseSuperHerosServiceException;

	String UpdateHpOfSuperHero(String upsuperhero, long userupdatehp) throws UniverseSuperHerosServiceException;








}
