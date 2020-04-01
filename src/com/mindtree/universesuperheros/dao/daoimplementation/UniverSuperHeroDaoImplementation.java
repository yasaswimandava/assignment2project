package com.mindtree.universesuperheros.dao.daoimplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.mindtree.universesuperheros.dao.UniverseSuperHeroDao;
import com.mindtree.universesuperheros.entity.SuperHero;
import com.mindtree.universesuperheros.entity.Universe;
import com.mindtree.universesuperheros.exception.daoexception.UniverseSuperHerosDaoException;
import com.mindtree.universesuperheros.util.UniverseSuperHerosJdbcConnectionUtility;

public class UniverSuperHeroDaoImplementation implements UniverseSuperHeroDao {
	UniverseSuperHerosJdbcConnectionUtility connect = new UniverseSuperHerosJdbcConnectionUtility();

	@Override
	public int insertuniverse(Universe uni) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement callst = null;
		try {
			callst = con.prepareCall("{call add_universe(?)}");
			callst.setString(1, uni.getUniversename());
			callst.execute();
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException("error in database");
		} finally {
			connect.closeResource(con);
		}
		return 1;
	}

	@Override
	public boolean ValidateFromDataBase(String universename) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement callst2 = null;
		ResultSet rst = null;
		try {
			callst2 = con.prepareCall("{call get_universe}");
			rst = callst2.executeQuery();
			while (rst.next()) {
				if (universename.equalsIgnoreCase(rst.getString(2))) {
					return true;
				}
			}
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException("universe not found");
		} finally {
			connect.closeResource(rst);
			connect.closeResource(callst2);
			connect.closeResource(con);
		}
		return false;
	}

	@Override
	public boolean validateSuperHero(String heroname) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		try {
			call = con.prepareCall("{call get_superheros}");
			rst = call.executeQuery();
			while (rst.next()) {
				if (heroname.equalsIgnoreCase(rst.getString(2))) {
					return true;
				}
			}
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException("hero not found");
		} finally {
			connect.closeResource(rst);
			connect.closeResource(con);
		}
		return false;
	}

	@Override
	public String insertSuperHero(SuperHero sh) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		try {
			call = con.prepareCall("{call add_superhero(?,?)}");
			call.setString(1, sh.getSuperheroname());
			call.setLong(2, sh.getHp());
			call.execute();
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException(e);
		} finally {
			connect.closeResource(call);
			connect.closeResource(con);
		}
		return "added successfully";
	}

	@Override
	public String assignSuperHeroToUniverse(String assignuniverse, String assignhero)
			throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		try {
			int getuid = getUniverseId(assignuniverse);
			try {
				call = con.prepareCall("{call assign_superheroToUniverse(?,?)}");
				call.setInt(1, getuid);
				call.setString(2, assignhero);
				call.execute();
			} catch (SQLException e) {
				throw new UniverseSuperHerosDaoException(e);
			}
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosDaoException(e);
		} finally {
			connect.closeResource(call);
			connect.closeResource(con);
		}
		return "assigned successfully";
	}

	public int getUniverseId(String assignuniverse) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		PreparedStatement pst = null;
		ResultSet rst = null;
		String quary = "select universeid from universe where universename In ('" + assignuniverse + "')";
		try {
			pst = con.prepareStatement(quary);
			rst = pst.executeQuery();
			while (rst.next()) {
				return rst.getInt(1);
			}
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException(e);
		} finally {
			connect.closeResource(rst);
			connect.closeResource(con);
		}
		return 0;
	}

	@Override
	public Set<Universe> displaySuperHeros(String uniname) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		Set<Universe> universes = new HashSet<Universe>();
		Set<SuperHero> superheros = new HashSet<SuperHero>();
		Universe uni = new Universe();
		SuperHero sp = new SuperHero();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		ResultSet rst1 = null;
		ResultSet rst = null;

		try {
			int getidofuniverse = getUniverseId(uniname);
			String quary1 = "select * from universe where universeid IN (" + getidofuniverse + ");";
			pst1 = con.prepareStatement(quary1);
			rst1 = pst1.executeQuery();
			while (rst1.next()) {
				String quary = "select * from superhero where universeid IN(" + getidofuniverse + ");";
				pst = con.prepareStatement(quary);
				rst = pst.executeQuery();
				while (rst.next()) {
					sp = new SuperHero(rst.getInt(1), rst.getString(2), rst.getLong(3));
					superheros.add(sp);
				}
				uni = new Universe(rst1.getInt(1), rst1.getString(2), superheros);
				universes.add(uni);
			}
		} catch (SQLException | UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosDaoException(e);
		} 
			
		 finally {
			connect.closeResource(con);
		}
		return universes;
	}

	@Override
	public Set<SuperHero> getSuperHeroWithMaxhp(String uname) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		Set<SuperHero> superheroshp = new TreeSet<SuperHero>();
		SuperHero sh = new SuperHero();
		CallableStatement call = null;
		ResultSet rst = null;
		try {
			int getid = getUniverseId(uname);
			try {
				call = con.prepareCall("{call get_maxhp(?)}");
				call.setInt(1, getid);
				rst = call.executeQuery();
				while (rst.next()) {
					sh = new SuperHero(rst.getInt(1), rst.getString(2), rst.getLong(3));
					superheroshp.add(sh);
				}
			} catch (SQLException e) {
				throw new UniverseSuperHerosDaoException(e);
			}
		} catch (UniverseSuperHerosDaoException e) {
			throw new UniverseSuperHerosDaoException(e);
		} finally {
			connect.closeResource(rst);
			connect.closeResource(con);
			connect.closeResource(call);
		}
		return superheroshp;
	}

	@Override
	public String UpdateHp(String upsuperhero, long userupdatehp) throws UniverseSuperHerosDaoException {
		Connection con = UniverseSuperHerosJdbcConnectionUtility.getConnection();
		CallableStatement cst = null;
		try {
			cst = con.prepareCall("{call update_hp(?,?)}");
			cst.setString(1, upsuperhero);
			cst.setLong(2, userupdatehp);
			cst.execute();
		} catch (SQLException e) {
			throw new UniverseSuperHerosDaoException(e);
		} finally {
			connect.closeResource(cst);
			connect.closeResource(con);
		}
		return "updated Successfully";
	}
}
