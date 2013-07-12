package be.fabrice.bookstore.domain.model.catalog;

import org.apache.commons.lang3.StringUtils;

import be.fabrice.bookstore.domain.model.InconsistentModelCreation;

public class Category {
	private String name;

	/**
	 * Constructeur obligeant la création d'une catégorie avec un nom valide.
	 * Pas de constructeur sans paramètre défini pour le moment, mais ne devra pas casser la cohéence de la classe.
	 * Le name est immutable.
	 * @param name
	 */
	public Category(String name){
		if(StringUtils.isBlank(name)){
			throw new InconsistentModelCreation("name cannot be null nor blank: "+name);
		} else {
			this.name = name.trim();
		}
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (! (obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
