package com.mindtree.universesuperheros.entity;

import java.util.List;
import java.util.Set;

public class Universe {
	private int id;
	private String Universename;
	private Set<SuperHero> superheros;

	public Universe() {
	}

	public Universe(String universename) {
		super();
		Universename = universename;
	}

	public Universe(int id, String universename) {
		this.id = id;
		Universename = universename;
	}

	public Universe(int id, String universename, Set<SuperHero> superheros) {
		this.id = id;
		Universename = universename;
		this.superheros = superheros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUniversename() {
		return Universename;
	}

	public void setUniversename(String universename) {
		Universename = universename;
	}

	public Set<SuperHero> getSuperheros() {
		return superheros;
	}

	public void setSuperheros(Set<SuperHero> superheros) {
		this.superheros = superheros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Universename == null) ? 0 : Universename.hashCode());
		result = prime * result + id;
		result = prime * result + ((superheros == null) ? 0 : superheros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universe other = (Universe) obj;
		if (Universename == null) {
			if (other.Universename != null)
				return false;
		} else if (!Universename.equals(other.Universename))
			return false;
		if (id != other.id)
			return false;
		if (superheros == null) {
			if (other.superheros != null)
				return false;
		} else if (!superheros.equals(other.superheros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Universe [id=" + id + ", Universename=" + Universename + ", superheros=" + superheros + "]";
	}

}
