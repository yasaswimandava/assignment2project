package com.mindtree.universesuperheros.entity;

public class SuperHero implements Comparable<SuperHero>{

	private int superheroid;
	private String superheroname;
	private long hp;

	public SuperHero() {
	}

	public SuperHero(int superheroid, String superheroname, long hp) {
		this.superheroid = superheroid;
		this.superheroname = superheroname;
		this.hp = hp;
	}

	public SuperHero(String superheroname, long hp) {
		super();
		this.superheroname = superheroname;
		this.hp = hp;
	}

	public int getSuperheroid() {
		return superheroid;
	}

	public void setSuperheroid(int superheroid) {
		this.superheroid = superheroid;
	}

	public String getSuperheroname() {
		return superheroname;
	}

	public void setSuperheroname(String superheroname) {
		this.superheroname = superheroname;
	}

	public long getHp() {
		return hp;
	}

	public void setHp(long hp) {
		this.hp = hp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (hp ^ (hp >>> 32));
		result = prime * result + superheroid;
		result = prime * result + ((superheroname == null) ? 0 : superheroname.hashCode());
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
		SuperHero other = (SuperHero) obj;
		if (hp != other.hp)
			return false;
		if (superheroid != other.superheroid)
			return false;
		if (superheroname == null) {
			if (other.superheroname != null)
				return false;
		} else if (!superheroname.equals(other.superheroname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuperHero [superheroid=" + superheroid + ", superheroname=" + superheroname + ", hp=" + hp + "]";
	}

	@Override
	public int compareTo(SuperHero o) {
		if(hp>o.getHp()) {
			return 1;
		}
		else if(hp<o.getHp())
		{
			return -1;
		}
		return 0;
	}
	

}
