package com.mindtree.universesuperheros.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mindtree.universesuperheros.entity.SuperHero;
import com.mindtree.universesuperheros.entity.Universe;
import com.mindtree.universesuperheros.exception.serviceexception.UniverseSuperHerosServiceException;
import com.mindtree.universesuperheros.service.UniverseSuperHerosService;
import com.mindtree.universesuperheros.service.serviceimplementation.UniverseSuperHerosServiceImplementation;
import com.mindtree.universesuperheros.util.UniverseSuperHerosInputUtility;

public class UniverseSuperHerosApp {
	public static void main(String[] args) {
		boolean flag = true;
		UniverseSuperHerosService service = new UniverseSuperHerosServiceImplementation();
		do {
			System.out.println("***WELCOME TO GALAXy***");
			System.out.println("ENTER OPTIONS TO PERFORM");
			System.out.println("PRESS 1 : TO ADD UNIVERSE");
			System.out.println("PRESS 2 : TO ADD SUPERHEROS");
			System.out.println("PRESS 3 : ASSIGN SUPER HEROS TO UNIVERSE");
			System.out.println("PRESS 4 : DISPLAY SUPER HEROS OF A GIVEN UNIVERSE");
			System.out.println("PRESS 5 : GET SH WITH MAX HP GIVEN UNIVERSE NAME");
			System.out.println("PRESS 6 : UPDATE HP OF THE GIVEN SUPER HERO");
			System.out.println("PRESS 7 : EXIT");
			System.out.println("****************************************");
			String option = UniverseSuperHerosInputUtility.acceptString();
			switch (option) {
			case "1":
				System.out.println("ENTER UNIVERSE NAME");
				String universename = UniverseSuperHerosInputUtility.acceptString();
				try {
					int result = service.addUniverse(universename);
					if (result == 1) {
						System.out.println("successfully added");
					}

				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "2":
				System.out.println("enter superheroname");
				String heroname = UniverseSuperHerosInputUtility.acceptString();
//				UniverseSuperHerosInputUtility.acceptString();
				System.out.println("enter hp");
				long hp = UniverseSuperHerosInputUtility.acceptLong();
				try {
					String result = service.addSuperHero(heroname, hp);
					System.out.println(result);
				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "3":
				System.out.println("enter universe name that you want to assign super heros");
				String assignuniverse = UniverseSuperHerosInputUtility.acceptString();
				System.out.println("enter super hero that you want to assign");
				String assignhero = UniverseSuperHerosInputUtility.acceptString();
				try {
					String result = service.assignSuperHeroToUniverse(assignuniverse, assignhero);
					System.out.println(result);
				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "4":
				System.out.println("enter universe name that you want to get super heros");
				String UNiname = UniverseSuperHerosInputUtility.acceptString();
				try {
					Set<Universe> result = service.getSuperHeros(UNiname);
					for (Universe i : result) {
						System.out.println("super heros" + i);
					}

				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e);
				}
				break;
			case "5":
				System.out.println("enter universe name");
				String Uname = UniverseSuperHerosInputUtility.acceptString();
				try {
					Set<SuperHero> result = service.getMaxHpSuperHero(Uname);
					for (SuperHero i : result) {
						System.out.println("super hero with max hp in that universe : " + i);
					}
				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "6":
				System.out.println("enter super hero name that you want to update hp");
				String upsuperhero = UniverseSuperHerosInputUtility.acceptString();
				System.out.println("enter hp that you want to update");
				long userupdatehp = UniverseSuperHerosInputUtility.acceptLong();
				String result;
				try {
					result = service.UpdateHpOfSuperHero(upsuperhero, userupdatehp);
					System.out.println(result);
				} catch (UniverseSuperHerosServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "7":
				System.out.println("****THANK YOU **** VISIT AGAIN****");
				flag = false;
				break;
			default:
				System.out.println("ENTER VALID OPTION");
			}
		} while (flag);
	}

}
